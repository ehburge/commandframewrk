package co.ex.frmwrk.driven.bus.impl;

import co.ex.app.driven.cmd.handler.CommandBusDrivenFrm;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import com.ex.thing.cmd.AppCommand;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommandBusDrivenFrmAdapterImpl implements CommandBusDrivenFrm {

  static AppThingCommandSaveThingDtoSaveMapper appThingCommandSaveThingDtoSaveMapper;

  private final Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap;

  @Autowired
  public CommandBusDrivenFrmAdapterImpl(
      Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap) {
    this.commandHandlerDrivenFrmMap = commandHandlerDrivenFrmMap;
  }

  @Override
  public void perform(AppCommand appCommand) {
//    ThingDtoSave thingDtoSave =
//        AppThingCommandSaveThingDtoSaveMapper.INSTANCE.appThingCommandSaveToThingDtoSave();.appThingCommandSaveToThingDtoSave(
//            (AppThingCommandSave) appCommand);
    ThingDtoSave thingDtoSave = appThingCommandSaveThingDtoSaveMapper
        .appThingCommandSaveToThingDtoSave((AppThingCommandSave) appCommand);
    CommandHandlerDrivenFrm drivenApp = commandHandlerDrivenFrmMap.get(thingDtoSave.getClass());
    drivenApp.handle(thingDtoSave);
  }
}
