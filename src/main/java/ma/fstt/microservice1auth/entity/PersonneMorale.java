package ma.fstt.microservice1auth.entity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("MORALE")
public class PersonneMorale extends UserCredential {

    private String nomSoc;
    private String numSocie;




}
