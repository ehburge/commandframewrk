package co.ex.frmwrk.send.bus.impl;

import co.ex.frmwrk.gateway.Dto;
import co.ex.frmwrk.send.bus.DtoSenderBus;
import co.ex.frmwrk.send.handler.DtoSenderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class DtoSenderBusImpl implements DtoSenderBus {

    private final Map<Class<?>, DtoSenderHandler> chMap;

    @Override
    public void perform(Dto dto) {
        chMap.get(dto.getClass()).handle(dto);
    }
}
