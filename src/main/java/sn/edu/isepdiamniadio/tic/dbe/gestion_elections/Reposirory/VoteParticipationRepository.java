package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Reposirory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.User;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.Vote;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.VoteParticipation;

import java.util.Optional;

@Repository
public interface VoteParticipationRepository extends JpaRepository<VoteParticipation, Long> {
    Optional<VoteParticipation> findByVoteAndDepute(Vote vote, User depute);
}
