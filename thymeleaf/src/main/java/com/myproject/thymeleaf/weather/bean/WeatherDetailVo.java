package com.myproject.thymeleaf.weather.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 一个一维数组 String(22)，共有23个元素。<br />String(0) 到 String(4)：省份，城市，城市代码，城市图片名称，最后更新时间。String(5) 到 String(11)：
 * 当天的 气温，概况，风向和风力，天气趋势开始图片名称(以下称：图标一)，天气趋势结束图片名称(以下称：图标二)，现在的天气实况，天气和生活指数。String(12) 到 String(16)：
 * 第二天的 气温，概况，风向和风力，图标一，图标二。String(17) 到 String(21)：第三天的 气温，概况，风向和风力，图标一，图标二。String(22) 被查询的城市或地区的介绍
 *
 * @author zhanjianjian
 * @since 2021/6/11
 */
@Data
public class WeatherDetailVo implements Serializable {

    String province;
    String city;
    String cityCode;
    String cityImage;
    String updateTime;

    String temperatureToday;
    String overviewToday;
    String windDirectionToday;
    String startPictureToday;
    String endPictureToday;
    String liveWeather;
    String liveIndex;

    String temperatureTomorrow;
    String overviewTomorrow;
    String windDirectionTomorrow;
    String startPictureTomorrow;
    String endPictureTomorrow;

    String temperatureThird;
    String overviewThird;
    String windDirectionThird;
    String startPictureThird;
    String endPictureThird;

    String cityIntroduction;

}
