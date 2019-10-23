package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.kd.main.common.entities.ExternalTransfer;
import org.kd.main.common.entities.Transfer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExternalTransferDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Transfer> readAll() {
        var transfers = readAllNoTransact();
        entityManager.unwrap(Session.class).close();
        return transfers;
    }

    List<Transfer> readAllNoTransact() {
        var session = entityManager.unwrap(Session.class);
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(ExternalTransfer.class);
        criteria.from(ExternalTransfer.class);

        var externalTransfers = session.createQuery(criteria).getResultList();
        var transfers = new ArrayList<Transfer>(externalTransfers);
        return transfers;
    }

    Transfer readTransferByPrimaryKey(long id) {
        return entityManager.find(ExternalTransfer.class, id);
    }

    Session getSession() {
        /*
        Container like Spring is not resposible for maintaing the life cycle for the EntityManager created from it. But you can @Autowire EntityManagerFactory directly as the bean is already configured in the applicationContext. Hence this could be simple like this emf.createEntityManager().unwrap(Session.class)' to get Session`
         */

        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new RuntimeException("No entityManager available");

        }
        return session;
    }
}
