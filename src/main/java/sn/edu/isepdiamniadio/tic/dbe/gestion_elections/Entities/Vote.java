package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String libelle;
    private String etat = "clos"; // Le vote est par défaut "clos" à la création

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private List<VoteParticipation> participations = new ArrayList<>();

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<VoteParticipation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<VoteParticipation> participations) {
        this.participations = participations;
    }
}
