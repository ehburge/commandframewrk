package co.ex.frmwrk.ports.bus.impl;

import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.ports.bus.DtoSenderBus;
import co.ex.frmwrk.ports.handler.DtoSenderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class DtoSenderBusImpl implements DtoSenderBus {

    private final Map<Class<?>, DtoSenderHandler> chMap;

    @Override
    public void perform(ThingDto thingDto) {
        chMap.get(thingDto.getClass()).handle(thingDto);
    }
}
