package demo.demobase.actibpm;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

//demo.demobase.actibpm.TaskListenerImpl
public class TaskListenerImpl implements TaskListener {
    //用来动态指定 办理人
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("nihaoa");
        System.out.println("------------------??????????");
    }
}
