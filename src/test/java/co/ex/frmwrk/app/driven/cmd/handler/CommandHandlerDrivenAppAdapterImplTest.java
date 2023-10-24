package co.ex.frmwrk.app.driven.cmd.handler;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppCommandSave;
import co.ex.app.cmd.impl.AppCommandCommand000;
import co.ex.app.config.AppMapBeans;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.app.model.AppCommandComments;
import co.ex.app.model.AppCommandPart;
import co.ex.app.model.AppCommandParts;
import co.ex.domain.config.SetupMapBeans;
import co.ex.frmwrk.driven.bus.impl.CommandBusDrivenFrmAdapterImpl;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.Dto;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.mapping.AppCommandCommandSaveCommandDtoSaveMapper;
import co.ex.frmwrk.mapping.AppCommandCommandSaveCommandDtoSaveMapperImpl;
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
  AppCommandComments comments;
  AppCommandParts appParts;

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
        AppCommandComments.builder()
            .comments(new ArrayList<>(Arrays.asList("Larry", "Moe", "Curly", "Schemp")))
            .build();
    AppCommandPart commandPart1 = AppCommandPart.builder().partId("1").qty(2).build();
    AppCommandPart commandPart2 = AppCommandPart.builder().partId("2").qty(3).build();
    AppCommandPart commandPart3 = AppCommandPart.builder().partId("3").qty(2).build();
    appParts =
        AppCommandParts.builder()
            .parts(new ArrayList<>(Arrays.asList(commandPart1, commandPart2, commandPart3)))
            .build();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void handle() {

    Map<Class<?>, CommandHandlerDrivenFrm> chDrivenFrmMap = new HashMap<>();
    CommandHandlerDrivenFrmTesting commandHandlerDrivenFrmTesting =
        new CommandHandlerDrivenFrmTesting();
    chDrivenFrmMap.put(DtoCommandSave.class, commandHandlerDrivenFrmTesting);

    Map<Class<? extends AppCommand>, CommandBusDrivenFrm> cbDrivenFrmMap = new HashMap<>();
    AppCommandCommandSaveCommandDtoSaveMapper saveCommandDtoSaveMapper =
        new AppCommandCommandSaveCommandDtoSaveMapperImpl();

    CommandBusDrivenFrm commandBusDrivenFrm =
        new CommandBusDrivenFrmAdapterImpl(chDrivenFrmMap, saveCommandDtoSaveMapper);
    cbDrivenFrmMap.put(AppCommandCommand000.class, commandBusDrivenFrm);

    appSetupMapBeans.putCommandBusDrivenFrm(cbDrivenFrmMap);

    AppCommandSave appCommandSave =
            AppCommandSave.builder().commandNbr(1L).comments(comments).parts(appParts).build();

    CommandBusDrivingApp commandBusDrivingApp = appSetupMapBeans.getCommandBusDrivingApp();
    commandBusDrivingApp.perform(appCommandSave);

    assertEquals(1, commandHandlerDrivenFrmTesting.getCommandDtoSave().getCommandNbr());
    assertEquals(
        4, commandHandlerDrivenFrmTesting.getCommandDtoSave().getComments().getComments().size());
    assertEquals(3, commandHandlerDrivenFrmTesting.getCommandDtoSave().getParts().getParts().size());
  }

  class CommandHandlerDrivenFrmTesting implements CommandHandlerDrivenFrm {

    private DtoCommandSave dtoCommandSave;

    @Override
    public void handle(Dto dto) {
      dtoCommandSave = (DtoCommandSave) dto;
    }

    public DtoCommandSave getCommandDtoSave() {
      return dtoCommandSave;
    }
  }
}
