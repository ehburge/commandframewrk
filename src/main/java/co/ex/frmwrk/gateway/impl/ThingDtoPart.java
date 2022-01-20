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
public class ThingDtoPart implements Comparable {

  private Integer partNbr;
  private String partDesc;
  private BigDecimal price;
  private short qty;
  private boolean inStock;
  private String stockStatus;
  private BigDecimal discount;

  @Override
  public int compareTo(Object o) {
    return partDesc.compareTo(((ThingDtoPart) o).partDesc);
  }
}
