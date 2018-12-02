package org.kd;

import org.kd.ui.Ui;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dao-test-context.xml");

        HibernateTransactionManager transactionManager = context.getBean(HibernateTransactionManager.class);
        Ui ui = context.getBean(Ui.class);

        ui.go();
    }

}
