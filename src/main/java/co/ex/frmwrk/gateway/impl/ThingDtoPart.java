package co.ex.frmwrk.gateway.impl;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@ToString
@Getter
@Jacksonized
@Builder(access = AccessLevel.PUBLIC)
public class ThingDtoPart {

  private final String partId;
  private final int qty;
  private final String lineAction;
  private final String promoCodes;
}
