package com.myproject.thymeleaf.weather.controller;

import com.myproject.thymeleaf.weather.bean.WeatherDetailVo;
import com.myproject.thymeleaf.weather.webxml.ArrayOfString;
import com.myproject.thymeleaf.weather.webxml.WeatherWebService;
import com.myproject.thymeleaf.weather.webxml.WeatherWebServiceSoap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanjianjian
 * @since 2021/6/11
 */
@Controller
@RequestMapping("/rest/weather")
public class WeatherRest {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/queryCityWeather")
    public String getByCity(Model model, @RequestParam String city) {

        WeatherDetailVo weatherDetailVo = (WeatherDetailVo) redisTemplate.opsForValue().get(city);
        if (weatherDetailVo == null) {
            WeatherWebService weatherWebService = new WeatherWebService();
            WeatherWebServiceSoap weatherWebServiceSoap = weatherWebService.getWeatherWebServiceSoap();
            ArrayOfString arrayOfString = weatherWebServiceSoap.getWeatherbyCityName(city);
            List<String> string = arrayOfString.getString();
            weatherDetailVo = new WeatherDetailVo();
            buildWeatherDetail(string, weatherDetailVo);
            if (!string.get(0).contains("访问被限制")) {
                redisTemplate.opsForValue().set(city, weatherDetailVo, 300, TimeUnit.MINUTES);
            }
        }
        model.addAttribute("weatherDetailVo", weatherDetailVo);
        return "weatherDetail";
    }

    private void buildWeatherDetail(List<String> detailList, WeatherDetailVo weatherDetailVo) {

        weatherDetailVo.setProvince(detailList.get(0));
        weatherDetailVo.setCity(detailList.get(1));
        weatherDetailVo.setCityCode(detailList.get(2));
        weatherDetailVo.setCityImage(detailList.get(3));
        weatherDetailVo.setUpdateTime(detailList.get(4));

        weatherDetailVo.setTemperatureToday(detailList.get(5));
        weatherDetailVo.setOverviewToday(detailList.get(6));
        weatherDetailVo.setWindDirectionToday(detailList.get(7));
        weatherDetailVo.setStartPictureToday(detailList.get(8));
        weatherDetailVo.setEndPictureToday(detailList.get(9));
        weatherDetailVo.setLiveWeather(detailList.get(10));
        weatherDetailVo.setLiveIndex(detailList.get(11));

        weatherDetailVo.setTemperatureTomorrow(detailList.get(12));
        weatherDetailVo.setOverviewTomorrow(detailList.get(13));
        weatherDetailVo.setWindDirectionTomorrow(detailList.get(14));
        weatherDetailVo.setStartPictureTomorrow(detailList.get(15));
        weatherDetailVo.setEndPictureTomorrow(detailList.get(16));

        weatherDetailVo.setTemperatureThird(detailList.get(17));
        weatherDetailVo.setOverviewThird(detailList.get(18));
        weatherDetailVo.setWindDirectionThird(detailList.get(19));
        weatherDetailVo.setStartPictureThird(detailList.get(20));
        weatherDetailVo.setEndPictureThird(detailList.get(21));

        weatherDetailVo.setCityIntroduction(detailList.get(22));
    }
}
