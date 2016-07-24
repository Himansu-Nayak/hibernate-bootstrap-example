package model;

import javax.persistence.*;

@Entity
public class AnnotatedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public String id;
    @Column
    public String login;
}
