package co.ex.frmwrk.driven.bus;

import cmd.AppCommand;
import cmd.DomCommand;

public interface CommandBusDrivenFrm {
    void perform(AppCommand domCommand);
}
