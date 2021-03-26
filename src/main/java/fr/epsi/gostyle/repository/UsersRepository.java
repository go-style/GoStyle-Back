package fr.epsi.gostyle.repository;

import fr.epsi.gostyle.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, String> {

    Optional<User> findByUsermail(String usermail);
}
