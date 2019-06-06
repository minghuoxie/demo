package demo.demoweb;

import demo.JPush.JPushService;
import demo.config.service.WebSocketService;
import demo.config.service.WebSocketUserService;
import demo.configdao.InitConfig;
import demo.demodao.Demo;
import demo.demodao.DemoUser;
import demo.demodao.DemoUserRepository;
import demo.demoservice.DemoService;
import demo.dto.LoginInput;
import demo.dto.LoginOutput;
import demo.entity.SocketMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api(tags = "用户登录接口")
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private InitConfig initConfig;

    @ApiOperation(value = "认证", notes = "认证")
    @ResponseBody  // 返回 Json 数据
    @RequestMapping(value = "/demoTest", method = RequestMethod.GET)
    public Map<String,String> demoTest(int id, String name){
        Map<String,String> map=new HashMap<String,String>();
        map.put("id",id+"");
        map.put("name",name+"");
        map.put("test","test");
        map.put("com.sdasd.sd",initConfig.getSysType()+"");
        return map;
    }

//    @Autowired
//    private DemoService demoService;
//
//    @Autowired
//    private JPushService jPushService;
//
//    @Autowired
//    private WebSocketService sendService;
//
//    @Autowired
//    private WebSocketUserService webSocketUserService;
//
//    @Autowired
//    private DemoUserRepository userRepository;
//
//    @GetMapping("/index")
//    public String index() {
//        return "security/login";
//    }
//    @GetMapping("/usertwo")
//    public String usertwo() {
//        return "usertwo";
//    }
//    @GetMapping("/addemo")
//    public String addemo() {
//        return "adddemo";
//    }
//    @GetMapping("/demolist")
//    public String demolist() {
//        return "demolist";
//    }
//
//    /**
//     * 模拟后台发送消息给前端
//     * */
//    @RequestMapping(value="/socketSend",method = RequestMethod.POST)
//    @ResponseBody
//    private String  socketSend(String message){
//        System.out.println("当前在线人数"+webSocketUserService.getOnlineUserCount());
//       // sendService.sendMessage(message);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SocketMessage socketMessage=new SocketMessage();
//        socketMessage.data = df.format(new Date());
//        socketMessage.message="asdasdasdsdfsdfsdf";
//        sendService.sendMessage(webSocketUserService.getOnlineUser(1),socketMessage);
//
//        return "true";
//    }
//
//    @ApiOperation(value = "认证", notes = "认证")
//    @ResponseBody  // 返回 Json 数据
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public LoginOutput login(@RequestBody @Valid LoginInput input){
//        System.out.println("-----input.username"+input.getUserName());
//        System.out.println("-----input.password"+input.getPassword());
//        LoginOutput output=new LoginOutput();
//        output.setUsername("res"+input.getUserName());
//        output.setPassword("res"+input.getPassword());
//
//        jPushService.pushClient(null,null,null);
//        return output;
//    }
//
//    @ApiOperation(value="获取所有",notes="获取所有")
//    @ResponseBody
//    @RequestMapping(value="/getall",method=RequestMethod.GET)
//    public Demo getall(){
//        return demoService.findAll();
//    }
//
//    @ApiOperation(value="添加测试数据",notes="测试接口")
//    @ResponseBody  // 返回 Json 数据
//    @RequestMapping(value="/add",method=RequestMethod.POST)
//    private Demo add(String test){
//        Demo demo = new Demo();
//        demo.setName(test.replace(" +",""));
//        return demoService.addOne(demo); // 成功返回 保存后的 demo
//    }
//
//    @ApiOperation(value="查找所有",notes="getall")
//    @ResponseBody
//    @RequestMapping(value="/getAll",method=RequestMethod.GET)
//    private List<Demo> getAll(){
//        //断点调试  Run to Cursor 从当前断点调到下一个断点
//        return demoService.findList();
//    }
//
//    @ApiOperation(value="添加许多数据",notes="many数据")
//    @ResponseBody  // 返回 Json 数据
//    @RequestMapping(value="/addMany",method=RequestMethod.POST)
//    private List<Demo> addMany(){
//        List<Demo> list=new ArrayList<Demo>();
//        list.add(new Demo(2,"洗液"));
//        list.add(new Demo(3,"uzi"));
//        list.add(new Demo(4,"康迪"));
//        list.add(new Demo(5,"司马老贼"));
//        return demoService.addList(list); // 成功返回 保存后的 demo
//    }
//
//    @ApiOperation(value="根据id范围获取数据",notes="getListByID")
//    @ResponseBody
//    @RequestMapping(value="/getListByBbetween",method=RequestMethod.GET)
//    private List<Demo> getListByBetween(Integer one,Integer two){
//        return demoService.findBetween(one,two);
//    }
//
//    @ApiOperation(value="根据姓名获取demo",notes="getNameByNAME")
//    @ResponseBody
//    @RequestMapping(value="/getDemoByName",method=RequestMethod.GET)
//    private DemoUser getDemoByName(String name){
//        return userRepository.findByName(name);
//    }
//
//    @ApiOperation(value="根据id和name查询",notes="getByIDandName")
//    @ResponseBody
//    @RequestMapping(value="/getByIdAndName",method=RequestMethod.GET)
//    private Demo getByIdAndName(Integer id,String name){
//        return demoService.findByIdAndName(id,name);
//    }
//
//    @ApiOperation(value="原生sql",notes="hahah")
//    @ResponseBody
//    @RequestMapping(value="/findListByParam",method=RequestMethod.GET)
//    private List<Demo> findListByParam(String name){
//        return demoService.findListDemoByParam(name);
//    }
//
//    @ApiOperation(value="使用对象查询",notes ="use obj find")
//    @ResponseBody
//    @RequestMapping(value="/findAllNoParam",method=RequestMethod.GET)
//    private List<Demo> findAllNoParam(){
//        return demoService.findAllNoParam();
//    }
}
