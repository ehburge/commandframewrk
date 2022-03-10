package co.ex.frmwrk.mapping;

import co.ex.app.cmd.impl.AppThingCommandSave;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AppThingCommandSaveThingDtoSaveMapper {

  ThingDtoSave appThingCommandSaveToThingDtoSave(AppThingCommandSave appThingCommandSave);
}
