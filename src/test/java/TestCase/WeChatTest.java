package TestCase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author： Steven Emerson
 * @date： 2021/4/15 22:56
 * @description： TODO
 * @version: 1.0
 */
@DisplayName("cookie 登录企业微信，并自动化添加成员")
public class WeChatTest {

    static WebDriver driver;

    @BeforeAll
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\data\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame#contacts");
    }

    @AfterAll
    public static void closeBrwoser() {
        driver.quit();
    }

    @Test
    @DisplayName("扫码登录获取cookie，并生成到本地")
    void saveCookie() throws Exception {
        driver.get("https://work.weixin.qq.com/wework_admin/frame#contacts");
        Thread.sleep(10000);  // 手机手动扫码登录
        driver.navigate().refresh(); // 刷新界面
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new  File("cookie.yaml"),cookies);
        cookies.forEach(cookie -> System.out.println(cookie.getName()+":"+cookie.getValue()));
    }

    @Test
    @DisplayName("使用cookie登录web页面")
    void cookieLogin() throws Exception {
        driver.get("https://work.weixin.qq.com/wework_admin/frame#contacts");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String,Object>> cookies = objectMapper.readValue(new File("cookie.yaml"),typeReference);
        cookies.forEach(cookie->{
            driver.manage().addCookie(new Cookie(cookie.get("name").toString(),cookie.get("value").toString()));
        });
        driver.navigate().refresh(); // 刷新界面
        Thread.sleep(3000);
        driver.findElements(By.xpath("//*[@class='qui_btn ww_btn js_add_member']")).get(1).click();
        String phone = makePhone();
        long timeStamp = System.currentTimeMillis();
        driver.findElement(By.id("username")).sendKeys(phone);
        driver.findElement(By.id("memberAdd_acctid")).sendKeys(phone);
        driver.findElement(By.id("memberAdd_phone")).sendKeys(phone);
        driver.findElements(By.xpath("//*[@class='qui_btn ww_btn js_btn_save']")).get(1).click();
        Thread.sleep(1500);
    }

    String makePhone(){
        //给予真实的初始号段，号段是在百度上面查找的真实号段
         String[] start = {"133", "149", "153", "173", "177",
                         "180", "181", "189", "199", "130", "131", "132",
                         "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "134", "135",
                         "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172",
                         "178", "182", "183", "184", "187", "188", "198", "170", "171"};
         //随机出真实号段   使用数组的length属性，获得数组长度，
         //通过Math.random（）*数组长度获得数组下标，从而随机出前三位的号段
         String phoneFirstNum = start[(int) (Math.random() * start.length)];
         //随机出剩下的8位数
         String phoneLastNum = "";
         //定义尾号，尾号是8位
         final int LENPHONE = 8;
         //循环剩下的位数
         for (int i = 0; i < LENPHONE; i++) {
                 //每次循环都从0~9挑选一个随机数
                 phoneLastNum += (int) (Math.random() * 10);
             }
         //最终将号段和尾数连接起来
         String phoneNum = phoneFirstNum + phoneLastNum;
         System.out.println(phoneNum);
         return phoneNum;
    }

}
