package demo.schedule;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoSche {
    /**
     * 参数类型：
     * 简单参数时：
     * */
    int index() default -1;

    /**
     * 参数类型
     * */
    MethodArgType argType();

    /**
     * 任务类型
     * */
    String  taskType();

    /**
     * 目标类型
     * */
    String targetType();
}
