package br.com.vini.taskcontroller.repositories;

import br.com.vini.taskcontroller.entity.GymImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
public interface GymImageRepository extends MongoRepository<GymImageEntity, String> {

    @Query("{'date': { $gte: ?0, $lt: ?1 } }")
    List<GymImageEntity> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
