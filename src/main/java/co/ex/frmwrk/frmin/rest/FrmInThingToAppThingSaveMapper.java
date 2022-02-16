package co.ex.frmwrk.frmin.rest;

import com.ex.thing.cmd.impl.AppThingCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FrmInThingToAppThingSaveMapper {

  AppThingCommandSave frmInThingToAppThing(FrmInThingCommand frmInThingCommand);
}
