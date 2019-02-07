package org.kd.db;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

class DbSaver {

    void save(String dbFilename, IDataSet dataSet) throws IOException, DataSetException {
        var path = "src\\main\\resources\\" + dbFilename;

        if (new File(path).delete()) {
            var ps = createDbStructureScript();
            var tables = formatTablesToWrite(dataSet);
            ps.append(String.join("\n", tables.toArray(new String[tables.size()])));

            new FileOutputStream(path).write(ps.toString().getBytes());
            System.out.println("DB saved");
        }
    }

    private List<String> formatTablesToWrite(IDataSet dataSet) throws IOException, DataSetException {
        var writer = new StringWriter();

        FlatXmlDataSet.write(dataSet, writer);
        var datasetClosingTag = "</dataset>";

        var tables = writer
                .toString()
                .replace("<?xml version='1.0' encoding='UTF-8'?>", "")
                .replace(datasetClosingTag, "")
                .split("\n");

        Arrays.sort(tables, Collections.reverseOrder());//funds need to be created prior to parties,as they contain FOREIGN KEY to parties

        var tablesWithTags = new Vector<String>();
        Arrays.asList(tables).stream().forEach(table -> tablesWithTags.add(table));
        tablesWithTags.add(datasetClosingTag);

        return tablesWithTags;
    }

    private StringWriter createDbStructureScript() {

        return new StringWriter().append(
                "<?xml version='1.0' encoding='UTF-8'?>\n" +
                        "<!DOCTYPE dataset [\n" +
                        "\t<!ELEMENT dataset (FUNDS |PARTIES |TRADES)*>\n" +
                        "\n" +
                        "\t<!ELEMENT FUNDS EMPTY>\n" +
                        "\t<!ATTLIST FUNDS\n" +
                        "\t        ID CDATA #REQUIRED\n" +
                        "\t        NAME CDATA #REQUIRED\n" +
                        "\t        SHORTNAME CDATA #REQUIRED\n" +
                        "\n        UNITS CDATA #REQUIRED\n" +
                        "\t        PARTY_ID CDATA #IMPLIED\n" +
                        "\t        TRADE_ID CDATA #IMPLIED>\n" +
                        "\n" +
                        "\t<!ELEMENT PARTIES EMPTY>\n" +
                        "\t<!ATTLIST PARTIES\n" +
                        "\t        ID CDATA #REQUIRED\n" +
                        "\t        NAME CDATA #REQUIRED\n" +
                        "\t        SHORTNAME CDATA #REQUIRED>\n" +
                        "\n" +
                        "\t<!ELEMENT TRADES EMPTY>\n" +
                        "\t<!ATTLIST TRADES\n" +
                        "\t        ID CDATA #REQUIRED\n" +
                        "\t        QUANTITY CDATA #REQUIRED>\n" +
                        "\t]>\n" +
                        "\n");
    }
}
