package demo.demoservice.impl;

import demo.demodao.DemoJpaBuild;
import demo.demodao.DemoJpaBuildRepository;
import demo.demoservice.DemoJpaBuildService;
import demo.demoservice.impl.vo.DemoJpaBuildAndUser;
import demo.schedule.AutoSche;
import demo.schedule.MethodArgType;
import demo.sqlfind.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

@Service
@Transactional
public class DemoJpaBuildServiceImpl implements DemoJpaBuildService {

    @Autowired
    private DemoJpaBuildRepository demoJpaBuildRepository;

    @Autowired
    private CommonRepository commonRepository;

    @Override
    public List<DemoJpaBuild> insertMany(List<DemoJpaBuild> list) {
        return demoJpaBuildRepository.save(list);
    }


    @Override
    @AutoSche(index=0,argType = MethodArgType.SIMPLE,taskType = "taskTypesss",targetType = "targetTypesss")
    public List<DemoJpaBuild> findAll() {
//        List<DemoJpaBuild> demos=demoJpaBuildRepository.findAll((root,criteriaQuery,criteriaBuilder)->{
//           return null;
//        });
        List<DemoJpaBuild> demos=demoJpaBuildRepository.findAll(new Specification<DemoJpaBuild>(){
            @Override
            public Predicate toPredicate(javax.persistence.criteria.Root<DemoJpaBuild> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("qq").as(String.class),"2300671708"));
                predicates.add(cb.equal(root.get("age").as(String.class),"23"));
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });
        return demos;
    }

    @Override
    @AutoSche(index=0,argType = MethodArgType.SIMPLE,taskType = "taskTypesss",targetType = "targetTypesss")
    public String findAspect(int index,String arg){

        System.out.println(index+"-----"+arg);

        return arg+"return";
    }

    @Override
    public void findBySql() {
       // String sql="select * from demo_jpa_build where id=?1";
        String sql="select a.*,b.username from demo_jpa_build a left join demo_user b on a.id=b.id where a.id=?1";

//        Map<Integer,Object> sendMap=new HashMap<>();
//        sendMap.put(1,1);

       // commonRepository.find(sql,sendMap);
        commonRepository.getMap(sql);
    }
}
/**
 * Specification<T>  jpa的动态查询
 * T findOne(Specification<T> spec);  根据查询条件获取一条数据
 * List<T> findAll(Specification<T> spec); 根据条件获取全部数据
 * Page<T> findAll(Specification<T> spec, Pageable pageable); 根据条件分页查询数据，Pageable设定页码，
 *      一页数据量，同时返回的是Page类的对象，可以通过getContent()方法拿到List集合数据
 *
 * List<T> findAll(Specification<T> spec, Sort sort); 根据条件查询并且返回排序后的结果。
 * long count(Specification<T> spec); 获取满足当前条件的数据条数
 *
 *--------////Root<DemoJpaBuild> root, CriteriaQuery<?> query, CriteriaBuilder cb
 * root:查询的根对象
 * query：顶层查询对象，自定义查询方式(一般不使用)
 * cb:查询构造器，封装了很多查询条件
 *
 *
 *
 *
 * */