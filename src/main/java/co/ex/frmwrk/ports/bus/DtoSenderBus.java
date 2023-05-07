package co.ex.frmwrk.ports.bus;

import co.ex.frmwrk.gateway.ThingDto;

public interface DtoSenderBus {

    void perform(ThingDto thingDto);
}
