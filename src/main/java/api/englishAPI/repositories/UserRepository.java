package api.englishAPI.repositories;


import api.englishAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<Users, UUID> {
    boolean existsByEmail(String email);

    Users findByEmail(String email);
}
