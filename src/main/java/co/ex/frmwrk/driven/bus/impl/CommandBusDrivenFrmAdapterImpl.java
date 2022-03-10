package co.ex.frmwrk.driven.bus.impl;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppThingCommandSave;
import co.ex.app.driven.cmd.handler.impl.CommandBusDrivenFrm;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommandBusDrivenFrmAdapterImpl implements CommandBusDrivenFrm {

  private final AppThingCommandSaveThingDtoSaveMapper appThingCommandSaveThingDtoSaveMapper;
  private final Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap;

  @Autowired
  public CommandBusDrivenFrmAdapterImpl(
      Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap,
      AppThingCommandSaveThingDtoSaveMapper appThingCommandSaveThingDtoSaveMapper) {
    this.commandHandlerDrivenFrmMap = commandHandlerDrivenFrmMap;
    this.appThingCommandSaveThingDtoSaveMapper = appThingCommandSaveThingDtoSaveMapper;
  }

  @Override
  public void perform(AppCommand appCommand) {
    //    ThingOutDto thingDtoSave =
    //
    // AppThingCommandSaveThingDtoSaveMapper.INSTANCE.appThingCommandSaveToThingDtoSave();.appThingCommandSaveToThingDtoSave(
    //            (AppThingCommandSave) appCommand);
    ThingDtoSave thingDtoSave =
        appThingCommandSaveThingDtoSaveMapper.appThingCommandSaveToThingDtoSave(
            (AppThingCommandSave) appCommand);
    CommandHandlerDrivenFrm drivenApp = commandHandlerDrivenFrmMap.get(thingDtoSave.getClass());
    drivenApp.handle(thingDtoSave);
  }
}
