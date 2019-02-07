package org.kd.db;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.*;

class DbSaver {

    void save(String dbFilename, IDataSet dataSet) throws IOException, DataSetException {
        var path = "src\\main\\resources\\" + dbFilename;

        if (new File(path).delete()) {
            var dbScript = new FileOutputStream(path);

            var ps = createDbStructureScript();

            StringWriter writer = new StringWriter();
            FlatXmlDataSet.write(dataSet, writer);
            ps.append(writer.toString().replace("<?xml version='1.0' encoding='UTF-8'?>", ""));

            dbScript.write(ps.toString().getBytes());
            // dependent tables database export: export table X and all tables that
            // have a PK which is a FK on X, in the right order for insertion
           /* var depTableNames = TablesDependencyHelper.getAllDependentTables(databaseConnection, "parties");
            var depDataSet = databaseConnection.createDataSet(depTableNames);
            FlatXmlDataSet.write(depDataSet, new FileOutputStream(dbFilename));*/
            System.out.println("DB saved");
        }
    }

    private StringWriter createDbStructureScript() {

        return new StringWriter().append(
                "<?xml version='1.0' encoding='UTF-8'?>\n" +
                        "<!DOCTYPE dataset [\n" +
                        "\t<!ELEMENT dataset (FUNDS |PARTIES |TRADES)*>\n" +
                        "\n\n" +
                        "\t<!ELEMENT FUNDS EMPTY>\n" +
                        "\t<!ATTLIST FUNDS\n" +
                        "\t        ID CDATA #REQUIRED\n" +
                        "\t        NAME CDATA #REQUIRED\n" +
                        "\t        SHORT_NAME CDATA #REQUIRED\n" +
                        "\n        UNITS CDATA #REQUIRED\n" +
                        "\t        PARTY_ID CDATA #IMPLIED\n" +
                        "\t        TRADE_ID CDATA #IMPLIED>\n" +
                        "\n" +
                        "\t<!ELEMENT PARTIES EMPTY>\n" +
                        "\t<!ATTLIST PARTIES\n" +
                        "\t        ID CDATA #REQUIRED\n" +
                        "\t        NAME CDATA #REQUIRED\n" +
                        "\t        SHORT_NAME CDATA #REQUIRED>\n" +
                        "\n" +
                        "\t<!ELEMENT TRADES EMPTY>\n" +
                        "\t<!ATTLIST TRADES\n" +
                        "\t        ID CDATA #REQUIRED\n" +
                        "\t        QUANTITY CDATA #REQUIRED>\n" +
                        "\t]>\n" +
                        "\n");
    }
}
