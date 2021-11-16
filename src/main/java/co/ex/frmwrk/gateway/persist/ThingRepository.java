package co.ex.frmwrk.gateway.persist;

import org.springframework.data.repository.CrudRepository;

public interface ThingRepository extends CrudRepository<ThingEntity, Long> {

  ThingEntity findDistinctByThingNbr(Long thingNbr);
}
