package com.eraytasay.weather.service.currentdata.lib.service.consumer.openweather;

import com.eraytasay.weather.service.currentdata.lib.dto.data.CurrentWeatherData;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithCoordinates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.eraytasay.weather.service.currentdata.lib.service.util.RestTemplateHelper.getUrl;
import static com.eraytasay.weather.service.currentdata.lib.service.util.RestTemplateHelper.getUrlParameters;

@Component
public class CurrentWeatherDataConsumer {
    private final RestTemplate m_restTemplate;

    @Value("${api.openweather.weather.currentdata.url}")
    private String m_url;

    public CurrentWeatherDataConsumer(RestTemplate restTemplate)
    {
        m_restTemplate = restTemplate;
    }

    public CurrentWeatherData getCurrentWeatherDataByCoordinates(WeatherDataQueryWithCoordinates weatherDataQueryWithCoordinates)
    {
        var urlParameters = getUrlParameters(weatherDataQueryWithCoordinates);
        var url = getUrl(this.m_url, urlParameters);

        return m_restTemplate.getForObject(url, CurrentWeatherData.class, urlParameters);
    }
}
