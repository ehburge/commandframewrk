package co.ex.frmwrk.gateway.jpa.impl;

import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.jpa.ThingEntity;
import co.ex.frmwrk.gateway.jpa.ThingRepository;
import co.ex.frmwrk.mapping.ThingDtoSaveToThingEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("jpa")
public class CommandHandlerDrivenFrmSaveJPA implements CommandHandlerDrivenFrm<ThingDtoSave> {

  private final ThingRepository thingRepository;

  @Override
  public void handle(ThingDtoSave thingDtoSave) {

    ThingEntity thingEntityLookup =
        thingRepository.findDistinctByThingNbr(thingDtoSave.getThingNbr());

    if (thingEntityLookup == null) {
      ThingEntity thingEntity =
          ThingDtoSaveToThingEntity.INSTANCE.thingDtoSaveToThingEntity(thingDtoSave);

      thingRepository.save(thingEntity);
    }
  }
}
