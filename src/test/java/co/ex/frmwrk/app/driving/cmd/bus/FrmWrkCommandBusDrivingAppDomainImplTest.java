package co.ex.frmwrk.app.driving.cmd.bus;

import co.ex.frmwrk.gateway.ThingDto;
import co.ex.app.driven.gateway.impl.CommandHandlerDrivenDomainCreateThingImpl;
import co.ex.app.driven.gateway.impl.CommandHandlerDrivenDomainSaveThingImpl;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.domain.cmd.impl.DomainThingCommandSave;
import co.ex.frmwrk.gateway.impl.CommandHandlerDrivenFrmTestingImpl;
import co.ex.frmwrk.guice.injector.FrmWrkApp;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FrmWrkCommandBusDrivingAppDomainImplTest {

  private static Injector injector;

  CommandBusDrivingApp commandBusDrivingApp;

  @BeforeAll
  static void setupClass() {
    injector = FrmWrkApp.getInjector();
    //        Guice.createInjector(
    //            new AbstractModule() {
    //              @Override
    //              protected void configure() {
    //
    //                MapBinder<Class, CommandHandlerDrivingDomain> commandHandlerDrivenMapBinder =
    //                    MapBinder.newMapBinder(
    //                        binder(), Class.class, CommandHandlerDrivingDomain.class);
    //                commandHandlerDrivenMapBinder
    //                    .addBinding(CreateThingCommand.class)
    //                    .to(Key.get(new TypeLiteral<CommandHandlerDrivingDomainImpl>() {}));
    //                commandHandlerDrivenMapBinder
    //                    .addBinding(SaveThingCommand.class)
    //                    .to(Key.get(new TypeLiteral<CommandHandlerDrivingDomainImpl>() {}));
    //
    //                bind(CommandBusDrivenDomain.class).to(CommandBusDrivenDomainImpl.class);
    //
    //                //                MapBinder<Class, CommandBusDrivenDomain>
    //                // commandBusDrivenDomainMapBinder =
    //                //                    MapBinder.newMapBinder(binder(), Class.class,
    //                // CommandBusDrivenDomain.class);
    //                //                commandBusDrivenDomainMapBinder
    //                //                    .addBinding(CreateThingCommand.class)
    //                //                    .to(Key.get(new
    // TypeLiteral<CommandBusDrivenDomainImpl>()
    //                // {}));
    //                //                commandBusDrivenDomainMapBinder
    //                //                    .addBinding(SaveThingCommand.class)
    //                //                    .to(Key.get(new
    // TypeLiteral<CommandBusDrivenDomainImpl>()
    //                // {}));
    //
    //                MapBinder<Class, CommandHandlerDrivenDomain>
    // commandHandlerDrivenDomainMapBinder =
    //                    MapBinder.newMapBinder(binder(), Class.class,
    // CommandHandlerDrivenDomain.class);
    //                commandHandlerDrivenDomainMapBinder
    //                    .addBinding(CreateThingCommand.class)
    //                    .to(Key.get(new TypeLiteral<CommandHandlerDrivenDomainCreateThingImpl>()
    // {}));
    //                commandHandlerDrivenDomainMapBinder
    //                    .addBinding(SaveThingCommand.class)
    //                    .to(Key.get(new TypeLiteral<CommandHandlerDrivenDomainSaveThingImpl>()
    // {}));
    //
    //                bind(CommandBusDrivingApp.class).to(CommandBusDrivingAppImpl.class);
    //
    //                bind(CommandBusDrivenDomain.class).to(CommandBusDrivenDomainImpl.class);
    //
    //                bind(CreateThingDtoFactory.class).to(CreateThingDtoFactoryImpl.class);
    //                bind(SaveThingDtoFactory.class).to(SaveThingDtoFactoryImpl.class);
    //                bind(SaveThingCommandMapping.class)
    //                    .to(SaveThingCommandBoundaryMappingImpl.class);
    //                bind(SaveThingCommandToThingDtoConverter.class)
    //                    .to(SaveThingCommandToThingDtoConverterImpl.class);
    //                bind(CommandHandlerDrivenFrm.class).to(CommandHandlerDrivenFrmTestingImpl.class);
    //              }
    //            });
  }

  @BeforeEach
  void setUp() {
    commandBusDrivingApp = injector.getInstance(CommandBusDrivingApp.class);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void executeCreate() {
    CommandHandlerDrivenFrmTestingImpl mockCreate = injector.getInstance(CommandHandlerDrivenFrmTestingImpl.class);

    CommandHandlerDrivenDomainCreateThingImpl chCrtThing  = injector.getInstance(CommandHandlerDrivenDomainCreateThingImpl.class);

    chCrtThing.setThingOutsideAdapter(mockCreate);

    DomainThingCommandSave domainThingCommandSave =
        DomainThingCommandSave.builder().thingNbr(123L).price(new BigDecimal("123.45")).build();
    commandBusDrivingApp.execute(domainThingCommandSave);

    ThingDto thingDto = mockCreate.getThingDtoMapDelegate().get(domainThingCommandSave.getThingNbr());

    assertNotNull(thingDto);
  }

  @Test
  void executeSave() {
    CommandHandlerDrivenFrmTestingImpl mockSave = injector.getInstance(CommandHandlerDrivenFrmTestingImpl.class);

    CommandHandlerDrivenDomainSaveThingImpl chSaveThing  = injector.getInstance(CommandHandlerDrivenDomainSaveThingImpl.class);

    chSaveThing.setThingAccessAdapter(mockSave);

    DomainThingCommandSave domainThingCommandSave =
            DomainThingCommandSave.builder().thingNbr(123L).price(new BigDecimal("123.45")).build();
    commandBusDrivingApp.execute(domainThingCommandSave);

    ThingDto thingDto = mockSave.getThingDtoMapDelegate().get(domainThingCommandSave.getThingNbr());

    assertNotNull(thingDto);
  }

  @Test
  void executeCreateAndSave() {
    CommandHandlerDrivenFrmTestingImpl mockAccessAdapter =
        (CommandHandlerDrivenFrmTestingImpl) injector.getInstance(CommandHandlerDrivenFrmTestingImpl.class);

    CommandHandlerDrivenDomainCreateThingImpl chCrtThing  = injector.getInstance(CommandHandlerDrivenDomainCreateThingImpl.class);
    chCrtThing.setThingOutsideAdapter(mockAccessAdapter);

    CommandHandlerDrivenDomainSaveThingImpl chSaveThing  = injector.getInstance(CommandHandlerDrivenDomainSaveThingImpl.class);
    chSaveThing.setThingAccessAdapter(mockAccessAdapter);

    DomainThingCommandSave domainThingCommandSave =
        DomainThingCommandSave.builder().thingNbr(123L).price(new BigDecimal("123.45")).build();
    commandBusDrivingApp.execute(domainThingCommandSave);

    DomainThingCommandSave domainThingCommandSave1 =
            DomainThingCommandSave.builder().thingNbr(124L).price(new BigDecimal("124.45")).build();
    commandBusDrivingApp.execute(domainThingCommandSave1);

    Map<Long, ThingDto> saveMap = mockAccessAdapter.getThingDtoMapDelegate().getThingDtoMap();

    assertEquals(2, saveMap.size());

    ThingDto thingDto = saveMap.get(domainThingCommandSave1.getThingNbr());

    assertNotNull(thingDto);
  }
}
