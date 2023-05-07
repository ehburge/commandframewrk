package co.ex.frmwrk.app.driven.cmd.handler;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppThingCommand000;
import co.ex.app.config.AppMapBeans;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.app.model.AppThingComments;
import co.ex.app.model.AppThingPart;
import co.ex.app.model.AppThingParts;
import co.ex.domain.config.SetupMapBeans;
import co.ex.frmwrk.driven.bus.impl.CommandBusDrivenFrmAdapterImpl;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandHandlerDrivenAppAdapterImplTest {

  static AppMapBeans appMapBeans;
  static SetupMapBeans setupMapBeans;

  static AppSetupMapBeans appSetupMapBeans;
  AppThingComments comments;
  AppThingParts appParts;

  @BeforeAll
  static void setupAll() {

    setupMapBeans = new SetupMapBeans(null);
    setupMapBeans.setup();
    appMapBeans = new AppMapBeans();
    appMapBeans.postConstruct();
    appSetupMapBeans = new AppSetupMapBeans(appMapBeans, setupMapBeans);
    appSetupMapBeans.setup();
  }

  @BeforeEach
  void setUp() {
    comments =
        AppThingComments.builder()
            .comments(new ArrayList<>(Arrays.asList("Larry", "Moe", "Curly", "Schemp")))
            .build();
    AppThingPart thingPart1 = AppThingPart.builder().partId("1").qty(2).build();
    AppThingPart thingPart2 = AppThingPart.builder().partId("2").qty(3).build();
    AppThingPart thingPart3 = AppThingPart.builder().partId("3").qty(2).build();
    appParts =
        AppThingParts.builder()
            .parts(new ArrayList<>(Arrays.asList(thingPart1, thingPart2, thingPart3)))
            .build();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void handle() {

    Map<Class<?>, CommandHandlerDrivenFrm> chDrivenFrmMap = new HashMap<>();
    CommandHandlerDrivenFrmTesting commandHandlerDrivenFrmTesting =
        new CommandHandlerDrivenFrmTesting();
    chDrivenFrmMap.put(ThingDtoSave000.class, commandHandlerDrivenFrmTesting);

    Map<Class<? extends AppCommand>, CommandBusDrivenFrm> cbDrivenFrmMap = new HashMap<>();
    AppThingCommandSaveThingDtoSaveMapper saveThingDtoSaveMapper =
        new AppThingCommandSaveThingDtoSaveMapperImpl();

    CommandBusDrivenFrm commandBusDrivenFrm =
        new CommandBusDrivenFrmAdapterImpl(chDrivenFrmMap, saveThingDtoSaveMapper);
    cbDrivenFrmMap.put(AppThingCommand000.class, commandBusDrivenFrm);

    appSetupMapBeans.putCommandBusDrivenFrm(cbDrivenFrmMap);

    AppThingCommand000 appThingCommandV1 =
        AppThingCommand000.builder().thingNbr(1L).comments(comments).parts(appParts).build();

    CommandBusDrivingApp commandBusDrivingApp = appSetupMapBeans.getCommandBusDrivingApp();
    commandBusDrivingApp.perform(appThingCommandV1);

    assertEquals(1, commandHandlerDrivenFrmTesting.getThingDtoSave().getThingNbr());
    assertEquals(
        4, commandHandlerDrivenFrmTesting.getThingDtoSave().getComments().getComments().size());
    assertEquals(3, commandHandlerDrivenFrmTesting.getThingDtoSave().getParts().getParts().size());
  }

  class CommandHandlerDrivenFrmTesting implements CommandHandlerDrivenFrm {

    private ThingDtoSave000 thingDtoSave000;

    @Override
    public void handle(ThingDto thingDto) {
      thingDtoSave000 = (ThingDtoSave000) thingDto;
    }

    public ThingDtoSave000 getThingDtoSave() {
      return thingDtoSave000;
    }
  }
}
