package co.ex.frmwrk.app.driven.cmd.handler;

import co.ex.app.AppInjector2;
import co.ex.app.driven.cmd.handler.CommandBusDrivenFrm;
import co.ex.app.driven.cmd.handler.CommandHandlerDrivenApp;
import co.ex.frmwrk.driven.bus.impl.CommandBusDrivenFrmAdapterImpl;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import com.google.inject.Guice;
import com.google.inject.Injector;
import model.AppThingComments;
import model.AppThingPart;
import model.AppThingParts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
    injector = Guice.createInjector(new AppInjector2());
  }

  @BeforeEach
  void setUp() {
    comments =
        new AppThingComments(
            new ArrayList<>(Arrays.asList(new String[] {"Larry", "Moe", "Curly", "Schemp"})));
    AppThingPart thingPart1 = new AppThingPart(1, "part 1 desc", new BigDecimal(123.45));
    AppThingPart thingPart2 = new AppThingPart(2, "part 2 desc", new BigDecimal(234.56));
    AppThingPart thingPart3 = new AppThingPart(3, "part 3 desc", new BigDecimal(345.56));

    appParts =
        new AppThingParts(
            new ArrayList<>(
                Arrays.asList(new AppThingPart[] {thingPart1, thingPart2, thingPart3})));
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
    CommandBusDrivenFrm commandBusDrivenFrm = new CommandBusDrivenFrmAdapterImpl(chDrivenFrmMap);
    cbDrivenFrmMap.put(AppThingCommandSave.class, commandBusDrivenFrm);

    // May not need to do it this way.
    // Might be able to create a child injector of spring framework classes.
    // Inject CommandBusDrivenFrm impl into CommandHandlerDrivenApp constructor
    // Haven't been able to get a Guice child injector to work.
    CommandHandlerDrivenApp impl = injector.getInstance(CommandHandlerDrivenApp.class);
    impl.setCommandBusDrivenFrmMap(cbDrivenFrmMap);

    AppThingCommandSave appThingCommandSave =
        AppThingCommandSave.builder().thingNbr(1L).comments(comments).parts(appParts).build();
    impl.handle(appThingCommandSave);
    assertEquals(1, commandHandlerDrivenFrmTesting.getThingDtoSave().getThingNbr());
    assertEquals(4, commandHandlerDrivenFrmTesting.getThingDtoSave().getComments().getComments().size());
    assertEquals(3, commandHandlerDrivenFrmTesting.getThingDtoSave().getParts().getParts().size());
  }

  class CommandHandlerDrivenFrmTesting implements CommandHandlerDrivenFrm {

    private ThingDtoSave thingDtoSave;

    @Override
    public void handle(ThingDto thingDto) {
      thingDtoSave = (ThingDtoSave) thingDto;
    }

    public ThingDtoSave getThingDtoSave() {
      return thingDtoSave;
    }
  }
}
