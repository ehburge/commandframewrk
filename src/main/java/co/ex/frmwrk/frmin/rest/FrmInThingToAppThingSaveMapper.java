package co.ex.frmwrk.frmin.rest;

import co.ex.app.cmd.impl.AppThingCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FrmInThingToAppThingSaveMapper {

  AppThingCommandSave frmInThingToAppThing(FrmInThingCommand frmInThingCommand);
}
