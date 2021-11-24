package co.ex.frmwrk.gateway.persist;

import lombok.*;

import javax.persistence.Embeddable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ThingComment {

    private String comment = null;
}
