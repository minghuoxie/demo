package demo.demobase.actibpm.testxiangmu;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class TestXiangMuh {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    private String stuNo=null;
    private Sql consql=new Sql();
    //部署流程
    public void deploymentProcessDefinition(){
        Deployment deployment=processEngine.getRepositoryService() //与流程定义和部署对象相关的service
                .createDeployment() //创建一个部署对象
                .name("stuqinjia") //部署的名称
                .addClasspathResource("testxiangmu/ceshi.bpmn")
                .addClasspathResource("testxiangmu/ceshi.png")
                .deploy();

        System.out.println("-------------id:"+deployment.getId()); //部署ID
        System.out.println("-----------name:"+deployment.getName()); //部署名称
    }
    //用户登陆
    public void userLogin(String name){
            String sql="select * from stu s where s.stname=?";
            Map<String,String> userMap=consql.executionQueryMap(sql,new Object[]{name});
            if(userMap!=null){
                stuNo=userMap.get("stuo");
            }
            System.out.println("-------"+stuNo);

    }

    //启动流程，需要指定请假学生和请假信息  创建请假条并且发送  绑定格式为 s1002_1  学号_请假条ID
    public void startProcess(String info){
        //启动流程  绑定编号
        String processDifinitionKey="stuqinjiakey";
        Map<String,Object> vriable=new HashMap<>();
        vriable.put("uid",stuNo);
        ProcessInstance pi=processEngine.getRuntimeService() //与正在执行的流程实列和执行对象相关的Service
               // .startProcessInstanceByKey(processDifinitionKey);//默认执行最新版本的key
                .startProcessInstanceByKey(processDifinitionKey,vriable);
        System.out.println("--------------id:"+pi.getId());  //流程实列ID
        System.out.println("--------流程定义ID:"+pi.getProcessDefinitionId()); //流程定义ID

        //创建请假表  将pro_difinitionId绑定流程实列ID
        String insertSql="insert into sinfo(stuo,info,pro_difinitionId) values(?,?,?)";
        try {
            consql.executionSql(insertSql,new Object[]{stuNo,info,pi.getProcessInstanceId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查看代办
    public void findTask(){
       List<Task> taskList= processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(stuNo)
                .orderByTaskCreateTime()
                .asc()
                .list();

       if(taskList!=null&&taskList.size()>0){
           for(Task task:taskList){
               System.out.println(task.getId()+" "+task.getProcessInstanceId()+" "+task.getProcessDefinitionId()+" "+task.getAssignee());
           }
       }
    }

    //完成代办，选择老师和提交请假条
    public void completeTask(String taskId,String type){
            //查看学生的老师
        String findTeaSql="select t.* from tea t join stship sp on t.tno=sp.tno where sp.stuo=?";
        List<Map<String,String>> teaList=consql.executionQuery(findTeaSql,new Object[]{stuNo});
        if(teaList!=null&&teaList.size()>0){
            for(Map<String,String> teaMap:teaList){
                System.out.println(teaMap);
            }
        }

        Map<String,Object> varable=new HashMap<>();
        varable.put("sid",teaList.get(0).get("tno"));
        varable.put("sme",type);
        processEngine.getTaskService()
                .complete(taskId,varable);
    }

    public static void main(String[] args){
       // new Sql().test_conn();
        TestXiangMuh demoHelloWord=new TestXiangMuh();
        // 1.部署流程
        //demoHelloWord.deploymentProcessDefinition();
        // 2.用户登陆
        demoHelloWord.userLogin("李四");
        //3.启动流程  创建请假申请
        //demoHelloWord.startProcess("今天脚又歪倒了，请假");
     //   demoHelloWord.startProcess();

        //4.提交申请  完成任务  选择对应的审批老师
        demoHelloWord.findTask();
        //yes no
       demoHelloWord.completeTask("505","yes");
        //  testXiangMuh.deploymentProcessDefinition();
    }
}
