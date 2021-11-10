package co.ex.frmwrk.guice.injector;

import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FrmWrkAppTest {

  @Test
  public void makeInjector() {

    Injector injector = FrmWrkApp.getInjector();

    assertNotNull(injector);
  }
}
