package demo.demodao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DemoRoleRepository extends JpaRepository<DemoRole,Long>, JpaSpecificationExecutor<DemoRole> {

}
