package amch.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DemoOptimisticLockApplicationTests {

    @Autowired
    DataRepository repository;
    @Autowired
    DataService service;

    @Test
    void contextLoads() {
    }

    @Test
//	@Transactional(Transactional.TxType.REQUIRES_NEW)
//	@Rollback(false)
    void testOptimisticLockException() {
        DataEntity entity = new DataEntity();
        entity.setData("Données initiales");
        repository.save(entity);

        DataEntity entityTransaction1 = service.findByIdWithOptimisticLock(entity.getId());
        DataEntity entityTransaction2 = service.findByIdWithOptimisticLock(entity.getId());

        service.updateEntityWithOptimisticLock(entityTransaction1.getId(), "Nouvelles données 1");
        service.updateEntityWithOptimisticLock(entityTransaction2.getId(), "Nouvelles données 2");
        entityTransaction1.setData("-----");
        assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
            repository.save(entityTransaction1);
            repository.save(entityTransaction2);
        });
    }
}
