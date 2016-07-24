import static org.fest.assertions.api.Assertions.assertThat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.AnnotatedUser;
import model.AnnotatedUserBean;
import model.NoIdUser;
import model.User;
import model.UserBean;

public abstract class HibernateTest {

    protected SessionFactory sessionFactory;

    private void persistAndClear(Object entity) {
        Session session = sessionFactory.openSession();
        session.persist(entity);
        session.flush();
        session.close();
    }

    protected void persistUser() {
        User user = new User();
        user.login = "test";

        persistAndClear(user);
        assertThat(user.id).isNotEmpty();
    }

    protected void persistAnnotatedUser() {
        AnnotatedUser user = new AnnotatedUser();
        user.login = "test";

        persistAndClear(user);
        assertThat(user.id).isNotEmpty();
    }

    protected void persistUserBean() {
        UserBean user = new UserBean();
        user.login = "test";

        persistAndClear(user);
        assertThat(user.id).isNotEmpty();
    }

    protected void persistAnnotatedUserBean() {
        AnnotatedUserBean user = new AnnotatedUserBean();
        user.login = "test";

        persistAndClear(user);
        assertThat(user.id).isNotEmpty();
    }

    protected void persistNoIdUser() {
        NoIdUser user = new NoIdUser();
        user.login = "test";

        persistAndClear(user);
    }
}
