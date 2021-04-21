package com.sunline.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author： Steven Emerson
 * @date： 2021/4/18 22:11
 * @description： TODO
 * @version: 1.0
 */
public class MainPage extends BasePage{

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ContactPage addMember(){
        click(By.linkText("添加成员"));
        return new ContactPage(driver);
    }

    public ContactPage toContactPage(){
        click(By.linkText("通讯录"));
        return new ContactPage(driver);
    }

    public void sendMsg(){

    }

    public void importContact(){

    }

}
