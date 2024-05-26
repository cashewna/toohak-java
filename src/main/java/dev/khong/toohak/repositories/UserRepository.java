package dev.khong.toohak.repositories;

import dev.khong.toohak.models.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    void deleteAll();

    @Query("SELECT * FROM users WHERE email = :email")
    public Optional<User> findByEmail(String email);

    @Modifying
    @Query("INSERT INTO users (email, password, name_first, name_last) VALUES (:email, :password, :nameFirst, :nameLast)")
    public void createUser(String email, String password, String nameFirst, String nameLast);
}
