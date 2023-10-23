package co.ex.frmwrk.driven.bus.impl;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppCommandSave;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandBusDrivenFrmAdapterImpl implements CommandBusDrivenFrm {

  private final AppThingCommandSaveThingDtoSaveMapper appThingCommandSaveThingDtoSaveMapper;
  private final Map<Class<?>, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap;

  @Autowired
  public CommandBusDrivenFrmAdapterImpl(
      Map<Class<?>, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap,
      AppThingCommandSaveThingDtoSaveMapper appThingCommandSaveThingDtoSaveMapper) {
    this.commandHandlerDrivenFrmMap = commandHandlerDrivenFrmMap;
    this.appThingCommandSaveThingDtoSaveMapper = appThingCommandSaveThingDtoSaveMapper;
  }

  @Override
  public void perform(AppCommand appCommand) {
    DtoCommandSave dtoCommandSave =
        appThingCommandSaveThingDtoSaveMapper.appThingCommandSaveToThingDtoSave(
            (AppCommandSave) appCommand);
    CommandHandlerDrivenFrm drivenApp = commandHandlerDrivenFrmMap.get(dtoCommandSave.getClass());
    drivenApp.handle(dtoCommandSave);
  }
}
