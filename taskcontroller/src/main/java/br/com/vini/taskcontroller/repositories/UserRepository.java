package br.com.vini.taskcontroller.repositories;

import br.com.vini.taskcontroller.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String> {
    UserDetails findByLogin(String login);
}
