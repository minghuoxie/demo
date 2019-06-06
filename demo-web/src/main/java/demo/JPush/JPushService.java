package demo.JPush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JPushService {

    public boolean pushClient(Collection<Integer> userIds,String alter,String title){
        List<String> list=new ArrayList<>();
        list.add("1");
        boolean returnResult=true;
        //push客户端配置 连接时长 ===
        ClientConfig clientConfig=ClientConfig.getInstance();
        //获取推送客户端对象
        JPushClient pushClient=new JPushClient(JPushParams.MASTER_SECRET,JPushParams.APP_KEY,null,clientConfig);
        PushPayload pushPayload=GetPushPayload.getByAliasAndNotification(list,"content","title");
        try{
            PushResult result=pushClient.sendPush(pushPayload);
        }catch(APIConnectionException e){
            System.out.println("error:"+pushPayload.getSendno()+":"+e);
            returnResult=false;
        }catch(APIRequestException e){
            System.out.println("HTTPSTATUS:"+e.getStatus());
            System.out.println("ERRORCODE:"+e.getErrorCode());
            System.out.println("ERRORMESSAGE:"+e.getErrorMessage());
            System.out.println("SENDNO:"+pushPayload.getSendno()+":"+e);
            returnResult=false;
        }finally{
            pushClient.close();
        }
        return returnResult;
    }

    /**
     * 根据用户id获取对应的registrayionid
     * */
    private Collection<String> getRegistrations(Collection<Integer> userIds){
        return null;
    }
}
