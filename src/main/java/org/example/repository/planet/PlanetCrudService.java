package org.example.repository.planet;

import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.hibernate.HibernateUtils;
import org.example.repository.GenericDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class PlanetCrudService implements GenericDao<Planet> {

    private static final String GET_ALL_PLANET_QUERY = "FROM Planet";

    @Override
    public boolean create(Planet planet) {
        boolean result = false;

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.persist(planet);
                transaction.commit();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public boolean update(Planet planet) {
        boolean result = false;

        if (Objects.isNull(planet.getId())) {
            return false;
        }

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.merge(planet);
                transaction.commit();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public Planet getById(Object id) {
        String planetId = (String) id;

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Planet planet = session.get(Planet.class, planetId);

            planet.getFromPlanetTickets().forEach(Ticket::getId);
            planet.getToPlanetTickets().forEach(Ticket::getId);

            return planet;
        }
    }

    @Override
    public List<Planet> getAll() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_PLANET_QUERY, Planet.class).list();
        }
    }

    @Override
    public void deleteById(Object id) {
        String planetId = (String) id;

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet existing = session.get(Planet.class, planetId);
            session.remove(existing);
            transaction.commit();
        }
    }

    @Override
    public void delete(Planet planet) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }
}
