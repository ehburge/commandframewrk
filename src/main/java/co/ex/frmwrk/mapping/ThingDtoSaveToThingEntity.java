package co.ex.frmwrk.mapping;

import cmd.impl.AppThingCommandSave;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.jpa.ThingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThingDtoSaveToThingEntity {
  ThingDtoSaveToThingEntity INSTANCE = Mappers.getMapper(ThingDtoSaveToThingEntity.class);

  ThingEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);
}
