package co.ex.frmwrk.send.bus;

import co.ex.frmwrk.gateway.Dto;

public interface DtoSenderBus {

    void perform(Dto dto);
}
