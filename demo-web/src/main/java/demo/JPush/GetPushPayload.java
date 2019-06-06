package demo.JPush;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import java.util.Collection;

import static org.reflections.util.ConfigurationBuilder.build;

public class GetPushPayload {
    /**
     * 获取android，registration的方式
     * */
    public static PushPayload getByRegistrationsAndNotification(Collection<String> registrations, String alter,String title){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registrations))
                .setNotification(Notification.android(alter,title,null))
                .build();
    }

    /**
     * 根据别名和通知获取PushPayload
     * */
    public static PushPayload getByAliasAndNotification(Collection<String> alias,String alter,String title){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(alter,title,null))
                .build();
    }
    /**
     * 推送消息
     * */
    public static PushPayload sendMessage(String alia){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alia))
                .setMessage(Message.newBuilder().setMsgContent("笑笑笑笑").addExtra("type","1").build())
                .build();
    }
}
