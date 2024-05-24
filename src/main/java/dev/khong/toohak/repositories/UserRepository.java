package dev.khong.toohak.repositories;

import dev.khong.toohak.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
