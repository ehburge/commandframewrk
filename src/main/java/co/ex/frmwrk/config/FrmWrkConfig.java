package co.ex.frmwrk.config;

import cmd.impl.AppThingCommandSave;
import co.ex.app.AppInjector2;
import co.ex.app.driven.cmd.handler.CommandBusDrivenFrm;
import co.ex.app.driven.cmd.handler.CommandHandlerDrivenApp;
import co.ex.app.driven.cmd.handler.impl.CommandHandlerDrivenAppImpl;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.driven.bus.impl.CommandBusDrivenFrmImpl;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.jpa.impl.CommandHandlerDrivenFrmSaveJPA;
import co.ex.frmwrk.gateway.msg.impl.CommandHandlerDrivenFrmSaveMsg;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FrmWrkConfig {

  static final Injector injector = Guice.createInjector(new AppInjector2());

  @Bean
  public CommandHandlerDrivenApp attachSaveThingAccessAdapter(Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap) {
    CommandHandlerDrivenAppImpl commandHandlerDrivenAppImpl =
        injector.getInstance(CommandHandlerDrivenAppImpl.class);

    commandHandlerDrivenAppImpl.setCommandBusDrivenFrmMap(commandBusDrivenFrmMap);

    return commandHandlerDrivenAppImpl;
  }

  @Bean
  public Map<Class, CommandBusDrivenFrm> makeThingAccessAdapterMsgMap(
      CommandBusDrivenFrm commandBusDrivenFrm) {
    Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap = new HashMap<>();
    commandBusDrivenFrmMap.put(AppThingCommandSave.class, commandBusDrivenFrm);
    return commandBusDrivenFrmMap;
  }

  @Bean
  public Map<Class, CommandHandlerDrivenFrm> commandBusDrivenFrm(
      CommandHandlerDrivenFrmSaveMsg commandHandlerDrivenFrmSaveMsg) {
    Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap = new HashMap<>();
    commandHandlerDrivenFrmMap.put(ThingDtoSave.class, commandHandlerDrivenFrmSaveMsg);

    return commandHandlerDrivenFrmMap;
  }

  @Bean
  public Map<Class, CommandHandlerDrivenFrmSaveJPA> makeThingAccessAdapterPersistMap(
      CommandHandlerDrivenFrmSaveJPA outsideAdapterSaveJPA,
      CommandHandlerDrivenFrmSaveJPA saveThingAccessAdapterJPA) {
    Map<Class, CommandHandlerDrivenFrmSaveJPA> thingAccessAdapterPersistMap = new HashMap<>();
    thingAccessAdapterPersistMap.put(AppThingCommandSave.class, outsideAdapterSaveJPA);
    thingAccessAdapterPersistMap.put(ThingDtoSave.class, saveThingAccessAdapterJPA);
    return thingAccessAdapterPersistMap;
  }

  @Bean
  public CommandBusDrivingApp makeCommandBusDriving() {
    CommandBusDrivingApp commandBusDrivingApp = injector.getInstance(CommandBusDrivingApp.class);

    return commandBusDrivingApp;
  }
}
