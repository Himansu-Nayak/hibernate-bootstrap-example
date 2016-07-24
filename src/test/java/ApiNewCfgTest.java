import model.AnnotatedUser;
import model.AnnotatedUserBean;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApiNewCfgTest extends HibernateTest {

    private StandardServiceRegistry serviceRegistry;
    private Configuration configuration;

    @Before
    public void setUp() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySetting(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect")
                .applySetting(AvailableSettings.DRIVER, "org.h2.Driver").applySetting(AvailableSettings.USER, "sa")
                .applySetting(AvailableSettings.PASS, "")
                .applySetting(AvailableSettings.URL, "jdbc:h2:mem:null-pointer")
                .applySetting(AvailableSettings.SHOW_SQL, "true").applySetting(AvailableSettings.FORMAT_SQL, "true")
                .applySetting(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true")
                .applySetting(AvailableSettings.HBM2DDL_AUTO, "create-drop");

        serviceRegistry = builder.build();

        configuration = new Configuration().setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop");
    }

    @Test
    public void testCfgXmlMapping() {
        sessionFactory = configuration.addResource("cfg/User.hbm.xml").buildSessionFactory(serviceRegistry);

        persistUser();
    }

    @Test
    public void testBootXmlMapping() {
        sessionFactory = configuration.addResource("boot/User.hbm.xml").buildSessionFactory(serviceRegistry);

        persistUser();
    }

    @Test
    public void testAnnotationMapping() {
        sessionFactory = configuration.addAnnotatedClass(AnnotatedUser.class).buildSessionFactory(serviceRegistry);

        persistAnnotatedUser();
    }

    @Test
    public void testCfgXmlMappingBean() {
        sessionFactory = configuration.addResource("cfg/UserBean.hbm.xml").buildSessionFactory(serviceRegistry);

        persistUserBean();
    }

    @Test
    public void testBootXmlMappingBean() {
        sessionFactory = configuration.addResource("boot/UserBean.hbm.xml").buildSessionFactory(serviceRegistry);

        persistUserBean();
    }

    @Test
    public void testAnnotationMappingBean() {
        sessionFactory = configuration.addAnnotatedClass(AnnotatedUserBean.class).buildSessionFactory(serviceRegistry);

        persistAnnotatedUserBean();
    }

    @Test
    public void testCfgNoIdBean() {
        sessionFactory = configuration.addResource("cfg/NoIdUser.hbm.xml").buildSessionFactory(serviceRegistry);

        persistNoIdUser();
    }

    @Test
    public void testBootNoIdBean() {
        sessionFactory = configuration.addResource("boot/NoIdUser.hbm.xml").buildSessionFactory(serviceRegistry);

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
