package com.test.demo;

import com.baidu.aip.ocr.AipOcr;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.HashMap;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/14 9:15
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
public class TestOCRDemo {

    @Test
    public void testOCR() throws Exception{
        ITesseract instance = new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径
        instance.setDatapath("D:\\ProgramSoft\\SpringWebDemo\\src\\tessdata");

        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        instance.setLanguage("chi_sim");

        // 指定识别图片
        File imgDir = new File("E:/uploadExcel/2.png");
        long startTime = System.currentTimeMillis();
        String ocrResult = instance.doOCR(imgDir);

        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }


    @Test
    public void testBaiDuOCR(){
        //设置APPID/AK/SK
       final String APP_ID = "15752277";
       final String API_KEY = "7qQQxGanh2NDCWFqc7i2GTnb";
       final String SECRET_KEY = "FEAXK4rCKyqP1ih9ny4majgDTGRkk0Dw";

        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String path = "E:/uploadExcel/2.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
    }
}
