package co.ex.frmwrk.frmin.persist_incoming;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandIncomingRepository extends JpaRepository<CommandIncoming, Long> {

  List<CommandIncoming> findByCommandNbrOrderByDttm(Long commandNbr);
}
