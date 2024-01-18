package ma.fstt.microservice1auth.controller;

import ma.fstt.microservice1auth.entity.*;
import ma.fstt.microservice1auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ma.fstt.microservice1auth.dto.AuthRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/auth")

public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest user) {
        UserCredential userCredential;

        if ("MORALE".equals(user.getUserType())) {
            PersonneMorale personneMorale = new PersonneMorale();
            // Définissez les champs spécifiques à PersonneMorale
            personneMorale.setNomSoc(user.getNomSoc());
            personneMorale.setNumSocie(user.getNumSocie());

            userCredential = personneMorale;
        } else if ("PHYSIQUE".equals(user.getUserType())) {
            PersonnePhysique personnePhysique = new PersonnePhysique();
            // Définissez les champs spécifiques à PersonnePhysique
            personnePhysique.setNom(user.getNom());
            personnePhysique.setPrenom(user.getPrenom());
            personnePhysique.setNumeroTelephone(user.getNumeroTelephone());
            personnePhysique.setnumPermis(user.getNumPermis());
            personnePhysique.setCIN(user.getCIN());
            personnePhysique.setadresse(user.getAdresse());

            personnePhysique.setdateNaissance(user.getDateNaissance());

            userCredential = personnePhysique;
        } else {
            return new ResponseEntity<>("Invalid user type", HttpStatus.BAD_REQUEST);
        }


        // Définissez les champs communs
        userCredential.setemail(user.getEmail());
        userCredential.setPassword(user.getPassword());

        // Enregistrez l'utilisateur et vérifiez si l'enregistrement a réussi
        String savedUser = service.saveUser(userCredential);
        if (savedUser == null) {
            return new ResponseEntity<>("Failed to add user", HttpStatus.BAD_REQUEST);
        }

        // Générez un token pour l'utilisateur
        String token = service.generateToken(user.getNom());

        // Créez une réponse contenant le message de succès et le token
        Map<String, String> response = new HashMap<>();
        response.put("message", "User added successfully");
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    
//    @PostMapping("/token")
//    public String getToken(@RequestBody UserCredential userCredential) {
//        return service.generateToken(userCredential.getName());
//    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
