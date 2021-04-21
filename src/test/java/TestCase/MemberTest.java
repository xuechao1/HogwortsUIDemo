package TestCase;

import com.sunline.page.BasePage;
import com.sunline.page.ContactPage;
import com.sunline.page.MainPage;
import com.sunline.page.WeChat;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author： Steven Emerson
 * @date： 2021/4/18 16:06
 * @description： TODO
 * @version: 1.0
 */
public class MemberTest {


    @Test
    void add() {
        String accountId = "seveniruby_" + System.currentTimeMillis();
        String username = "seveniruby";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0, 11);
        // TODO: 登录企业微信
        WeChat weChat = new WeChat();
        MainPage mainPage = weChat.login();
        // TODO: 进入通讯录
        ContactPage contactPage = mainPage.addMember();
        // TODO :添加成员 头像（有无） 姓名（中英文 特殊字符 长度） 账号（唯一性 命名规则） 手机（正确 错误 重复）
        // TODO: 参数化  排列组合
        contactPage.addMember(username, accountId, mobile, null);
        // TODO: 保存

        // TODO: 判断结果  若干断言
        contactPage.search(accountId);
        String s = contactPage.getMember();
        assertThat(s, equalTo("123"));
    }

    @Test
    void addLink() {
        // TODO:测试数据
        String accountId = "seveniruby_" + System.currentTimeMillis();
        String username = "seveniruby";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0, 11);
        // TODO:测试步骤
        String res = new WeChat().openWeb().login().addMember().addMember(
                username, accountId, mobile, null
        ).search(accountId).getMember();
        // TODO:断言
        assertThat(res, equalTo(username));
    }

    @Test
    void departAdd() {
        // TODO:测试数据
        String departName = "dx5_" + System.currentTimeMillis();
        // TODO:测试步骤
        String res = new WeChat().openWeb().login().toContactPage().addDepart(departName, "定向5期").getTips();
        // TODO:断言
        assertThat(res, equalTo("新建部门成功"));
    }

    @Test
    void uploadPhoto() {
        // TODO:测试数据
        String accountId = "seveniruby_" + System.currentTimeMillis();
        String username = "seveniruby";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0, 11);
        String imagePath = "C:\\Users\\Administrator\\Desktop\\stand.jpg";
        // TODO:测试步骤
        String res = new WeChat().openWeb().login().addMember().uploadPhoto(
                username, accountId, mobile, imagePath,null).getTips();
        // TODO:断言
        assertThat(res,equalTo("保存成功"));
    }

    @Test
    void delete(){
        try {
            // TODO:测试数据
            // TODO:测试步骤
            String res = new WeChat().openWeb().login().toContactPage().delete().getTips();
            // TODO:
            Thread.sleep(1000);
            assertThat(res,equalTo("正在删除..."));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
