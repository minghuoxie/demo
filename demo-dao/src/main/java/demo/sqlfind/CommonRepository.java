package demo.sqlfind;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class CommonRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void find(String sql, Map<Integer,Object> params){
        Query nativeQuery=entityManager.createNativeQuery(sql);
        if(params!=null){
            for(Integer key:params.keySet()){
                nativeQuery.setParameter(key,params.get(key));
            }
        }
       // List<Object> objs=nativeQuery.getResultList();
       // nativeQuery.get
       // Map<String,Object> maps=nativeQuery.getHints(); maps==null
    }

    public <T> T getObj(String sql,String args[],Class<T> cla){
        Query nativeQuery=entityManager.createNativeQuery(sql,cla);
        nativeQuery.setParameter(1,1);
        T t=(T)nativeQuery.getSingleResult();
        return t;
    }

    public Map<String,Object> getMap(String sql){
        Query nativeQuery=entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1,1);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List rows=nativeQuery.getResultList();
        for(Object obj:rows){
            Map ma=(Map)obj;
            System.out.println(ma);
        }
        return null;
    }

}
