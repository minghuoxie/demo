package demo.config;

import demo.config.interceptors.WebSocketChannelInterceptor;
import demo.config.interceptors.WebSocketHandshakeInterceptor;
import demo.entity.SocketConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * @EnableWebSocketMessageBroker 注解用于开启使用STOMP协议传输基于代理(MessageBroker)的消息
     * 这时候控制器controller开始支持@MessageMapping，就像使用@requestMapping一样
     *
     * **/

    @Autowired
    private WebSocketChannelInterceptor webSocketChannelInterceptor;

    @Autowired
    private WebSocketHandshakeInterceptor webSocketHandshakeInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        //服务端发送消息给客户端的域,多个用逗号隔开
//        //WEBSOCKETBROADCASTPATH 消息代理路径
//        config.enableSimpleBroker(SocketConstant.WEBSOCKETBROADCASTPATH,SocketConstant.P2PPUSHBASEPATH);
//        //定义一对一推送的时候的前缀
//        config.setUserDestinationPrefix(SocketConstant.P2PPUSHBASEPATH);
//        //定义websocket发送前缀
//        config.setApplicationDestinationPrefixes(SocketConstant.WEBSOCKETPATHPERFIX);

        /*config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app"); //定义前缀
        **/
        //服务端发送消息给客户端的域,多个用逗号隔开
        ///SIMPLE_BROKER_TOPIC:/sockets/topic   SIMPLE_BROKER_USER:/sockets/user
        config.enableSimpleBroker(SocketConstant.SIMPLE_BROKER_TOPIC, SocketConstant.SIMPLE_BROKER_USER);
        //定义一对一推送的时候前缀  SIMPLE_BROKER_USER:/sockets/user
        config.setUserDestinationPrefix(SocketConstant.SIMPLE_BROKER_USER);
        //全局使用的消息前缀（客户端订阅路径上会体现出来）APPLICATION_DESTINATION_PREFIXE:/sockets/push
        config.setApplicationDestinationPrefixes(SocketConstant.APPLICATION_DESTINATION_PREFIXE);

    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketChannelInterceptor);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
      //  registry.addEndpoint(SocketConstant.WEBSOCKETPATH).withSockJS();
        //前端web连接的路劲 http://localhost:8080/my-websocket
       // registry.addEndpoint("/my-websocket").withSockJS(); //使用SockJS协议

        //STOMP_ENDPOINT = /sockets/endpoint
        registry.addEndpoint(SocketConstant.STOMP_ENDPOINT)
                .setAllowedOrigins("*")
                .addInterceptors(webSocketHandshakeInterceptor)
                .withSockJS();
    }
}
