package co.ex.frmwrk.config;

import co.ex.app.cmd.impl.AppThingCommandSave;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.impl.ThingDtoSave100;
import co.ex.frmwrk.gateway.ports.bus.DtoSenderBus;
import co.ex.frmwrk.gateway.ports.bus.impl.DtoSenderBusImpl;
import co.ex.frmwrk.gateway.ports.handler.DtoSenderHandler;
import co.ex.frmwrk.gateway.ports.handler.impl.DtoSenderHandler100Impl;
import co.ex.frmwrk.gateway.ports.handler.impl.DtoSenderHandlerImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FrmWrkConfig {

  private final BeanFactory beanFactory;

  public FrmWrkConfig(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  @Bean
  public Map<Class<?>, CommandBusDrivenFrm> commandBusDrivenFrmMap(
      CommandBusDrivenFrm commandBusDrivenFrm) {

    Map<Class<?>, CommandBusDrivenFrm> cbDrivenFrm_map = new HashMap<>();
    cbDrivenFrm_map.put(AppThingCommandSave.class, commandBusDrivenFrm);

    // setupMapBeans.putCommandBusDrivenFrm(cbDrivenFrm_map);
    return cbDrivenFrm_map;
  }

  @Bean
  public Map<Class<?>, CommandHandlerDrivenFrm> makeCommandHandlerDrivenFrmMap(
      CommandHandlerDrivenFrm commandHandlerDrivenFrm) {

    Map<Class<?>, CommandHandlerDrivenFrm> chDrivenFrm_map = new HashMap<>();
    chDrivenFrm_map.put(ThingDtoSave.class, commandHandlerDrivenFrm);

    return chDrivenFrm_map;
  }

  @Bean
  public Map<Class<?>, DtoSenderBus> makeDtoSenderBusMap() {
    Map<Class<?>, DtoSenderBus> cbMap = new HashMap<>();
    DtoSenderBus dtoSenderBus = beanFactory.getBean(DtoSenderBusImpl.class);
    cbMap.put(ThingDtoSave.class, dtoSenderBus);

    return cbMap;
  }

  @Bean
  public Map<Class<?>, DtoSenderHandler> makeDtoSenderHandlerMap() {
    Map<Class<?>, DtoSenderHandler> chMap = new HashMap<>();
    chMap.put(ThingDtoSave.class, beanFactory.getBean(DtoSenderHandlerImpl.class));
    chMap.put(ThingDtoSave100.class, beanFactory.getBean(DtoSenderHandler100Impl.class));

    return chMap;
  }
}
  //
  //  @Bean
  //  public CommandHandlerDrivenApp attachSaveThingAccessAdapter(
  //      Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap) {
  //    CommandHandlerDrivenApp commandHandlerDrivenAppImpl =
  //        injector.getInstance(CommandHandlerDrivenApp.class);
  //
  //    commandHandlerDrivenAppImpl.setCommandBusDrivenFrmMap(commandBusDrivenFrmMap);
  //
  //    return commandHandlerDrivenAppImpl;
  //  }
  //
  //  @Bean
  //  public Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap(
  //      CommandHandlerDrivenFrmSaveMsgImpl commandHandlerDrivenFrmSaveMsgImpl) {
  //    Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap = new HashMap<>();
  //    commandHandlerDrivenFrmMap.put(ThingDtoSave.class, commandHandlerDrivenFrmSaveMsgImpl);
  //
  //    return commandHandlerDrivenFrmMap;
  //  }
  //
  //  @Bean
  //  public Map<Class, PersistThingDto> makeThingAccessAdapterPersistMap(
  //      PersistThingDto outsideAdapterSaveJPA, PersistThingDto saveThingAccessAdapterJPA) {
  //
  //    Map<Class, PersistThingDto> thingAccessAdapterPersistMap = new HashMap<>();
  //    thingAccessAdapterPersistMap.put(AppThingCommandSave.class, outsideAdapterSaveJPA);
  //    thingAccessAdapterPersistMap.put(ThingDtoSave.class, saveThingAccessAdapterJPA);
  //
  //    return thingAccessAdapterPersistMap;
  //  }
