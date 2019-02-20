package org.kd.main.model.data.db;

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
            var stringWriter = createDbStructureScript();
            var tables = formatTablesToWrite(dataSet);
            stringWriter.append(String.join("\n", tables.toArray(new String[0])));

            new FileOutputStream(path).write(stringWriter.toString().getBytes());
            System.out.println("DB saved");
        }
    }

    private List<String> formatTablesToWrite(IDataSet dataSet) throws IOException, DataSetException {
        var writer = new StringWriter();

        FlatXmlDataSet.write(dataSet, writer);
        var datasetOpeningTag = "<dataset>";
        var datasetClosingTag = "</dataset>";

        var tables = writer
                .toString()
                .replace("<?xml version='1.0' encoding='UTF-8'?>", "")
                .replace(datasetOpeningTag, "")
                .replace(datasetClosingTag, "")
                .split("\n");

        Arrays.sort(tables, Collections.reverseOrder());//funds need to be created prior to parties,as they contain FOREIGN KEY to parties

        var tablesWithTags = new Vector<String>();
        tablesWithTags.add(datasetOpeningTag);
        Arrays.asList(tables).forEach(tablesWithTags::add);
        tablesWithTags.add(datasetClosingTag + "\n");

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
                        "\t\tID CDATA #REQUIRED\n" +
                        "\t\tNAME CDATA #REQUIRED\n" +
                        "\t\tSHORTNAME CDATA #REQUIRED\n" +
                        "\n\tUNITS CDATA #REQUIRED\n" +
                        "\t\tPARTY_ID CDATA #IMPLIED\n" +
                        "\t\tTRADE_ID CDATA #IMPLIED>\n" +
                        "\n" +
                        "\t<!ELEMENT PARTIES EMPTY>\n" +
                        "\t<!ATTLIST PARTIES\n" +
                        "\t\tID CDATA #REQUIRED\n" +
                        "\t\tNAME CDATA #REQUIRED\n" +
                        "\t\tSHORTNAME CDATA #REQUIRED>\n" +
                        "\n" +
                        "\t<!ELEMENT TRADES EMPTY>\n" +
                        "\t<!ATTLIST TRADES\n" +
                        "\t\tID CDATA #REQUIRED\n" +
                        "\t\tQUANTITY CDATA #REQUIRED>\n" +
                        "\t]>\n");
    }
}
