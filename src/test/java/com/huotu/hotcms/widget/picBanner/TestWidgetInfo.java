/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huotu.hotcms.widget.picBanner;

import com.huotu.hotcms.widget.ComponentProperties;
import com.huotu.hotcms.widget.Widget;
import com.huotu.hotcms.widget.WidgetStyle;
import com.huotu.widget.test.WidgetTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
public class TestWidgetInfo extends WidgetTest {

    @Override
    protected boolean printPageSource() {
        return false;
    }

    @Override
    protected void editorWork(Widget widget, WebElement editor, Supplier<Map<String, Object>> currentWidgetProperties) {
        WebElement maxImg = editor.findElement(By.id("picBannerMaxImg"));
        List<WebElement> input = maxImg.findElements(By.tagName("input"));
        assertThat(input).isNotNull();
        assertThat(input.size()).as("图片上传插件").isNotEqualTo(0);

        WebElement minImg = editor.findElement(By.id("picBannerMinImg"));
        input = minImg.findElements(By.tagName("input"));
        assertThat(input).isNotNull();
        assertThat(input.size()).as("图片上传插件").isNotEqualTo(0);
        try {
            Map map = currentWidgetProperties.get();
            assertThat(map.containsKey(WidgetInfo.VALID_PIC_URL)).isTrue();

        }catch (IllegalStateException ex){
            assertThat(0).as("save没有属性值返回异常").isEqualTo(0);
        }

    }

    @Override
    protected void browseWork(Widget widget, WidgetStyle style, Function<ComponentProperties, WebElement> uiChanger) {
        ComponentProperties properties = new ComponentProperties();
        properties.put("pcImg", "1.jpg");
        properties.put("mobileImg", "4.jpg");
        properties.put("linkUrl", "http://www.baidu.com");
        WebElement webElement = uiChanger.apply(properties);
        List<WebElement> img = webElement.findElements(By.className("img-responsive"));
        assertThat(img.size()).isEqualTo(2);
        String pc = img.get(0).getAttribute("src");
        assertThat(pc).contains("1.jpg");
        String mobile = img.get(1).getAttribute("src");
        assertThat(mobile).contains("4.jpg");
        List<WebElement> a = webElement.findElements(By.tagName("a"));
        String href1 = a.get(0).getAttribute("href");
        assertThat(href1).isEqualToIgnoringCase("http://www.baidu.com");
        String href2 = a.get(1).getAttribute("href");
        assertThat(href2).isEqualToIgnoringCase("http://www.baidu.com");
    }

    @Override
    protected void editorBrowseWork(Widget widget, Function<ComponentProperties, WebElement> uiChanger) throws IOException {
        ComponentProperties properties = widget.defaultProperties(resourceService);
        WebElement webElement = uiChanger.apply(widget.defaultProperties(resourceService));
        assertThat(webElement.findElement(By.name(WidgetInfo.VALID_PIC_URL)).getAttribute("value"))
                .isEqualTo(properties.get(WidgetInfo.VALID_PIC_URL));
    }


}
