package org.kd.main;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.kd.dao.CounterpartyDao;
import org.kd.dao.FundDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import java.io.InputStream;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dao-test-context.xml");

        IDatabaseConnection databaseConnection = context.getBean(DatabaseDataSourceConnection.class);
        try {
            IDataSet dataSet = loadDataSet();
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
            CounterpartyDao counterpartyDao = context.getBean(CounterpartyDao.class);
            FundDao fundDao = context.getBean(FundDao.class);

            System.out.println(counterpartyDao.get(1).getName());
            System.out.println(fundDao.get(1).getId() + " " + fundDao.get(1).getName());

        } catch (SQLException | DatabaseUnitException e) {
            e.printStackTrace();
        }
    }

    private static IDataSet loadDataSet() throws DataSetException {
        String name = "dbunit-dataset.xml";
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        if (stream == null) {
            throw new IllegalStateException("Dbunit file '" + name + "' does not exist");
        }
        return new FlatXmlDataSetBuilder().build(stream);
    }
}
