package ma.fstt.microservice1auth.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.fstt.microservice1auth.constant.AppConstant;
import ma.fstt.microservice1auth.dto.AuthRequest;
import ma.fstt.microservice1auth.entity.*;
import ma.fstt.microservice1auth.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public UserCredential saveUser(UserCredential credential) {

        credential.setPassword(passwordEncoder.encode(credential.getPassword()));

        if (credential instanceof PersonnePhysique) {
            PersonnePhysique personnePhysique = (PersonnePhysique) credential;
            // Traitez les données spécifiques à PersonnePhysique
            System.out.println("Nom: " + personnePhysique.getNom());
            System.out.println("Prénom: " + personnePhysique.getPrenom());
            System.out.println("Numéro de téléphone: " + personnePhysique.getNumeroTelephone());
            System.out.println("CIN: " + personnePhysique.getCin());
            System.out.println("Adresse: " + personnePhysique.getAdresse());
//            System.out.println("email " + personnePhysique.getEmail());
            System.out.println("Numéro de permis: " + personnePhysique.getNumPermis());

        } else if (credential instanceof PersonneMorale) {
            PersonneMorale personneMorale = (PersonneMorale) credential;
            // Traitez les données spécifiques à PersonneMorale
            System.out.println("Nom de la société: " + personneMorale.getNomSoc());
            System.out.println("Numéro de la société: " + personneMorale.getNumSocie());

        }
        UserCredential savedUser = repository.save(credential);
        // Créez votre message
        String message = createMessage(savedUser);

        // Envoyez le message à Kafka
        kafkaTemplate.send(AppConstant.CLIENT_INFO, message);

        return savedUser;
    }


    private String createMessage(UserCredential credential) {
        // Convertissez votre objet credential en JSON
        // Vous pouvez utiliser la bibliothèque de votre choix pour cela
        // Par exemple, vous pouvez utiliser Jackson si vous l'utilisez déjà dans votre projet
        // Assurez-vous d'ajouter la dépendance appropriée à votre pom.xml
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(credential);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert user credential to JSON", e);
        }
//        return credential;
    }
    

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }




    public UserCredential validateUser(AuthRequest user) {
        UserCredential userCredential = UserCredentialRepository.findByEmail(user.getEmail());
        if (userCredential != null && passwordEncoder.matches(user.getPassword(), userCredential.getPassword())) {
            return userCredential;
        } else {
            return null;
        }
    }

}
