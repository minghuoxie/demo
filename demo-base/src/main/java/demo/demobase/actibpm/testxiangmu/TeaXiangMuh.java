package demo.demobase.actibpm.testxiangmu;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeaXiangMuh {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    private String tno=null;
    private Sql consql=new Sql();
    //用户登陆
    public void userLogin(String name){
        String sql="select * from tea t where t.tname=?";
        Map<String,String> userMap=consql.executionQueryMap(sql,new Object[]{name});
        if(userMap!=null){
            tno=userMap.get("tno");
        }
        System.out.println("-------"+tno);
    }

    //查看代办
    public void findTask(){
        List<Task> taskList= processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(tno)
                .orderByTaskCreateTime()
                .asc()
                .list();

        if(taskList!=null&&taskList.size()>0){
            for(Task task:taskList){
                System.out.println(task.getId()+" "+task.getProcessInstanceId()+" "+task.getProcessDefinitionId()+" "+task.getAssignee());
                findInfo(task.getProcessInstanceId());
            }
        }

    }

    //查看请假信息
    public void findInfo(String proInstanceId){
        String sql="select * from sinfo info where info.pro_difinitionId=?";
        List<Map<String,String>> list=consql.executionQuery(sql,new Object[]{proInstanceId});
        consql.printList(list);
    }

    //完成代办，走下一级
    public void compeleteTaskNext(String taskId){
        Map<String,Object> avariable=new HashMap<>();
        avariable.put("tme","yes");
        processEngine.getTaskService()
                .complete(taskId,avariable);
    }

    //完成代办，不批准
    public void compeleteTaskPre(String taskId,String yesOrNo){
        Map<String,Object> avariable=new HashMap<>();
        avariable.put("tme",yesOrNo);
        processEngine.getTaskService()
                .complete(taskId,avariable);
    }

    public static void main(String[] args){
        TeaXiangMuh teaXiangMuh=new TeaXiangMuh();
        teaXiangMuh.userLogin("李易峰");
        teaXiangMuh.findTask();

        //通过 yes  不通过no
        teaXiangMuh.compeleteTaskPre("604","yes");

       // teaXiangMuh.lookTask();
      //  teaXiangMuh.end("901");
    }

    //领导完成任务
    public void lingdaoCompelete(){
        Map<String,Object> avariable=new HashMap<>();
        avariable.put("tgme","yes");
        processEngine.getTaskService()
                .complete("1203",avariable);
    }

    //保存申请单
    public void end(String proId){
        processEngine.getRuntimeService()
                .signal(proId);
    }

    //领导 查看任务
    public void lookTask(){
        List<Task> list=processEngine.getTaskService()
                .createTaskQuery()
                .taskCandidateUser(tno)
                .list();
        if(list!=null&&list.size()>0){
            for(Task task:list){
                System.out.println(task.getId()+" "+task.getProcessInstanceId()+" "+task.getProcessDefinitionId()+" "+task.getAssignee());
            }
        }
    }
}
