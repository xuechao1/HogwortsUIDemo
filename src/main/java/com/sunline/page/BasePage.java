package com.sunline.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author： Steven Emerson
 * @date： 2021/4/18 22:29
 * @description： TODO
 * @version: 1.0
 */
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public boolean click(By by){
        // todo: 突然弹窗阻挡 异常处理 流程调整
        // todo: find 找不到 弹窗阻挡 加载延迟
        // todo: click的时候报错
        // todo: click 的时候不生效
        // todo： click的时候弹框阻挡
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            // todo: 解决弹窗
            this.handleAlert();
            return click(by);
        }
        return true;
    }

    public void sendKeys(By by,String content){
        driver.findElement(by).sendKeys(content);
    }

    public void handleAlert(){
        driver.getPageSource();
    }

    public void clickUntil(By by,By next){
        // todo: 用来解决前几次点击不生效，后面点击生效的过程，使用显式等待
    }

}
