package demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Bean
    public Docket application(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("xie")
                .select() //选择那些路径生成文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())  //对所有路径进行监督
                .build()
                .apiInfo(applicationinfo()); //用来创建该api的基本信息
    }

    private ApiInfo applicationinfo(){
        //(java.lang.String title, java.lang.String description, java.lang.String version,
        // java.lang.String termsOfServiceUrl,
        // java.lang.String contactName,
        // java.lang.String license, java.lang.String licenseUrl)
        ApiInfo apiInfo=new ApiInfo("接口管理--大标题",
                "小标题","0.1",
                "",
                "作者",
                "hahah",
                "www.baidu.com");
        return apiInfo;
    }
}
