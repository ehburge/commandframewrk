package co.ex.frmwrk.gateway.persist.impl;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingRepository;
import co.ex.frmwrk.mapping.ThingDtoSaveToThingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersistThingDto {

  static ThingDtoSaveToThingEntity thingDtoSaveToThingEntity = ThingDtoSaveToThingEntity.INSTANCE;

  private final ThingRepository thingRepository;

  public ThingDtoSave persist(ThingDtoSave thingDtoSave) {

    ThingEntity thingEntityLookup =
        thingRepository.findDistinctByThingNbr(thingDtoSave.getThingNbr());

    ThingEntity thingEntity = null;
    if (thingEntityLookup == null) {
      thingEntity = thingDtoSaveToThingEntity.thingDtoSaveToThingEntity(thingDtoSave);
    }

    return thingDtoSaveToThingEntity.thingEntityToThingDtoSave(thingRepository.save(thingEntity));
  }
}
