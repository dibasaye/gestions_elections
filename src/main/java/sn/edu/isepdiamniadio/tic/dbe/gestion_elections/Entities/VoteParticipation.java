package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities;

import jakarta.persistence.*;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Enum.Bulletin;

@Entity
public class VoteParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Bulletin bulletin;
    @ManyToOne
    private User depute;

    public User getDepute() {
        return depute;
    }

    public void setDepute(User depute) {
        this.depute = depute;
    }

    public Bulletin getBulletin() {
        return bulletin;
    }

    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @ManyToOne
    private Vote vote;

    // Getters, setters et constructeurs
}
