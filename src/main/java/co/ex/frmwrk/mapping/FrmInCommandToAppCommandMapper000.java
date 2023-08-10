package co.ex.frmwrk.mapping;

import co.ex.app.cmd.impl.AppThingCommand000;
import co.ex.frmwrk.frmin.cmd.impl.FrmInThingCommand000;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FrmInCommandToAppCommandMapper000 {

  AppThingCommand000 frmInThingCommandToAppCommand(FrmInThingCommand000 frmInThingCommand000);
}
