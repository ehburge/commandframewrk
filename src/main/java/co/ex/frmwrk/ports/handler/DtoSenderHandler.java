package co.ex.frmwrk.ports.handler;

import co.ex.frmwrk.gateway.ThingDto;

public interface DtoSenderHandler {

    void handle(ThingDto thingDto);
}
