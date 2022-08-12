package co.ex.frmwrk.config;

import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class PutDrivenMap {

  private final Map<Class<?>, CommandBusDrivenFrm> cbDrivenFrm_map;
  private final AppSetupMapBeans appSetupMapBeans;

  @PostConstruct
  public void initDrivenMap() {
    appSetupMapBeans.putCommandBusDrivenFrm(cbDrivenFrm_map);
  }
}
