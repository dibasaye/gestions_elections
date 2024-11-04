package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String role;

    private String username;
    private String password;

    public Object getRole() {
        return role;
    }
}
