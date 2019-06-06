package demo.demodao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//https://blog.csdn.net/qq_36722039/article/details/81124382  jpa查询
public interface DemoRepository extends JpaRepository<Demo,Long>, JpaSpecificationExecutor<Demo> {

   Demo findById(Integer id);

   List<Demo> findAllByIdBetween(Integer idOne,Integer idTwo);

   Demo findByName(String name);

  // @Query(value="select d from Demo d where id=?1 and name=?2")
   Demo findByIdAndName(Integer id,String name);

   @Query(value="SELECT a.did,a.sname FROM demo a WHERE a.sname=?1",nativeQuery = true)
   List<Demo> findByParam(String name);

   @Query(value="SELECT a FROM Demo a",nativeQuery=false)
   List<Demo> findAllNoParam();
}
