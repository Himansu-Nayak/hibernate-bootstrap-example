import model.AnnotatedUser;
import model.AnnotatedUserBean;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.metamodel.MetadataSources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApiBootTest extends HibernateTest {

    private StandardServiceRegistry serviceRegistry;
    private MetadataSources metadataSources;

    @Before
    public void setUp() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySetting(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect")
                .applySetting(AvailableSettings.DRIVER, "org.h2.Driver").applySetting(AvailableSettings.USER, "sa")
                .applySetting(AvailableSettings.PASS, "")
                .applySetting(AvailableSettings.URL, "jdbc:h2:mem:null-pointer")
                .applySetting(AvailableSettings.SHOW_SQL, true).applySetting(AvailableSettings.FORMAT_SQL, true)
                .applySetting(AvailableSettings.HBM2DDL_AUTO, "create-drop")
                .applySetting(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, true);

        serviceRegistry = builder.build();
        metadataSources = new MetadataSources(serviceRegistry);
    }

    @Test
    public void testBootXmlMapping() {
        metadataSources.addResource("boot/User.hbm.xml");
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistUser();
    }

    @Test
    public void testCfgXmlMapping() {
        metadataSources.addResource("cfg/User.hbm.xml");
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistUser();
    }

    @Test
    public void testAnnotationMapping() {
        metadataSources.addAnnotatedClass(AnnotatedUser.class);
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistAnnotatedUser();
    }

    @Test
    public void testBootXmlMappingBean() {
        metadataSources.addResource("boot/UserBean.hbm.xml");
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistUserBean();
    }

    @Test
    public void testCfgXmlMappingBean() {
        metadataSources.addResource("cfg/UserBean.hbm.xml");
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistUserBean();
    }

    @Test
    public void testAnnotationMappingBean() {
        metadataSources.addAnnotatedClass(AnnotatedUserBean.class);
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistAnnotatedUserBean();
    }

    @Test
    public void testCfgNoIdBean() {
        metadataSources.addResource("cfg/NoIdUser.hbm.xml");
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistNoIdUser();
    }

    @Test
    public void testBootNoIdBean() {
        metadataSources.addResource("boot/NoIdUser.hbm.xml");
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

        persistNoIdUser();
    }

    @After
    public void cleanUp() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
