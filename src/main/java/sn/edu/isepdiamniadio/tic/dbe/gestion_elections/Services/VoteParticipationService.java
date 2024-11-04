package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.User;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.Vote;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.VoteParticipation;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Enum.Bulletin;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Exceptions.UnauthorizedException;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Exceptions.VoteNotFoundException;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Reposirory.UserRepository;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Reposirory.VoteParticipationRepository;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Reposirory.VoteRepository;

@Service
public class VoteParticipationService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteParticipationRepository participationRepository;

    public String voter(Long idVote, Long deputeId, Bulletin bulletin) {
        Vote vote = voteRepository.findById(idVote).orElseThrow(() -> new VoteNotFoundException("Identifiant de vote introuvable!"));

        if (!vote.getEtat().equals("ouvert")) {
            return "Désolé, le vote est déjà clos !";
        }

        User depute = userRepository.findById(deputeId).orElseThrow(() -> new UnauthorizedException("Désolé, vous n'êtes pas autorisé à voter !"));
        if (!depute.getRole().equals("depute")) {
            return "Désolé, vous n'êtes pas autorisé à voter !";
        }

        if (participationRepository.findByVoteAndDepute(vote, depute).isPresent()) {
            return "Vous avez déjà voté !";
        }

        VoteParticipation participation = new VoteParticipation();
        participation.setVote(vote);
        participation.setDepute(depute);
        participation.setBulletin(bulletin);

        participationRepository.save(participation);

        return "Votre vote est bien pris en compte !";
    }

    public VoteParticipation enregistrerVote(long idVote, Bulletin voteBulletin, Long deputeId) {
        // Vérifier si le vote existe
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new VoteNotFoundException("Vote non trouvé"));

        // Vérifier si le vote est ouvert
        if (!vote.getEtat().equals("ouvert")) {
            throw new IllegalStateException("Le vote doit être ouvert pour participer.");
        }

        // Vérifier si le député existe
        User depute = userRepository.findById(deputeId)
                .orElseThrow(() -> new UnauthorizedException("Député non autorisé à voter."));

        // Vérifier si le député a déjà voté
        if (participationRepository.findByVoteAndDepute(vote, depute).isPresent()) {
            throw new IllegalStateException("Le député a déjà voté pour ce vote.");
        }

        // Créer une nouvelle participation
        VoteParticipation participation = new VoteParticipation();
        participation.setVote(vote);
        participation.setDepute(depute);
        participation.setBulletin(voteBulletin);

        // Enregistrer la participation
        return participationRepository.save(participation);
        // Retourne l'objet VoteParticipation enregistré
    }
}
