import model.AnnotatedUser;
import model.AnnotatedUserBean;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApiCfgTest extends HibernateTest {

    private Configuration configuration;

    @Before
    public void setUp() {
        configuration = new Configuration();
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect")
                .setProperty(Environment.DRIVER, "org.h2.Driver").setProperty(Environment.USER, "sa")
                .setProperty(Environment.PASS, "").setProperty(Environment.URL, "jdbc:h2:mem:null-pointer")
                .setProperty(Environment.SHOW_SQL, "true").setProperty(Environment.FORMAT_SQL, "true")
                .setProperty(Environment.HBM2DDL_AUTO, "create-drop")
                .setProperty(Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "true");
    }

    @Test
    public void testCfgXmlMapping() {
        sessionFactory = configuration.addResource("cfg/User.hbm.xml").buildSessionFactory();

        persistUser();
    }

    @Test
    public void testBootXmlMapping() {
        sessionFactory = configuration.addResource("boot/User.hbm.xml").buildSessionFactory();

        persistUser();
    }

    @Test
    public void testAnnotationMapping() {
        sessionFactory = configuration.addAnnotatedClass(AnnotatedUser.class).buildSessionFactory();

        persistAnnotatedUser();
    }

    @Test
    public void testCfgXmlMappingBean() {
        sessionFactory = configuration.addResource("cfg/UserBean.hbm.xml").buildSessionFactory();

        persistUserBean();
    }

    @Test
    public void testBootXmlMappingBean() {
        sessionFactory = configuration.addResource("boot/UserBean.hbm.xml").buildSessionFactory();

        persistUserBean();
    }

    @Test
    public void testAnnotationMappingBean() {
        sessionFactory = configuration.addAnnotatedClass(AnnotatedUserBean.class).buildSessionFactory();

        persistAnnotatedUser();
    }

    @Test
    public void testCfgNoIdBean() {
        sessionFactory = configuration.addResource("cfg/NoIdUser.hbm.xml").buildSessionFactory();

        persistNoIdUser();
    }

    @Test
    public void testBootNoIdBean() {
        sessionFactory = configuration.addResource("boot/NoIdUser.hbm.xml").buildSessionFactory();

        persistNoIdUser();
    }

    @After
    public void cleanUp() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
