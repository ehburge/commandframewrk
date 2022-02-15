package co.ex.frmwrk.gateway.persist.impl;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import co.ex.frmwrk.mapping.ThingDtoSave_ThingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersistThingDto {

  private final ThingDtoSave_ThingEntity thingDtoSaveThingEntity;
  private final ThingEntityRepository thingRepository;

  public ThingEntity persist(ThingDtoSave thingDtoSave) {

    ThingEntity thingEntity = thingDtoSaveThingEntity.thingDtoSaveToThingEntity(thingDtoSave);

    return thingRepository.save(thingEntity);
  }
}
