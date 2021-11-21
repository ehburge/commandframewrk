package co.ex.frmwrk.gateway.persist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ThingPart implements Comparable{
    private Integer partNbr;
    private String partDesc;
    private BigDecimal price;

    @Override
    public int compareTo(Object o) {
        return partDesc.compareTo(((ThingPart)o).partDesc);
    }
}
