package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@ToString(includeFieldNames=true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ThingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = false)
  private Long thingNbr;

  @CreationTimestamp
  private Timestamp dttm;

  @ElementCollection(fetch=FetchType.LAZY)
  @CollectionTable(name = "thing_comments", joinColumns = @JoinColumn(name = "thing_id"))
  private List<ThingComment> comments;

  @ElementCollection(fetch=FetchType.LAZY)
  @CollectionTable(name = "thing_parts", joinColumns = @JoinColumn(name = "thing_id"))
  private List<ThingPart> parts;
}
