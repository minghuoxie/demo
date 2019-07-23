package demo.demobase.actibpm.testxiangmu;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeaLXiangMuh {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    private String tno=null;
    private Sql consql=new Sql();
    //部门领导登陆
    public void userLogin(String name){
        String sql="select * from tea t where t.tname=?";
        Map<String,String> userMap=consql.executionQueryMap(sql,new Object[]{name});
        if(userMap!=null){
            tno=userMap.get("tno");
        }
        System.out.println("-------"+tno);
    }

    //查看任务
    public String lookTask(){
        String proInstanceId="";
       List<Task> list= processEngine.getTaskService()
                .createTaskQuery()
                .taskCandidateUser(tno)
                .list();
        if(list!=null&&list.size()>0){
            for(Task task:list){
                System.out.println(task.getId()+" "+task.getProcessInstanceId()+" "+task.getProcessDefinitionId()+" "+task.getAssignee());
                findInfo(proInstanceId=task.getProcessInstanceId());
            }
        }
        return proInstanceId;
    }

    //查看请假信息
    public void findInfo(String proInstanceId){
        String sql="select * from sinfo info where info.pro_difinitionId=?";
        List<Map<String,String>> list=consql.executionQuery(sql,new Object[]{proInstanceId});
        consql.printList(list);
    }

    //完成代办
    public void compeleteTask(String taskId,String type,String processInstanceId){
        Map<String,Object> avariable=new HashMap<>();
        avariable.put("tgme",type);

        //将任务聚合到自己身上
        processEngine.getTaskService()
                .claim(taskId,tno);

        processEngine.getTaskService()
                .complete(taskId,avariable);
        if("yes".equals(type)) {
            endProcess(processInstanceId);
        }
    }

    //结束 流程
    public void endProcess(String processInstanceId){
        processEngine.getRuntimeService()
                .signal(processInstanceId);
    }

    public static void main(String[] args){
        TeaLXiangMuh teaLXiangMuh=new TeaLXiangMuh();
        teaLXiangMuh.userLogin("戚薇");
        teaLXiangMuh.compeleteTask("703","yes", teaLXiangMuh.lookTask());
       // teaLXiangMuh.endProcess("501");
    }
}
