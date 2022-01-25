package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.persist.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Builder
public class ThingDtoPart {

  private String partId;
  private int qty;

}
