package co.ex.frmwrk.config;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppThingCommand000;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.driving.bus.CommandBusDrivingFrmAdapter;
import co.ex.frmwrk.driving.handler.CommandHandlerDrivingFrmAdapter;
import co.ex.frmwrk.frmin.cmd.FrmInThingCommand;
import co.ex.frmwrk.frmin.cmd.impl.FrmInThingCommand000;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import co.ex.frmwrk.gateway.impl.ThingDtoSave100;
import co.ex.frmwrk.send.bus.DtoSenderBus;
import co.ex.frmwrk.send.bus.impl.DtoSenderBusImpl;
import co.ex.frmwrk.send.handler.DtoSenderHandler;
import co.ex.frmwrk.send.handler.impl.DtoSenderHandler000Impl;
import co.ex.frmwrk.send.handler.impl.DtoSenderHandler100Impl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrmWrkConfig {

  private final BeanFactory beanFactory;

  private final AppSetupMapBeans appSetupMapBeans;

  public FrmWrkConfig(BeanFactory beanFactory, AppSetupMapBeans appSetupMapBeans) {
    this.beanFactory = beanFactory;
    this.appSetupMapBeans = appSetupMapBeans;
  }

  @Bean
  public Map<Class<?>, CommandBusDrivingFrmAdapter> makeCommandBusDrivingFrmAdapterMap(
      CommandBusDrivingFrmAdapter commandBusDrivingFrmAdapter) {

    Map<Class<?>, CommandBusDrivingFrmAdapter> commandBusDrivingFrmAdapterMap = new HashMap<>();
    commandBusDrivingFrmAdapterMap.put(FrmInThingCommand000.class, commandBusDrivingFrmAdapter);
    return commandBusDrivingFrmAdapterMap;
  }

  @Bean
  public Map<Class<? extends FrmInThingCommand>, CommandHandlerDrivingFrmAdapter>
      makeCommandHandlerDrivingFrmMap(CommandHandlerDrivingFrmAdapter handlerDrivingFrmAdapter) {

    Map<Class<? extends FrmInThingCommand>, CommandHandlerDrivingFrmAdapter> handlerDrivingFrmAdapterMap = new HashMap<>();
    handlerDrivingFrmAdapterMap.put(FrmInThingCommand000.class, handlerDrivingFrmAdapter);
    return handlerDrivingFrmAdapterMap;
  }

  @Bean
  public Map<Class<? extends AppCommand>, CommandBusDrivingApp> getAppCommandBusMap() {
    return appSetupMapBeans.getCommandBusDrivingAppMap();
  }

  @Bean
  public Map<Class<? extends AppCommand>, CommandBusDrivenFrm> makeCommandBusDrivenFrmMap(
      CommandBusDrivenFrm commandBusDrivenFrm) {

    Map<Class<? extends AppCommand>, CommandBusDrivenFrm> cbDrivenFrm_map = new HashMap<>();
    cbDrivenFrm_map.put(AppThingCommand000.class, commandBusDrivenFrm);

    return cbDrivenFrm_map;
  }

  @Bean
  public Map<Class<?>, CommandHandlerDrivenFrm> makeCommandHandlerDrivenFrmMap(
      CommandHandlerDrivenFrm commandHandlerDrivenFrm) {

    Map<Class<?>, CommandHandlerDrivenFrm> chDrivenFrm_map = new HashMap<>();
    chDrivenFrm_map.put(ThingDtoSave000.class, commandHandlerDrivenFrm);

    return chDrivenFrm_map;
  }

  @Bean
  public Map<Class<? extends ThingDto>, DtoSenderBus> makeDtoSenderBusMap() {
    Map<Class<? extends ThingDto>, DtoSenderBus> cbMap = new HashMap<>();
    DtoSenderBus dtoSenderBus = beanFactory.getBean(DtoSenderBusImpl.class);
    cbMap.put(ThingDtoSave000.class, dtoSenderBus);

    return cbMap;
  }

  @Bean
  public Map<Class<? extends ThingDto>, DtoSenderHandler> makeDtoSenderHandlerMap() {
    Map<Class<? extends ThingDto>, DtoSenderHandler> chMap = new HashMap<>();
    chMap.put(ThingDtoSave000.class, beanFactory.getBean(DtoSenderHandler000Impl.class));
    chMap.put(ThingDtoSave100.class, beanFactory.getBean(DtoSenderHandler100Impl.class));

    return chMap;
  }
}
