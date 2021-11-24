package co.ex.frmwrk.gateway.persist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class ThingPart implements Comparable{

    private Integer partNbr;
    private String partDesc;
    @JsonSerialize(using = MoneySerializer.class)
    @JsonDeserialize(as = BigDecimal.class)
    private BigDecimal price;

    @Override
    public int compareTo(Object o) {
        return partDesc.compareTo(((ThingPart)o).partDesc);
    }
}
