package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.event.jpa.EventEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {
}
