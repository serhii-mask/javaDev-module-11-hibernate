package org.example.repository;

import org.example.entities.Planet;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class PlanetCrudService implements PlanetDao {

    private static final String GET_ALL_PLANET_QUERY = "FROM Planet";

    @Override
    public boolean createPlanet(Planet planet) {
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
    public boolean updatePlanet(Planet planet) {
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
    public Planet getPlanetById(String planetId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Planet.class, planetId);
        }
    }

    @Override
    public List<Planet> getAllPlanets() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_PLANET_QUERY, Planet.class).list();
        }
    }

    @Override
    public void deletePlanetById(String planetId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet existing = session.get(Planet.class, planetId);
            session.remove(existing);
            transaction.commit();
        }
    }

    @Override
    public void deletePlanet(Planet planet) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }
}
