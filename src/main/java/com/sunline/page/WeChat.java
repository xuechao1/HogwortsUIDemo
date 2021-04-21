package com.sunline.page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author： Steven Emerson
 * @date： 2021/4/18 22:08
 * @description： TODO
 * @version: 1.0
 */
public class WeChat{

    WebDriver driver;

    public WeChat openWeb(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\data\\chromedriver.exe");
        // todo: 支持多浏览器
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        return this;
    }

    public MainPage login(){
        try {
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            List<HashMap<String,Object>> cookies = objectMapper.readValue(new File("cookie.yaml"),typeReference);
            cookies.forEach(cookie->{
                driver.manage().addCookie(new Cookie(cookie.get("name").toString(),cookie.get("value").toString()));
            });
            driver.navigate().refresh(); // 刷新界面
            Thread.sleep(3000);
            return new MainPage(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean loginOut(){
        return true;
    }

    public ContactPage toContactPage(){
        return new ContactPage(driver);
    }

}
