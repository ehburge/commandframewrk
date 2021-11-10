package co.ex.frmwrk.mapping;

import cmd.impl.AppThingCommandSave;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppThingCommandSaveThingDtoSaveMapper {
  AppThingCommandSaveThingDtoSaveMapper INSTANCE = Mappers.getMapper(AppThingCommandSaveThingDtoSaveMapper.class);

  ThingDtoSave appThingCommandSaveToThingDtoSave(AppThingCommandSave appThingCommandSave);
}
