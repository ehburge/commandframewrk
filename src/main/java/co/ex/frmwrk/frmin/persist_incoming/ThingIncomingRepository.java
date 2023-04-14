package co.ex.frmwrk.frmin.persist_incoming;

import co.ex.frmwrk.gateway.persist.ThingEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingIncomingRepository extends JpaRepository<ThingIncoming, Long> {

  List<ThingIncoming> findByThingNbrOrderByDttm(Long thingNbr);
}
