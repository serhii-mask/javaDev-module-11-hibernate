package org.example.hibernate;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.migration.FlywayMigrationService;
import org.example.props.PropertyReader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final HibernateUtils INSTANCE = new HibernateUtils();
    private SessionFactory sessionFactory;
    private final FlywayMigrationService migrationService;

    private HibernateUtils() {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();

        this.migrationService = new FlywayMigrationService();
    }

    public static HibernateUtils getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void closeSessionFactory() {
        this.sessionFactory.close();
    }

    public void migrateDatabase() {
        String connectionUrl = PropertyReader.getConnectionUrlForPostgres();
        String username = PropertyReader.getUserForPostgres();
        String password = PropertyReader.getPasswordForPostgres();

        migrationService.migrate(connectionUrl, username, password);
    }
}
