package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Reposirory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.Vote;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote>findByetat(String etat);
}

