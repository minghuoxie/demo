package demo.entity;

public class SocketConstant {
    //webSocket的相关配置
    //链接地址
    public static final String WEBSOCKETPATHPERFIX="/ws-push";
    public static final String WEBSOCKETPATH = "/endpointWisely";
    //消息代理路径
    public static final String WEBSOCKETBROADCASTPATH = "/topic";
    //前端发送给服务端的请求地址
    public static final String FORETOSERVERPATH = "/welcome";
    //服务端生产地址,客户端订阅此地址以接收服务端生产的消息
    public static final String PRODUCERPATH = "/topic/getResponse";
    //点对点消息推送地址前缀
    public static final String P2PPUSHBASEPATH = "/user";
    //点对点消息推送地址后缀,最后的地址为/user/用户识别码/msg
    public static final String P2PPUSHPATH = "/msg";

    /**
     *
     * websocket的常量配置
     * */
    /**
     * 所有配置的前缀
     */
    public final static String CONSTANT_PREFIXE = "/sockets";

    /**
     * stomp 的端点
     */
    public final static String STOMP_ENDPOINT = CONSTANT_PREFIXE + "/endpoint";

    /**
     * 全局消息订阅的 Broker 名称
     */
    public final static String SIMPLE_BROKER_TOPIC = CONSTANT_PREFIXE + "/topic";

    /**
     * 点对点消息订阅的 Broker 名称
     */
    public final static String SIMPLE_BROKER_USER = CONSTANT_PREFIXE + "/user";

    /**
     * 全局消息前缀
     */
    public final static String APPLICATION_DESTINATION_PREFIXE = CONSTANT_PREFIXE + "/push";

    /**
     * 点对点订阅后缀
     */
    public final static String USER_DESTINATION_SUFFIX = "/message";

    /**
     * 广播订阅地址
     */
    public final static String TOPIC_DESTINATION = CONSTANT_PREFIXE + "/topic/getResponse";

    /**
     * 用户回话存储键
     */
    public final static String USER_INFO_KEY = "WEB_SOCKET_SESSION";
}
