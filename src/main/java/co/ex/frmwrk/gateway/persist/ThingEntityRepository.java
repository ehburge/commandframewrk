package co.ex.frmwrk.gateway.persist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingEntityRepository extends JpaRepository<ThingEntity, Long> {

    ThingEntity findDistinctByThingNbr(Long thingNbr);
}