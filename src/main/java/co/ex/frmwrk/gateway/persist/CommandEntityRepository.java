package co.ex.frmwrk.gateway.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandEntityRepository extends JpaRepository<CommandEntity, Long> {

  List<CommandEntity> findByCommandNbrOrderByDttm(Long commandNbr);
}
