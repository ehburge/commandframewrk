package co.ex.frmwrk.gateway.jpa;

import org.springframework.data.repository.CrudRepository;

public interface ThingRepository extends CrudRepository<ThingEntity, Long> {

  ThingEntity findDistinctByThingNbr(Long thingNbr);
}
