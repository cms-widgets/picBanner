/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huotu.hotcms.widget.picBanner;

import com.huotu.hotcms.service.entity.support.WidgetIdentifier;
import com.huotu.hotcms.widget.ComponentProperties;
import com.huotu.hotcms.widget.Widget;
import com.huotu.hotcms.widget.WidgetStyle;
import me.jiangcai.lib.resource.service.ResourceService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * @author CJ
 */
public class WidgetInfo implements Widget{
    /*
     * 指定风格的模板类型 如：html,text等
     */
    public static final String VALID_STYLE_TEMPLATE = "styleTemplate";
    public static final String VALID_PC_IMG = "pcImg";
    public static final String VALID_MOBILE_IMG = "mobileImg";
    public static final String VALID_PIC_URL = "linkUrl";

    @Override
    public String groupId() {
        return "com.huotu.hotcms.widget.picBanner";
    }

    @Override
    public String widgetId() {
        return "picBanner";
    }


    @Override
    public String name(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return "图片条幅";
        }
        return "picBanner";
    }

    @Override
    public String description() {
        return "这是一个图片条幅，你可以对组件进行自定义修改。";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return "这是一个图片条幅，你可以对组件进行自定义修改。";
        }
        return "This is a picBanner,  you can make custom change the component.";
    }

    @Override
    public int dependBuild() {
        return 0;
    }

    @Override
    public WidgetStyle[] styles() {
        return new WidgetStyle[]{new DefaultWidgetStyle()};
    }



    @Override
    public Map<String, Resource> publicResources() {
        Map<String, Resource> map = new HashMap<>();
        map.put("thumbnail/defaultStyleThumbnail.png",new ClassPathResource("thumbnail/defaultStyleThumbnail.png",getClass().getClassLoader()));
        map.put("thumbnail.png",new ClassPathResource("thumbnail.png",getClass().getClassLoader()));
        map.put("js/picBanner.js",new ClassPathResource("js/picBanner.js",getClass().getClassLoader()));
        return map;
    }

    @Override
    public Resource widgetDependencyContent(MediaType mediaType) {
        if (mediaType.isCompatibleWith(Javascript)){
            return new ClassPathResource("js/picBanner.js", getClass().getClassLoader());
        }
        return null;
    }


    @Override
    public void valid(String styleId, ComponentProperties componentProperties) throws IllegalArgumentException {
        WidgetStyle[] widgetStyles = styles();
        boolean flag = false;
        if (widgetStyles == null || widgetStyles.length < 1) {
            throw new IllegalArgumentException();
        }
        for (WidgetStyle ws : widgetStyles) {
            if ((flag = ws.id().equals(styleId))) {
                break;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException();
        }
        //加入控件独有的属性验证
        String pcImg = (String) componentProperties.get(VALID_PC_IMG);
        String mobileImg = (String) componentProperties.get(VALID_MOBILE_IMG);
        String picUrl = (String) componentProperties.get(VALID_PIC_URL);

        if (pcImg == null || mobileImg == null || picUrl == null ||  pcImg.equals("") || mobileImg.equals("")
                || picUrl.equals("")){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Class springConfigClass() {
        return null;
    }

    @Override
    public ComponentProperties defaultProperties(ResourceService resourceService) {
        ComponentProperties properties = new ComponentProperties();
        try {
            WidgetIdentifier identifier = new WidgetIdentifier(groupId(), widgetId(), version());
            properties.put(VALID_PC_IMG, resourceService.getResource("widget/" + identifier.toURIEncoded()
                    + "/" + "thumbnail/defaultStyleThumbnail.png").httpUrl().toURI().toString());
            properties.put(VALID_MOBILE_IMG, resourceService.getResource("widget/" + identifier.toURIEncoded()
                    + "/" + "thumbnail/defaultStyleThumbnail.png").httpUrl().toURI().toString());
            properties.put(VALID_PIC_URL, "http://www.huobanplus.com");
        }catch (Exception e){

        }
        return properties;
    }

}
