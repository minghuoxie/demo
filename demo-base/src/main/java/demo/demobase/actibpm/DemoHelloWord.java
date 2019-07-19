package demo.demobase.actibpm;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;

public class DemoHelloWord {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    public void deploymentProcessDefinition(){
       Deployment deployment=processEngine.getRepositoryService() //与流程定义和部署对象相关的service
                .createDeployment() //创建一个部署对象
                .name("hwllowordbus") //部署的名称
                .addClasspathResource("bpms/helloword.bpmn")
                .addClasspathResource("bpms/helloword.png")
                .deploy();

       System.out.println("-------------id:"+deployment.getId()); //部署ID
       System.out.println("-----------name:"+deployment.getName()); //部署名称
    }

    //部署流程定义
    public void deploymentProcessDefinition_zip() {
       InputStream in= this.getClass().getClassLoader().getResourceAsStream("bpms/hellowordzip.zip");
//        InputStream in= null;
//        try {
//            in = new FileInputStream(new File("D:\\yunzhi\\pro\\demo\\demo-base\\src\\main\\resources\\bpms\\hellowordzip.zip"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        ZipInputStream zipStream=new ZipInputStream(in);
        try {
            System.out.println("-------------"+zipStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        InputStream in=this.getClass().getClassLoader().getResourceAsStream("bpms/hellowordzip.zip");
//        ZipInputStream zipStream=new ZipInputStream(in);
        Deployment deployment=processEngine.getRepositoryService() //与流程定义和部署对象相关的service
                .createDeployment() //创建一个部署对象
                .name("hwllowordbus") //部署的名称
                .addZipInputStream(zipStream)
                .deploy();
//
        System.out.println("-------------id:"+deployment.getId()); //部署ID
        System.out.println("-----------name:"+deployment.getName()); //部署名称


//        processEngine.getRepositoryService().createProcessDefinitionQuery() //act_re_procdef 该表的查询服务
//        processEngine.getRepositoryService().createDeploymentQuery() //act_re_deployment 的查询服务
//        processEngine.getRepositoryService().createModelQuery()  //

        //processEngine.getRepositoryService().createProcessDefinitionQuery()
                /**指定条件查询**/
                //.processDefinitionId("")
//                .processDefinitionKey("")
//                .processDefinitionKeyLike("模糊查询")
                /**排序*/
                //.orderByDeploymentId().asc()
                /**返回的结果集*/
                //.list()
                //.count() //返回条数
                //.singleResult() //返回单列
                //.listPage("第一条数据","每页大小");
    }



    //启动流程实列
    public void startProcessInstance(){
        //流程定义的key   act_re_procdef中的KEY_   .bpmn中的<process id="key">   helloword
        String processDifinitionKey="helloword";
        ProcessInstance pi=processEngine.getRuntimeService() //与正在执行的流程实列和执行对象相关的Service
                        .startProcessInstanceByKey(processDifinitionKey);//默认执行最新版本的key
        System.out.println("--------------id:"+pi.getId());  //流程实列ID
        System.out.println("--------流程定义ID:"+pi.getProcessDefinitionId()); //流程定义ID
    }

    //查询当前人的个人任务
    public void findMyPersonalTask(){
        String assignee="李四";
       List<Task> list= processEngine.getTaskService() //与正在执行的任务管理相关的service
                        .createTaskQuery() //创建任务查询对象
                        .taskAssignee(assignee) //指定个人任务查询 ，指定办理人
                        .list();

       if(list!=null&&list.size()>0){
           for(Task task:list){
               System.out.println("任务ID："+task.getId());
               System.out.println("任务名称："+task.getName());
               System.out.println("任务的创建时间："+task.getCreateTime());
               System.out.println("任务的办理人："+task.getAssignee());
               System.out.println("流程实列ID："+task.getProcessInstanceId());
               System.out.println("执行对象ID："+task.getExecutionId());
               System.out.println("流程定义ID："+task.getProcessDefinitionId());
           }
       }
    }

    public void completeTask(){
        String taskID="104";
        processEngine.getTaskService().complete(taskID);
    }



    public static void main(String[] args){
        DemoHelloWord demoHelloWord=new DemoHelloWord();
        demoHelloWord.deploymentProcessDefinition_zip();
    }


    /**
     * 1.和流程定义相关的表：
     * act_re_deployment  部署对象和流程定义相关的表     Deployment deployment=processEngine.getRepositoryService()
     * act_re_model
     * act_re_procdef  流程定义表  DEPLOYMENT_ID_ 是act_re_deployment表的id
     * act_ge_bytearray 资源文件表 DEPLOYMENT_ID_ 存放.bpmb和png文件
     * act_ge_property  主键生产策略
     *
     *
     * */
}
