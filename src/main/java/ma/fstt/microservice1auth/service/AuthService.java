package ma.fstt.microservice1auth.service;


import ma.fstt.microservice1auth.dto.AuthRequest;
import ma.fstt.microservice1auth.entity.*;
import ma.fstt.microservice1auth.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {
    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        // Encodez le mot de passe commun
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));

        // Vérifiez le type de UserCredential et traitez les données spécifiques
        if (credential instanceof PersonnePhysique) {
            PersonnePhysique personnePhysique = (PersonnePhysique) credential;
            // Traitez les données spécifiques à PersonnePhysique
            System.out.println("Nom: " + personnePhysique.getNom());
            System.out.println("Prénom: " + personnePhysique.getPrenom());
            System.out.println("Numéro de téléphone: " + personnePhysique.getNumeroTelephone());
            System.out.println("CIN: " + personnePhysique.getCIN());
            System.out.println("Adresse: " + personnePhysique.getAdresse());
//            System.out.println("email " + personnePhysique.getEmail());
            System.out.println("Numéro de permis: " + personnePhysique.getNumPermis());
            // ...
        } else if (credential instanceof PersonneMorale) {
            PersonneMorale personneMorale = (PersonneMorale) credential;
            // Traitez les données spécifiques à PersonneMorale
            System.out.println("Nom de la société: " + personneMorale.getNomSoc());
            System.out.println("Numéro de la société: " + personneMorale.getNumSocie());
            // ...
        }


        repository.save(credential);
        return "User added to the system";
    }


    //  public String saveUser(UserCredential credential) {
        //credential.setPassword(passwordEncoder.encode(credential.getPassword()));
//        if (credential instanceof PersonnePhysique) {
//            // Code spécifique à PersonnePhysique
//            PersonnePhysique personnePhysique = (PersonnePhysique) credential;
//            personnePhysique.setMdp(passwordEncoder.encode(personnePhysique.getMdp()));
//            personnePhysique.setConfirmMdp(passwordEncoder.encode(personnePhysique.getConfirmMdp()));
//        } else if (credential instanceof PersonneMorale) {
//            // Code spécifique à PersonneMorale
//            PersonneMorale personneMorale = (PersonneMorale) credential;
//            personneMorale.setMdp(passwordEncoder.encode(personneMorale.getMdp()));
//            personneMorale.setConfirmMdp(passwordEncoder.encode(personneMorale.getConfirmMdp()));
//        }

//        repository.save(credential);
//        return "User added to the system";
//    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }




    public UserCredential validateUser(AuthRequest user) {
        UserCredential userCredential = repository.findByEmail(user.getEmail());
        if (userCredential != null && passwordEncoder.matches(user.getPassword(), userCredential.getPassword())) {
            return userCredential;
        } else {
            return null;
        }
    }

}
