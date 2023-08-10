package co.ex.frmwrk.mapping;

import co.ex.app.cmd.impl.AppThingCommand000;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AppThingCommandSaveThingDtoSaveMapper {

  ThingDtoSave000 appThingCommandSaveToThingDtoSave(AppThingCommand000 appThingCommand000);
}
