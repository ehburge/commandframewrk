package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class ThingPart {

    private String partId;
    private int qty;


}
