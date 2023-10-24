package co.ex.frmwrk.frmin.rest;

import co.ex.app.cmd.impl.AppCommandSave;
import co.ex.frmwrk.frmin.cmd.impl.FrmInCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FrmInCommandToAppCommandSaveMapper {

  AppCommandSave frmInCommandToAppCommand(FrmInCommandSave frmInCommandCommand);
}
