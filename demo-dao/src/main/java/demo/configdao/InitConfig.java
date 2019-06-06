package demo.configdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitConfig {

    @Value("${com.sam.type}")
    private Integer sysType;

    public Integer getSysType(){
        return this.sysType;
    }
}
