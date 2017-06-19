package provider;


import org.hibernate.Session;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;


import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.Properties;
import java.util.Set;

/**
 * Created by alexandremasanes on 24/04/2017.
 */

@Singleton
public class SessionProducer {

    @Produces
    public Session getSession() {
        Properties properties;
        Configuration configuration;

        configuration = new Configuration();
        properties = configuration.configure("META-INF/hbn.xml").getProperties();
        new StandardServiceRegistryBuilder().applySettings(properties);

        Reflections reflections = new Reflections("business.model.mapping");

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);

        classes.forEach(clazz -> configuration.addAnnotatedClass(clazz));

        return configuration.buildSessionFactory().openSession();
    }
}
