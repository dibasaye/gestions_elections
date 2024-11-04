package sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Entities.VoteParticipation;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Services.VoteParticipationService;
import sn.edu.isepdiamniadio.tic.dbe.gestion_elections.Enum.Bulletin;

@RestController
@RequestMapping("/api/vote")
public class VoteParticipationController {

    @Autowired
    private VoteParticipationService voteParticipationService;

    @PostMapping("/{idVote}/participation")
    public ResponseEntity<String> voter(@PathVariable int idVote, @RequestParam String bulletin, @RequestParam String Depute) {
        try {
            Bulletin voteBulletin = Bulletin.valueOf(bulletin.toUpperCase()); // Convertir le bulletin en Enum
            VoteParticipation participation = voteParticipationService.enregistrerVote(idVote, voteBulletin, Long.valueOf(Depute));
            return ResponseEntity.status(201).body("Honorable " + Depute + ", votre vote est bien pris en compte !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Valeur de bulletin invalide ! Utilisez OUI, NON ou ABSTENTION.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Une erreur est survenue, veuillez réessayer plus tard !");
        }
    }
}
