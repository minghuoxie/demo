package demo.demodao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemoPermissionRepository extends JpaRepository<DemoPermission,Long>, JpaSpecificationExecutor<DemoPermission> {

    @Query(value="SELECT a FROM DemoPermission a WHERE a.uid=?1")
    List<DemoPermission> findByUid(Integer uid);
}
