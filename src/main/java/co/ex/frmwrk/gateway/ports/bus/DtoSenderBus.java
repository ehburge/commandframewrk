package co.ex.frmwrk.gateway.ports.bus;

import co.ex.frmwrk.gateway.ThingDto;

public interface DtoSenderBus {

    void perform(ThingDto thingDto);
}
