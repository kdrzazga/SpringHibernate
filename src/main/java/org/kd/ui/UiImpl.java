package org.kd.ui;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.kd.dao.CounterpartyDao;
import org.kd.dao.FundDao;
import org.kd.dao.hibernate.FundDaoImpl;
import org.kd.entities.Counterparty;
import org.kd.entities.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import java.io.InputStream;

public class UiImpl implements Ui {

    @Autowired
    private CounterpartyDao counterpartyDao;


    @Autowired
    private IDatabaseConnection databaseConnection;

    public void setUp() throws Exception {
        IDataSet dataSet = loadDataSet();
        DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
    }

    protected IDataSet loadDataSet() throws Exception {
        setUp();

        String name = "dbunit-dataset.xml";
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        if (stream == null) {
            throw new IllegalStateException("Dbunit file '" + name + "' does not exist");
        }
        return new FlatXmlDataSetBuilder().build(stream);
    }

    @Override
    public void go() {
/*
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dao-test-context.xml");

        HibernateTransactionManager transactionManager = context.getBean(HibernateTransactionManager.class);
        FundDao fundDao = context.getBean(FundDaoImpl.class);
        CounterpartyDao counterpartyDao = context.getBean(CounterpartyDao.class);
*/
        Counterparty cpty1 = counterpartyDao.get(1);
        Counterparty cpty2 = counterpartyDao.get(2);

       /* Fund fund1 = fundDao.get(1);
        Fund fund2 = fundDao.get(2);
*/
        System.out.println(cpty1);
        System.out.println(cpty2);
    }
}
