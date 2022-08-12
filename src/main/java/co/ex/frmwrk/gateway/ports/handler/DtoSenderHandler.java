package co.ex.frmwrk.gateway.ports.handler;

import co.ex.frmwrk.gateway.ThingDto;

public interface DtoSenderHandler {

    void handle(ThingDto thingDto);
}
