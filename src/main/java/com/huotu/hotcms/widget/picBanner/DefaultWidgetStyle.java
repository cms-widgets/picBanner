/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huotu.hotcms.widget.picBanner;

import com.huotu.hotcms.widget.WidgetStyle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

/**
 * @author CJ
 */
public class DefaultWidgetStyle implements WidgetStyle{

    @Override
    public String id() {
        return "picBannerDefaultStyle";
    }

    @Override
    public String name() {
        return "默认";
    }

    @Override
    public String name(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return name();
        }
        return "default";
    }

    @Override
    public String description() {
        return "默认的样式，该样式展示了图库下的若干个图片，并且每个图片的链接为用户选择的链接内容，" +
                "如果没有选择连接内容，图片则不具备链接功能";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return description();
        }
        return "The default style, the style shows a number of pictures gallery, " +
                "and the link to each picture for the user to choose the link content, " +
                "if you do not choose the contents of the connection, the picture does not have the link function";
    }

    @Override
    public Resource thumbnail() {
        return new ClassPathResource("/thumbnail/defaultStyleThumbnail.png", getClass().getClassLoader());
    }

    @Override
    public Resource previewTemplate() {
        return null;
    }

    @Override
    public Resource browseTemplate() {
        return new ClassPathResource("/template/defaultStyleBrowseTemplate.html", getClass().getClassLoader());
    }

}
