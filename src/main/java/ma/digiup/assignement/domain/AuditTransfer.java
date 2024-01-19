package ma.digiup.assignement.domain;

import lombok.Data;
import ma.digiup.assignement.domain.util.EventType;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT")
@Data
public class AuditTransfer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 100)
  private String message;

  @Enumerated(EnumType.STRING)
  private EventType eventType;

}
