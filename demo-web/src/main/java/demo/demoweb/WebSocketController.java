package demo.demoweb;

import demo.config.service.WebSocketService;
import demo.demodao.Demo;
import demo.entity.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSocketController {

    @Autowired
    private WebSocketService sendService;


//    @MessageMapping("/sockets/topic")
//    @SendTo("/sockets/user")  //发送信息到前端的/topic/send
//    public SocketMessage send(SocketMessage message){
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        message.data = df.format(new Date());
//        System.out.println("接收到前端的信息:"+message.message);
//        return message;
//    }
//
    @Scheduled(fixedRate =1000)
    @SendTo("/sockets/topic") //发送信息到前端/topic/callback
    public void callback() throws Exception {
        // 发现消息   后台主动向前端发送消息 发送的通道为/topic/callback
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //messagingTemplate.convertAndSend("/sockets/topic", df.format(new Date()));
        //df.format(new Date())
        sendService.sendMessage(df.format(new Date()));
    }
}
