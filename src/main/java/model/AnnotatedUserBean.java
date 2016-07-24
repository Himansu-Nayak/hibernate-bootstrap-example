package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AnnotatedUserBean extends AnnotatedUser {

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
