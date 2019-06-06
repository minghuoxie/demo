package demo.config.interceptors;

import demo.config.service.WebSocketUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private WebSocketUserServiceImpl webSocketUserService;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        //System.out.println("--------???"+accessor.getSessionId().toString());
        //建立连接
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            webSocketUserService.onlineUser(user);
//            logger.debug(String.format("[ %s : %s ] 上线了!", user.getUserName(), user.getUserId()));
            //webSocketUserService.onlineUser(accessor.getSessionId().toString());
        }
        //销毁连接
        if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
//            webSocketUserService.offlineUser(user);
//            logger.debug(String.format("[ %s : %s ] 下线了!", user.getUserName(), user.getUserId()));
           // webSocketUserService.onlineUser(accessor.getSessionId().toString());
        }
        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel messageChannel, boolean b) {

    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel messageChannel, boolean b, Exception e) {

    }

    @Override
    public boolean preReceive(MessageChannel messageChannel) {
        return false;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel messageChannel) {
        return null;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel messageChannel, Exception e) {

    }
}
