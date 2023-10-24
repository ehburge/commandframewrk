package co.ex.frmwrk.gateway.persist;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@ToString(includeFieldNames = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "command_entity", indexes = @Index(columnList = "commandNbr, dttm asc"))
public class CommandEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid", nullable = false, updatable = false, unique = true)
  private String uuid;

  // IDENTITY uses underlying DB id generation, AUTO uses hibernate id generation
  // SEQUENCE is not compatible between DB's

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false, unique = false)
  private Long id;

  @CreationTimestamp private Timestamp dttm;

  private String content_type; // data class

  @Column(length = 5000)
  private String entity_content; // Command json
}
