package org.kd.db;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.inject.Named;
import java.sql.SQLException;

@Named
public class DbManager {

    private String dbFilename;

    public void setupDb(IDatabaseConnection databaseConnection) throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, loadDataSet());
    }

    public void tearDownDb(IDatabaseConnection databaseConnection) throws DatabaseUnitException, SQLException {
        DatabaseOperation.DELETE_ALL.execute(databaseConnection, loadDataSet());
    }

    private IDataSet loadDataSet() throws DataSetException {

        var stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(dbFilename);
        if (stream == null) {
            throw new IllegalStateException("Dbunit file '" + dbFilename + "' does not exist");
        }
        return new FlatXmlDataSetBuilder().build(stream);
    }

    public void setDbFilename(String dbFilename) {
        this.dbFilename = dbFilename;
    }
}
