package org.kd.main.model;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.kd.dao.PartyDao;
import org.kd.dao.FundDao;
import org.kd.db.DbManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class DataModelManager {

    private static PartyDao partyDao;
    private static FundDao fundDao;
    private static ApplicationContext context;
    private static DbManager dbManager;

    public static void initApplication() {
        context = new ClassPathXmlApplicationContext("app-context.xml");
        dbManager = context.getBean(DbManager.class);
        initDb(context.getBean(DatabaseDataSourceConnection.class));
        partyDao = context.getBean(PartyDao.class);
        fundDao = context.getBean(FundDao.class);
    }

    public static void clearDb() {
        try {
            dbManager.tearDownDb(context.getBean(DatabaseDataSourceConnection.class));
        } catch (DatabaseUnitException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initDb(IDatabaseConnection databaseConnection) {
        try {
            dbManager.setupDb(databaseConnection);
        } catch (SQLException | DatabaseUnitException e) {
            e.printStackTrace();
        }
    }

    public static PartyDao getPartyDao() {
        return partyDao;
    }

    public static void setPartyDao(PartyDao partyDao) {
        DataModelManager.partyDao = partyDao;
    }

    public static FundDao getFundDao() {
        return fundDao;
    }

    public static void setFundDao(FundDao fundDao) {
        DataModelManager.fundDao = fundDao;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        DataModelManager.context = context;
    }
}
