package demo.demobase.actibpm.testxiangmu;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.List;
import java.util.Map;

//demo.demobase.actibpm.testxiangmu.TaskTeacherListener
public class TaskTeacherListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String tno= delegateTask.getVariable("sid")+"";
     //S =delegateTask.getAssignee();
       Sql conSql=new Sql();
        String sql="select b.tno from bumen b join tea t on b.bid=t.bid where t.tno=?";
        List<Map<String,String>> list=conSql.executionQuery(sql,new Object[]{tno});
        String line="";
        conSql.printList(list);
        if(list!=null&&list.size()>0){
            for(Map<String,String> map:list){
                line=map.get("tno");
                break;
            }
        }
        for(String aggine:line.split(",")){
            delegateTask.addCandidateUser(aggine);
        }
    }
}
