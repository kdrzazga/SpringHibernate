package org.kd.main.model.data;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.hibernate.SessionFactory;
import org.kd.main.model.data.dao.*;
import org.kd.main.model.data.db.DbManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import java.sql.SQLException;

@Configuration
@ImportResource("classpath:app-context.xml")
public class Config {

    @Autowired
    DriverManagerDataSource dataSource;

    @Autowired
    SessionFactory sessionFactory;

    @Bean
    public DatabaseDataSourceConnection databaseDataSourceConnection() throws SQLException {
        return new DatabaseDataSourceConnection(dataSource);
    }

    @Bean
    public DbManager dbManager() {
        var dbManager = new DbManager();
        dbManager.setDbFilename("dbunit-dataset.xml");
        return dbManager;
    }

    @Bean
    public PartyDao partyDao() {
        var partyDao = new PartyDaoImpl();
        partyDao.setSessionFactory(sessionFactory);
        return partyDao;
    }

    @Bean
    public FundDao fundDao() {
        var fundDao = new FundDaoImpl();
        fundDao.setSessionFactory(sessionFactory);
        return fundDao;
    }

    @Bean
    public TradeDao tradeDao() {
        var tradeDao = new TradeDaoImpl();
        tradeDao.setSessionFactory(sessionFactory);
        return tradeDao;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        var mgr = new HibernateTransactionManager();
        mgr.setDataSource(dataSource);
        mgr.setSessionFactory(sessionFactory);
        return mgr;
    }

}
