package co.ex.frmwrk.gateway.persist;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {
}
