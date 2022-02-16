package co.ex.frmwrk.config;

import co.ex.app.AppInjectorFrmWrk;
import co.ex.app.driven.cmd.handler.CommandHandlerDrivenApp;
import co.ex.app.driven.cmd.handler.impl.CommandBusDrivenFrm;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.msg.impl.CommandHandlerDrivenFrmSaveMsgImpl;
import co.ex.frmwrk.gateway.persist.impl.PersistThingDto;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FrmWrkConfig {

  static final Injector injector = Guice.createInjector(new AppInjectorFrmWrk());

  // Driving

  @Bean
  public CommandBusDrivingApp makeCommandBusDriving() {
    CommandBusDrivingApp commandBusDrivingApp = injector.getInstance(CommandBusDrivingApp.class);

    return commandBusDrivingApp;
  }

  // Driven

  @Bean
  public Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap(
      CommandBusDrivenFrm commandBusDrivenFrm) {
    Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap = new HashMap<>();
    commandBusDrivenFrmMap.put(AppThingCommandSave.class, commandBusDrivenFrm);

    return commandBusDrivenFrmMap;
  }

  @Bean
  public CommandHandlerDrivenApp attachSaveThingAccessAdapter(
      Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap) {
    CommandHandlerDrivenApp commandHandlerDrivenAppImpl =
        injector.getInstance(CommandHandlerDrivenApp.class);

    commandHandlerDrivenAppImpl.setCommandBusDrivenFrmMap(commandBusDrivenFrmMap);

    return commandHandlerDrivenAppImpl;
  }

  @Bean
  public Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap(
      CommandHandlerDrivenFrmSaveMsgImpl commandHandlerDrivenFrmSaveMsgImpl) {
    Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap = new HashMap<>();
    commandHandlerDrivenFrmMap.put(ThingDtoSave.class, commandHandlerDrivenFrmSaveMsgImpl);

    return commandHandlerDrivenFrmMap;
  }

  @Bean
  public Map<Class, PersistThingDto> makeThingAccessAdapterPersistMap(
      PersistThingDto outsideAdapterSaveJPA, PersistThingDto saveThingAccessAdapterJPA) {

    Map<Class, PersistThingDto> thingAccessAdapterPersistMap = new HashMap<>();
    thingAccessAdapterPersistMap.put(AppThingCommandSave.class, outsideAdapterSaveJPA);
    thingAccessAdapterPersistMap.put(ThingDtoSave.class, saveThingAccessAdapterJPA);

    return thingAccessAdapterPersistMap;
  }
}
