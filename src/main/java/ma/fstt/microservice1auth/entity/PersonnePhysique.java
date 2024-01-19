package ma.fstt.microservice1auth.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PHYSIQUE")
public class PersonnePhysique extends UserCredential {

    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String dateNaissance;

    private String adresse;
    private String cin;
    private String numPermis;

    public void setcin(String cin)
    {
        this.cin = cin;
    }
    public void setnumPermis(String numPermis) {
        this.numPermis = numPermis;
    }

    public void setadresse(String adresse) {
        this.adresse = adresse;
    }



    public void setdateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

}
