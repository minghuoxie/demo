package demo.demoweb;

import demo.demodao.DemoJpaBuild;
import demo.demoservice.DemoJpaBuildService;
import demo.dto.jpa.JpaBuildInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "JPA控制器")
@RequestMapping("/jpa")
public class JapController {
    @Autowired
    private DemoJpaBuildService demoJpaBuildService;

    @ApiOperation(value = "认证", notes = "认证")
    @ResponseBody  // 返回 Json 数据
    @RequestMapping(value = "/jpaTest", method = RequestMethod.GET)
    public Map<String,String> jpaTest(int id,String name){
        Map<String,String> map=new HashMap<String,String>();
        map.put("id",id+"");
        map.put("name",name+"");
        map.put("test","test");
        return map;
    }

    @ApiOperation(value = "保存测试数据", notes = "保存测试数据")
    @ResponseBody  // 返回 Json 数据
    @RequestMapping(value = "/jpaTestInsert", method = RequestMethod.POST)
    public List<DemoJpaBuild> jpaTestInsert(@Valid @RequestBody JpaBuildInput input){
        List<DemoJpaBuild> listInput=input.getBuilds();
        return demoJpaBuildService.insertMany(listInput);
    }

    @ApiOperation(value = "jpa查询", notes = "jpa查询")
    @ResponseBody  // 返回 Json 数据
    @RequestMapping(value = "/jpaFind", method = RequestMethod.GET)
    public List<DemoJpaBuild> jpaFind(){
        return demoJpaBuildService.findAll();
    }

    @ApiOperation(value = "测试切面AOP", notes = "测试切面AOP")
    @ResponseBody  // 返回 Json 数据
    @RequestMapping(value = "/findAspect", method = RequestMethod.GET)
    public Map<String,String> findAspect(){
        Map<String,String> returnMap=new HashMap<String,String>();
        returnMap.put("he","hahaha");
       // returnMap.put("return",demoJpaBuildService.findAspect(0,"return"));

        demoJpaBuildService.findBySql();

        return returnMap;
    }




}
/***
 *
 *
 *
 *
 * */