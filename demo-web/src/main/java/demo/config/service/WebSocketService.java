package demo.config.service;

import demo.entity.SocketConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播发给所有在线用户
     *  /sockets/topic/getResponse
     * @param message
     */
    public void sendMessage(Object message) {
        template.convertAndSend(SocketConstant.TOPIC_DESTINATION, message);
    }

    /**
     * 发送给指定用户
     *
     * @param users   用户名List
     * @param message 消息实体
     */
    public void sendMessage(List<String> users, Object message) {
        ///message
        users.forEach(user -> {
            template.convertAndSendToUser(user,SocketConstant.USER_DESTINATION_SUFFIX, message);
        });
    }

    /**
     * 发送给指定用户
     *
     * @param userName 用户名
     * @param message  消息实
     */
    public void sendMessage(String userName, Object message) {
        template.convertAndSendToUser(userName,SocketConstant.USER_DESTINATION_SUFFIX, message);
    }
}
