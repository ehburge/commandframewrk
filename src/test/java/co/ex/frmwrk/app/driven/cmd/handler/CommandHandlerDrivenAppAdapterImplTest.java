package co.ex.frmwrk.app.driven.cmd.handler;

import co.ex.app.AppInjectorFrmWrk;
import co.ex.app.driven.cmd.handler.impl.CommandBusDrivenFrm;
import co.ex.app.driven.cmd.handler.impl.CommandHandlerDrivenAppImpl;
import co.ex.frmwrk.driven.bus.impl.CommandBusDrivenFrmAdapterImpl;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapperImpl;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import com.ex.thing.model.AppThingComments;
import com.ex.thing.model.AppThingPart;
import com.ex.thing.model.AppThingParts;
import com.google.inject.Guice;
import com.google.inject.Injector;
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

  static Injector injector;
  AppThingComments comments;
  AppThingParts appParts;

  @BeforeAll
  static void setupAll() {
    injector = Guice.createInjector(new AppInjectorFrmWrk());
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

    Map<Class, CommandHandlerDrivenFrm> chDrivenFrmMap = new HashMap<>();
    CommandHandlerDrivenFrmTesting commandHandlerDrivenFrmTesting =
        new CommandHandlerDrivenFrmTesting();
    chDrivenFrmMap.put(ThingDtoSave.class, commandHandlerDrivenFrmTesting);

    Map<Class, CommandBusDrivenFrm> cbDrivenFrmMap = new HashMap<>();
    AppThingCommandSaveThingDtoSaveMapper saveThingDtoSaveMapper =
        new AppThingCommandSaveThingDtoSaveMapperImpl();
    CommandBusDrivenFrm commandBusDrivenFrm =
        new CommandBusDrivenFrmAdapterImpl(chDrivenFrmMap, saveThingDtoSaveMapper);
    cbDrivenFrmMap.put(AppThingCommandSave.class, commandBusDrivenFrm);

    // May not need to do it this way.
    // Might be able to create a child injector of spring framework classes.
    // Inject CommandBusDrivenFrm impl into CommandHandlerDrivenApp constructor
    // Haven't been able to get a Guice child injector to work.
    CommandHandlerDrivenAppImpl impl = injector.getInstance(CommandHandlerDrivenAppImpl.class);
    impl.setCommandBusDrivenFrmMap(cbDrivenFrmMap);

    AppThingCommandSave appThingCommandSave =
        AppThingCommandSave.builder().thingNbr(1L).comments(comments).parts(appParts).build();
    impl.handle(appThingCommandSave);
    assertEquals(1, commandHandlerDrivenFrmTesting.getThingDtoSave().getThingNbr());
    assertEquals(
        4, commandHandlerDrivenFrmTesting.getThingDtoSave().getComments().getComments().size());
    assertEquals(3, commandHandlerDrivenFrmTesting.getThingDtoSave().getParts().getParts().size());
  }

  class CommandHandlerDrivenFrmTesting implements CommandHandlerDrivenFrm {

    private ThingDtoSave thingDtoSave;

    @Override
    public void handle(ThingDtoSave thingDto) {
      thingDtoSave = thingDto;
    }

    public ThingDtoSave getThingDtoSave() {
      return thingDtoSave;
    }
  }
}
