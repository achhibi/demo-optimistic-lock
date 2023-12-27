package amch.demo;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;

import java.util.Optional;

public interface DataRepository extends CrudRepository<DataEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT e FROM DataEntity e WHERE e.id = :id")
    Optional<DataEntity> findByIdWithOptimisticLock(@Param("id") Long id);
}