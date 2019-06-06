package demo.demodao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DemoUserRepository extends JpaRepository<DemoUser,Long>, JpaSpecificationExecutor<DemoUser> {

//    @Query(value="SELECT a FROM User a WHERE a.username=?1")
//    DemoUser findByName(String username);
}
