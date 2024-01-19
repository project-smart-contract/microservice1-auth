package ma.fstt.microservice1auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String nom;
    private String prenom;
    private String password;
    private String numeroTelephone;
    private String nomSoc;
    private String numSocie;
    private String userType;
    private String numPermis;
    private String cin;
    private String adresse;
    private String email;
    private String dateNaissance;

//    public String getUsername() {
//        return this.username;
//    }

    public String getNumeroTelephone() {
        return this.numeroTelephone;
    }

    public String getUserType() {
        return this.userType;
    }

    public String getNomSoc() {
        return this.nomSoc;
    }

    public String getNumSocie() {
        return this.numSocie;
    }

    public String getNom() {
        return this.nom;
    }
    public String getPrenom() {
        return this.prenom;
    }

    public String getNumPermis() {
        return this.numPermis;
    }

    public String getcin() {
        return this.cin;
    }

    public String getAdresse() {
        return this.adresse ;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDateNaissance() {
        return this.dateNaissance;
    }

}
