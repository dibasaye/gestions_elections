package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.Vote;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Enum.Bulletin;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Exceptions.VoteNotFoundException;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Reposirory.VoteRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public Vote creerVote(String libelle) {
        Vote vote = new Vote();
        vote.setLibelle(libelle);
        vote.setEtat("clos"); // État par défaut
        vote.setDate(new Date()); //
        return voteRepository.save(vote);
    }

    public List<Vote> afficherVotes() {
        return voteRepository.findAll(); // Affiche tous les votes
    }

    public Map<String, Object> obtenirResultats(Long idVote) {  // Changement ici pour Long
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new VoteNotFoundException("Vote non trouvé"));

        Map<String, Object> resultats = new HashMap<>();
        resultats.put("voteId", vote.getId());
        resultats.put("date", vote.getDate());
        resultats.put("etat", vote.getEtat());
        resultats.put("votants", vote.getParticipations().size());

        // En supposant que vous avez une méthode pour compter les bulletins
        resultats.put("OUI", vote.getParticipations().stream().filter(p -> p.getBulletin() == Bulletin.OUI).count());
        resultats.put("NON", vote.getParticipations().stream().filter(p -> p.getBulletin() == Bulletin.NON).count());
        resultats.put("ABSTENTION", vote.getParticipations().stream().filter(p -> p.getBulletin() == Bulletin.ABSTENTION).count());

        return resultats;
    }

    public Vote mettreAJourVote(Long idVote, String nouvelEtat) {
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new VoteNotFoundException("Vote non trouvé"));
        vote.setEtat(nouvelEtat);
        return voteRepository.save(vote);
    }
    public void supprimerVote(Long idVote) {
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new VoteNotFoundException("Vote non trouvé"));
        voteRepository.delete(vote);
    }

}
