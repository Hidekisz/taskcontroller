package br.com.vini.taskcontroller.repositories;

import br.com.vini.taskcontroller.entity.GymImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GymImageRepository extends MongoRepository<GymImageEntity, String> {
}
