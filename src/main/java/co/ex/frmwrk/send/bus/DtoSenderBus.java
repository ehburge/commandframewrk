package co.ex.frmwrk.send.bus;

import co.ex.frmwrk.gateway.ThingDto;

public interface DtoSenderBus {

    void perform(ThingDto thingDto);
}
