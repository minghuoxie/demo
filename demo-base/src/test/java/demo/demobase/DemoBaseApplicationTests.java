package demo.demobase;

import com.sun.glass.ui.Application;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoBaseApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("hahahah-----------------");
    }

    @Test
    public void createTabs(){
        /**
         *   this.mailServerDefaultFrom = "activiti@localhost";
         *         this.databaseSchemaUpdate = "false";
         *         this.jdbcDriver = "org.h2.Driver";
         *         this.jdbcUrl = "jdbc:h2:tcp://localhost/activiti";
         *         this.jdbcUsername = "sa";
         *         this.jdbcPassword = "";
         *
         *         默认连接的数据库
         *
         * */
        ProcessEngineConfiguration processEngineConfiguration=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");

        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);
        //工作流的核心对象 ProcessEngine对象
        ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();


    }
    @Test
    public void createTabsByXml(){
        /**
         * 通过流程引擎调用各个service
         * RepositoryService ：管理流程定义
         * RuntimeService：执行管理，包括启动，推进，删除流程实列的操作
         * TaskService：任务管理
         * HistoryService：历史管理
         * IdentityService：组织机构管理
         * FormService：一个可选服务，任务表单管理
         * ManagerService：
         *
         * */
        ProcessEngineConfiguration processEngineConfiguration=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
        System.out.println("------------------end:"+processEngine);
        RuntimeService runtimeService=processEngine.getRuntimeService();
    }
}
