package co.ex.frmwrk.gateway.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThingEntityRepository extends JpaRepository<ThingEntity, Long> {

  List<ThingEntity> findByThingNbrOrderByDttm(Long thingNbr);
}
