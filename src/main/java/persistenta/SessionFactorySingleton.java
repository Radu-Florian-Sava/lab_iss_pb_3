package persistenta;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {
    private static SessionFactory sessionFactory=null;

    private SessionFactorySingleton(){};

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                System.err.println("Exceptie " + e);
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return sessionFactory;
    }

}
