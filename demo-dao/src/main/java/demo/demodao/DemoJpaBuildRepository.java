package demo.demodao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
public interface DemoJpaBuildRepository extends JpaRepository<DemoJpaBuild,Long>, JpaSpecificationExecutor<DemoJpaBuild> {

    List<DemoJpaBuild> findAllBy();
}
