package com.sunline.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

/**
 * @author： Steven Emerson
 * @date： 2021/4/18 22:09
 * @description： TODO
 * @version: 1.0
 */
public class ContactPage extends BasePage {

    public ContactPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ContactPage addMember(String name, String account, String mobile, HashMap<String, String> data) {
        sendKeys(By.name("username"), name);
        sendKeys(By.name("acctid"), account);
        sendKeys(By.name("mobile"), mobile);
        click(By.linkText("保存"));
        return this;
    }

    public ContactPage search(String accountId) {
        sendKeys(By.id("memberSearchInput"), accountId);
        return this;
    }

    public ContactPage delete() {
        driver.findElements(By.xpath("//*[@id='member_list']//input")).get(1).click();
        click(By.linkText("删除"));
        click(By.linkText("确认"));
        return this;
    }

    public ContactPage addDepart(String name, String department) {
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"), name);
        click(By.linkText("选择所属部门"));
        driver.findElement(By.tagName("form")).findElement(By.linkText(department)).click();
        click(By.linkText("确定"));
        return this;
    }

    public String getTips() {
        String res = driver.findElement(By.id("js_tips")).getText();
        return res;
    }

    public String getMember() {
        String name = driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
        return name;
    }

    public ContactPage uploadPhoto(String name, String account, String mobile,String path, HashMap<String, String> data) {
        sendKeys(By.name("username"), name);
        sendKeys(By.name("acctid"), account);
        sendKeys(By.name("mobile"), mobile);
        click(By.xpath("//*[@class='ww_icon ww_icon_CameraWhiteSmall']"));
        sendKeys(By.cssSelector("[type='file']"), path);
        click(By.xpath("//*[@class='qui_btn ww_btn ww_btn_Blue js_save']"));
        click(By.linkText("保存"));
        return this;
    }

}
