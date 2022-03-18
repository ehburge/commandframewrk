package co.ex.frmwrk.gateway.persist.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import co.ex.frmwrk.mapping.ThingDtoSave_ThingEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersistThingDto {
  Logger LOGGER = LoggerFactory.getLogger( PersistThingDto.class );

  private final ThingDtoSave_ThingEntity thingDtoSaveThingEntity;
  private final ThingEntityRepository thingRepository;

  public ThingEntity persist(ThingDtoSave thingDtoSave) {
    LOGGER.debug(
            "PersistThingDto.persist()".concat(System.lineSeparator()),
            JsonMapper.toJson(thingDtoSave));

    ThingEntity thingEntity = thingDtoSaveThingEntity.thingDtoSaveToThingEntity(thingDtoSave);

    return thingRepository.save(thingEntity);
  }
}
