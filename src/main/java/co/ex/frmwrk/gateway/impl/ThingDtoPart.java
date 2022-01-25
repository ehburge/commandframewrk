package co.ex.frmwrk.gateway.impl;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@ToString
@Getter
@Jacksonized
@Builder(access = AccessLevel.PUBLIC)
public class ThingDtoPart {

  private String partId;
  private int qty;
}
