package ma.fstt.microservice1auth.controller;

import ma.fstt.microservice1auth.dto.AuthRequest;
import ma.fstt.microservice1auth.entity.UserCredential;
import ma.fstt.microservice1auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/auth")
    public class LoginController {

        @Autowired
        private AuthService service;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody AuthRequest user) {
            UserCredential userCredential = service.validateUser(user);
            if (userCredential != null) {
                return new ResponseEntity<>("Utilisateur connu", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Utilisateur inconnu", HttpStatus.UNAUTHORIZED);
            }
        }
    }



