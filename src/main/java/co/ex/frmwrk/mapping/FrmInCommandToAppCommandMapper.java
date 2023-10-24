package co.ex.frmwrk.mapping;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppCommandSave;
import co.ex.frmwrk.frmin.cmd.impl.FrmInCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FrmInCommandToAppCommandMapper {

  AppCommandSave frmInCommandToAppCommand(FrmInCommandSave frmInCommandSave);
}
