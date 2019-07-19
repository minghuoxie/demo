package demo;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import demo.htlp.ConnHelp;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class TestMain {
    /**
     * https://github.com/minghuoxie/demo.git
     *
     * */
    public static void main(String[] args){
        testHuiShuiTieBa("http://tieba.baidu.com/f?kw=%BB%DD%CB%AE&tpl=5");
    }

    public static void testImg(){
        File imageFile = new File("D:/Temp/test.png");
        ITesseract instance = new Tesseract();
        instance.setDatapath("D:\\down\\d\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
        instance.setLanguage("chi_sim");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }


    //惠水天吧
    public static void testHuiShuiTieBa(String url){
        WebClient client=new WebClient(BrowserVersion.CHROME);//新建模拟谷歌Chrome浏览器的浏览器客户端对象

        client.getOptions().setThrowExceptionOnScriptError(false);//当执行js出错的时候是否抛出异常
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        client.getOptions().setActiveXNative(true);
        client.getOptions().setCssEnabled(false);//是否启用CSS
        client.getOptions().setJavaScriptEnabled(true);//是否启用js
        client.setAjaxController(new NicelyResynchronizingAjaxController()); //设置支持AJAX

        HtmlPage page=null;
        try{
            page=client.getPage(url);//加载网页
        }catch (Exception e){
            e.printStackTrace();
        }
        client.close();
        client.waitForBackgroundJavaScript(3000);
        String pageXml=page.asXml();//直接将加载玩成的xml转换位字符串
        pageXml=pageXml.replaceAll("<!--","");
        pageXml=pageXml.replaceAll("-->","");

        Document doc= Jsoup.parse(pageXml);
        Element body=doc.body();
        /**
         * 获取用户信息和吧主 pagelet_aside/pagelet/my_tieba
         * */
        Elements meInfo=body.getElementsByClass("wrap1");


        System.out.println("--end"+meInfo);

        /**
         * 获取帖子列表
         * */
//        Element ulId=body.getElementById("thread_list");
//        for(int i=0;i<ulId.children().size();i++){
//            Element e=ulId.children().get(i);
//            String title= ConnHelp.getTextByTagAndAttr("a","class","j_th_tit ",'C',null,e);
//            String content=ConnHelp.getTextByTagAndAttr("div","class","threadlist_abs threadlist_abs_onlyline ",'C',null,e);
//            System.out.println("title::::"+title);
//            System.out.println("::content:::::"+content);
//            System.out.println();
//        }
    }
}
