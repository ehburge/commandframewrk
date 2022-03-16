package co.ex.frmwrk.config;

import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class PutDrivenMap {

    private final Map<Class<?>, CommandBusDrivenFrm > cbDrivenFrm_map;
    private final AppSetupMapBeans appSetupMapBeans;

    @PostConstruct
    public void initDrivenMap() {
        appSetupMapBeans.putCommandBusDrivenFrm( cbDrivenFrm_map );
    }


}
