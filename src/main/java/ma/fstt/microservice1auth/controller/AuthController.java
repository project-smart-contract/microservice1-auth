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

            personneMorale.setNomSoc(user.getNomSoc());
            personneMorale.setNumSocie(user.getNumSocie());

            userCredential = personneMorale;
        } else if ("PHYSIQUE".equals(user.getUserType())) {
            PersonnePhysique personnePhysique = new PersonnePhysique();
            
            personnePhysique.setNom(user.getNom());
            personnePhysique.setPrenom(user.getPrenom());
            personnePhysique.setNumeroTelephone(user.getNumeroTelephone());
            personnePhysique.setnumPermis(user.getNumPermis());
            personnePhysique.setcin(user.getcin());
            personnePhysique.setadresse(user.getAdresse());

            personnePhysique.setdateNaissance(user.getDateNaissance());

            userCredential = personnePhysique;
        } else {
            return new ResponseEntity<>("Invalid user type", HttpStatus.BAD_REQUEST);
        }



        userCredential.setemail(user.getEmail());
        userCredential.setPassword(user.getPassword());


        String savedUser = String.valueOf(service.saveUser(userCredential));
        if (savedUser == null) {
            return new ResponseEntity<>("Failed to add user", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userCredential, HttpStatus.CREATED);


    }

    

//    @GetMapping("/validate")
//    public String validateToken(@RequestParam("token") String token) {
//        service.validateToken(token);
//        return "Token is valid";
//    }
}
