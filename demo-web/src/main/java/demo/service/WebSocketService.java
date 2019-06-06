package demo.service;

import demo.entity.SocketConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

public class WebSocketService {
////    @Autowired
////    private SimpMessagingTemplate template;
//
//    //广播，发送消息给所有用户
//    public void sendMsg(String msg) {
//        template.convertAndSend(SocketConstant.PRODUCERPATH, msg);
//    }
//
//    //发送给指定用户
//    public void sendUsers(List<String> users, String msg) {
//        users.forEach(userName -> {
//            template.convertAndSendToUser(userName, SocketConstant.P2PPUSHPATH, msg);
//        });
//    }
//
//    //发送给指定用户
//    public void sendUsers(String userName, String msg) {
//        template.convertAndSendToUser(userName, SocketConstant.P2PPUSHPATH, msg);
//    }
}
