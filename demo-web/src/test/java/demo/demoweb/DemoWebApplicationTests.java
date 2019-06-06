package demo.demoweb;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import demo.JPush.GetPushPayload;
import demo.JPush.JPushParams;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoWebApplicationTests {

    @Test
    public void contextLoads() {

    }
    @Test
    public void testPush(){
        List<String> list=new ArrayList<>();
        list.add("1");
        /**
         * push：推送
         * */
        final String APP_KEY="7b4b94cca0d185d611e53cca"; //1a7350dae617197dd11c052b
        final String MASTER_SECRET="860803cf613ed54aa3b941a8";
        final String TITLE="title";
        final String ALERT="alertcontent";
        final String REGISTRATION_ID="0900e8d85ef";

        //push客户端配置 连接时长 ===
        ClientConfig clientConfig=ClientConfig.getInstance();
        //获取推送客户端对象
        JPushClient pushClient=new JPushClient(JPushParams.MASTER_SECRET,JPushParams.APP_KEY,null,clientConfig);
        PushPayload pushPayload= GetPushPayload.sendMessage("10");
        try{
            PushResult result=pushClient.sendPush(pushPayload);
        }catch(APIConnectionException e){
            System.out.println("error:"+pushPayload.getSendno()+":"+e);
        }catch(APIRequestException e){
            System.out.println("HTTPSTATUS:"+e.getStatus());
            System.out.println("ERRORCODE:"+e.getErrorCode());
            System.out.println("ERRORMESSAGE:"+e.getErrorMessage());
            System.out.println("SENDNO:"+pushPayload.getSendno()+":"+e);
        }finally{
            pushClient.close();
        }
    }

    @Test
    public void testMd5() throws NoSuchAlgorithmException {
        MessageDigest md5=MessageDigest.getInstance("MD5");//声明为MD5算法
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String newStr=base64Encoder.encode(md5.digest("123456".getBytes()));
        System.out.println("加密后的密文:"+newStr);
        // 4QrcOUm6Wau+VuBX8g+IPg==
    }

    @Test
    public void testTess4(){
        File imageFile = new File("D:/Temp/test.png");
        ITesseract instance = new Tesseract();
        instance.setDatapath("D:\\down\\d\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testHtmlUnit(){
        WebClient client=new WebClient(BrowserVersion.CHROME);//新建模拟谷歌Chrome浏览器的浏览器客户端对象

        client.getOptions().setThrowExceptionOnScriptError(false);//当执行js出错的时候是否抛出异常
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        client.getOptions().setActiveXNative(false);
        client.getOptions().setCssEnabled(false);//是否启用CSS
        client.getOptions().setJavaScriptEnabled(true);//是否启用js
        client.setAjaxController(new NicelyResynchronizingAjaxController()); //设置支持AJAX

        HtmlPage page=null;
        try{
            page=client.getPage("http://ent.sina.com.cn/film/");//加载网页
        }catch (Exception e){
            e.printStackTrace();
        }
        client.close();
        client.waitForBackgroundJavaScript(3000);

        String pageXml=page.asXml();//直接将加载玩成的xml转换位字符串
        Document doc= Jsoup.parse(pageXml);

        List<Element> infoListEle = doc.getElementById("feedCardContent").getElementsByAttributeValue("class", "feed-card-item");//获取元素节点等
        infoListEle.forEach(element -> {
            System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").text());
            System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").attr("href"));
        });
        System.out.println("--End");
    }
}
