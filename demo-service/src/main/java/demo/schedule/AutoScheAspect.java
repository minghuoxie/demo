package demo.schedule;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
@Aspect
public class AutoScheAspect {

    /**
     * 定义切点
     * */
    @Pointcut("@annotation(demo.schedule.AutoSche)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        AutoSche autoSche=method.getAnnotation(AutoSche.class);
        int id =autoSche.index();
        String taskType=autoSche.taskType();
        String targetType=autoSche.targetType();
        System.out.println(id+"--:--"+taskType+"--:--"+targetType);

        Object[] args=proceedingJoinPoint.getArgs();
        Object obj = proceedingJoinPoint.proceed(); //执行proceed()就会去执行被监听的方法
        System.out.println("执行的结果:"+obj);
        if(args!=null&&args.length>0){
            for(int i=0;i<args.length;i++){
                System.out.println("args:"+args[i]);
            }
        }
        return obj;
    }
}
