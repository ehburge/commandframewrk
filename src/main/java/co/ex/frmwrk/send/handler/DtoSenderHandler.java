package co.ex.frmwrk.send.handler;

import co.ex.frmwrk.gateway.ThingDto;

public interface DtoSenderHandler {

    void handle(ThingDto thingDto);
}
