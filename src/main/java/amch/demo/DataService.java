package amch.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DataService {

    @Autowired
    private DataRepository  repository;

   @Transactional
    public void updateEntityWithOptimisticLock(Long entityId, String data) {
        DataEntity entity = repository.findByIdWithOptimisticLock(entityId).get();
        log.info(String.valueOf(entity));
        if (entity != null) {
            entity.setData(data);
        }
    }

    @Transactional
   public DataEntity findByIdWithOptimisticLock(Long id){
       return repository.findByIdWithOptimisticLock(id).get();
    }
}