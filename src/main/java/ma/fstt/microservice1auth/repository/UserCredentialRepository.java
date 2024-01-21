package ma.fstt.microservice1auth.repository;

import ma.fstt.microservice1auth.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
    static UserCredential findByEmail(String email) {
        return null;
    }
    //void save(UserCredential credential);
    //Optional<UserCredential> findByName(String username);
}

