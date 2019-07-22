package demo.demobase.actibpm;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

public class DemoHelloWord {
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    public void deploymentProcessDefinition(){
       Deployment deployment=processEngine.getRepositoryService() //与流程定义和部署对象相关的service
                .createDeployment() //创建一个部署对象
                .name("hwllowordbus") //部署的名称
                .addClasspathResource("bpms/flow.bpmn")
                .addClasspathResource("bpms/flow.png")
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

    /**删除流程定义*/
    public void deleteProcessDefinition(){
        String deployMentId="601";
        /**
         * 不带级联的删除
         *      只能删除没有启动的流程，如果流程启动，就会抛出异常
         * */
        processEngine.getRepositoryService()
                .deleteDeployment(deployMentId);

        //级联删除
        processEngine.getRepositoryService()
                .deleteDeployment(deployMentId,true);
        System.out.println("删除成功");
    }

    /**查看流程图*/
    public void viewPic(){
        String deployMentId="101";
        //根据部署id获取资源列表(.png .bpmn)
        List<String> listResource=processEngine.getRepositoryService()
                                        .getDeploymentResourceNames(deployMentId);

        String pngResource="";
        if(listResource!=null&&listResource.size()>0){
            for(String name:listResource){
                if(name.endsWith(".png")){
                    pngResource=name;
                    break;
                }
            }
        }

        //根据部署ID和资源名称获取InputStream
        InputStream in=processEngine.getRepositoryService()
                                .getResourceAsStream(deployMentId,pngResource);
        //将图片生成在D:\Temp\resourceimg
        File file=new File("D:/Temp/resourceimg/"+pngResource);
        try {
            FileUtils.copyInputStreamToFile(in,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("viewPic成功");
    }

    /**附加功能：查询最新版本的流程定义*/
    public void findLastVersionProcessDefinition(){
       List<ProcessDefinition> list = processEngine.getRepositoryService()
                    .createProcessDefinitionQuery()
                    .orderByProcessDefinitionVersion()
                    .asc()
                    .list();

       Map<String,ProcessDefinition> map=new LinkedHashMap<>();
       if(list!=null&&list.size()>0){
           for(ProcessDefinition definition:list){
               map.put(definition.getKey(),definition);
           }
       }
       List<ProcessDefinition> pdList=new ArrayList<>(map.values());

    }

    /**查询流程状态(判断流程是不是结束)*/
    public void isProcessEnd(){
        String processInstanceId="201";
       ProcessInstance instance= processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

       if(processInstanceId==null){
           System.out.println("流程结束");
       }else{
           System.out.println("流程正在执行");
       }
    }

    /**查询历史数据*/
    public void findHistoryTask(){
        String taskAssignee="张三";
        List<HistoricTaskInstance> list=processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .taskAssignee(taskAssignee)
                .list();

        if(list!=null&&list.size()>0){
            for(HistoricTaskInstance taskInstance:list){
                System.out.println("--"+taskInstance.getProcessInstanceId());
            }
        }
    }


    /**设置流程变量*/
    public void setAvariable(){
        //与流程实例，执行对象(正在执行)
       RuntimeService runtimeService= processEngine.getRuntimeService();
       //与任务(正在执行)
        TaskService taskService=processEngine.getTaskService();

        //设置流程变量
        //runtimeService.setVariable("executionId","key","object");//
      //  runtimeService.setVariables("executionId",new HashMap<String,String>().put("key","obj"));//执行对象ID和map集合绑定流程变量

        //taskService.setVariables("taskId",map);  //任务id和map集合绑定流程变量

        //runtimeService.startProcessInstanceByKey("key",map)  启动流程实例的同时，设置流程变量
        //taskService.complete("taskID",map); 完成任务的同时，设置流程变量


        //获取流程变量   放在集合中
       // runtimeService.getVariable("executionID","key"); 根据执行ID和变量的名称获取
        //runtimeService.getVariables("executionId");根据执行Id获取
       // runtimeService.getVariables("executionId",Collection<O>)  根据执行ID和变量名称集合获取

        // taskService.getVariable("taskId","key"); 根据任务ID和变量的名称获取
        //taskService.getVariables("taskId");根据任务Id获取
        // taskService.getVariables("taskId",Collection<O>)  根据任务ID和变量名称集合获取

    }
    public static void main(String[] args){
        DemoHelloWord demoHelloWord=new DemoHelloWord();
        demoHelloWord.deploymentProcessDefinition();
    }


    /**历史表：汇总*/
    public void findHitory(){
        processEngine.getHistoryService().createHistoricProcessInstanceQuery();//历史流程
        processEngine.getHistoryService().createHistoricTaskInstanceQuery();//历史任务
        processEngine.getHistoryService().createHistoricActivityInstanceQuery();//历史活动
        processEngine.getHistoryService().createHistoricVariableInstanceQuery();//历史流程变量
    }

    /**
     * 1.和流程定义相关的表：
     * act_re_deployment  部署对象和流程定义相关的表     Deployment deployment=processEngine.getRepositoryService()
     * act_re_model
     * act_re_procdef  流程定义表  DEPLOYMENT_ID_ 是act_re_deployment表的id
     * act_ge_bytearray 资源文件表 DEPLOYMENT_ID_ 存放.bpmb和png文件
     * act_ge_property  主键生产策略
     *
     * 1.//部署流程定义   在act_re_deployment，act_re_procdef和act_ge_bytearray中生成数据
     *
     *
     * 2.流程实例，执行对象，任务
     * act_ru_execution  正在执行的执行对象表  PROC_DEF_ID_(流程定义ID)
     * act_hi_procinst   流程实例的历史表  PROC_DEF_ID_
     * act_ru_task       正在执行的任务表(只有节点是UserTask的时候，该表中有数据)  EXECUTION_ID_(执行对象ID) PROC_DEF_ID_
     *                      ASSIGNEE_(执行者)
     * act_hi_taskinst   任务历史表(只有节点是UserTask的时候，该表中有数据) PROC_DEF_ID_  EXECUTION_ID_ ASSIGNEE_
     * act_hi_actinst    所有活动节点的历史表  PROC_DEF_ID_  EXECUTION_ID_ TASK_ID_(任务ID)
     *
     * 总结：一个流程中，执行对象可以有多个，但是流程实例只有一个
     *
     *
     * 3.流程变量
     * act_ru_variable  正职执行的流程变量  EXECUTION_ID_(执行对象ID)  PROC_INST_ID_(流程实例ID)  TASK_ID_(任务ID)
     * act_hi_varinst   流程变量 历史   EXECUTION_ID_(执行对象ID)  PROC_INST_ID_(流程实例ID)  TASK_ID_(任务ID)
     *
     *
     *
     * */
}
