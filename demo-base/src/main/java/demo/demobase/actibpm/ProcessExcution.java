package demo.demobase.actibpm;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

public class ProcessExcution {

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

    //启动流程实列
    public void startProcessInstance(){
        //流程定义的key   act_re_procdef中的KEY_   .bpmn中的<process id="key">   helloword
        String processDifinitionKey="helloword";
        ProcessInstance pi=processEngine.getRuntimeService() //与正在执行的流程实列和执行对象相关的Service
                .startProcessInstanceByKey(processDifinitionKey);//默认执行最新版本的key
        System.out.println("--------------id:"+pi.getId());  //流程实列ID
        System.out.println("--------流程定义ID:"+pi.getProcessDefinitionId()); //流程定义ID
    }
}
