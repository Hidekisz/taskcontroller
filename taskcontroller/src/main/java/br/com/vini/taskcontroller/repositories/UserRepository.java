package br.com.vini.taskcontroller.repositories;

import br.com.vini.taskcontroller.dto.UserGetAllResponse;
import br.com.vini.taskcontroller.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends MongoRepository<UserEntity,String> {
    UserDetails findByLogin(String login);

    UserEntity findByUserId(String userId);

    @Query(value = "{}", fields = "{'password' : 0}")
    List<UserGetAllResponse> findAllNoPassword();
}
