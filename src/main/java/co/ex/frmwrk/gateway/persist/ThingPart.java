package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Builder
public class ThingPart implements Comparable{

    private Integer partNbr;
    private String partDesc;
    private BigDecimal price;
    private short qty;
    private boolean inStock;
    private String stockStatus;
    private BigDecimal discount;

    @Override
    public int compareTo(Object o) {
        return partDesc.compareTo(((ThingDtoPart)o).getPartDesc());
    }
}
