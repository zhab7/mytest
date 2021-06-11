package com.myproject.thymeleaf.weather;

import com.myproject.thymeleaf.weather.webxml.ArrayOfString;
import com.myproject.thymeleaf.weather.webxml.WeatherWebService;
import com.myproject.thymeleaf.weather.webxml.WeatherWebServiceSoap;

import java.util.List;

/**
 * @author zhanjianjian
 * @since 2021/6/10
 */

public class WeatherTest {

    public static void main(String[] args) {
        WeatherWebService weatherWebService = new WeatherWebService();
        WeatherWebServiceSoap webServiceSoap = weatherWebService.getWeatherWebServiceSoap();
        ArrayOfString city = webServiceSoap.getWeatherbyCityName("上海");


        if (city != null) {
            List<String> string = city.getString();
            for (String s : string) {
                System.out.println("s = " + s);
            }
        }


    }
}
