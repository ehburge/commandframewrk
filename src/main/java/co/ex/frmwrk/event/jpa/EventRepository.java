package co.ex.frmwrk.event.jpa;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {

  EventEntity findDistinctByThingNbr(Long thingNbr);
}
