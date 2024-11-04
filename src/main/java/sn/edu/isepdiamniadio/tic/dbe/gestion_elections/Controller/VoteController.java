package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.Vote;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Services.VoteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // Créer un nouveau vote
    @PostMapping
    public ResponseEntity<Vote> creerVote(@RequestBody String libelle) {
        Vote vote = voteService.creerVote(libelle);
        return new ResponseEntity<>(vote, HttpStatus.CREATED);
    }

    // Afficher tous les votes
    @GetMapping
    public ResponseEntity<List<Vote>> afficherVotes() {
        List<Vote> votes = voteService.afficherVotes();
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }

    // Obtenir les résultats d'un vote spécifique
    @GetMapping("/{idVote}/resultats")
    public ResponseEntity<Map<String, Object>> obtenirResultats(@PathVariable Long idVote) {
        Map<String, Object> resultats = voteService.obtenirResultats(idVote);
        return new ResponseEntity<>(resultats, HttpStatus.OK);
    }

    // Mettre à jour l'état d'un vote
    @PutMapping("/{idVote}/etat")
    public ResponseEntity<Vote> mettreAJourEtat(@PathVariable Long idVote, @RequestBody String nouvelEtat) {
        Vote voteMisAJour = voteService.mettreAJourVote(idVote, nouvelEtat);
        return new ResponseEntity<>(voteMisAJour, HttpStatus.OK);
    }

    // Supprimer un vote
    @DeleteMapping("/{idVote}")
    public ResponseEntity<Void> supprimerVote(@PathVariable Long idVote) {
        voteService.supprimerVote(idVote);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
