package demo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TestMain {
    /**
     * https://github.com/minghuoxie/demo.git
     *
     * */
    public static void main(String[] args){
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
}
