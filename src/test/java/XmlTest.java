import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;
import org.junit.Test;

public class XmlTest extends HibernateTest {

    @Test
    public void testBootXmlMapping() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.configure("boot/hibernate.cfg.xml");

        StandardServiceRegistry serviceRegistry = builder.build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistUser();
        persistUserBean();
        persistAnnotatedUser();
        persistAnnotatedUserBean();

        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    @Test
    public void testCfgXmlMapping() {
        sessionFactory = new Configuration().configure("cfg/hibernate.cfg.xml").buildSessionFactory();

        persistUser();
        persistUserBean();
        persistAnnotatedUser();
        persistAnnotatedUserBean();
        persistNoIdUser();

        sessionFactory.close();
    }

    @Test
    public void testNewCfgXmlMapping() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.configure("cfg/hibernate.cfg.xml");

        sessionFactory = new Configuration().configure("cfg/hibernate.cfg.xml").buildSessionFactory(builder.build());

        persistUser();
        persistUserBean();
        persistAnnotatedUser();
        persistAnnotatedUserBean();
        persistNoIdUser();

        sessionFactory.close();
    }

}
