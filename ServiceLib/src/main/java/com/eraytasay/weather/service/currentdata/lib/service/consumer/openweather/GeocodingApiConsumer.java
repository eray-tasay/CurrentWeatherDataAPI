package com.eraytasay.weather.service.currentdata.lib.service.consumer.openweather;

import com.eraytasay.weather.service.currentdata.lib.dto.data.Coord;
import com.eraytasay.weather.service.currentdata.lib.service.exp.NoSuchPlaceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GeocodingApiConsumer {
    private final RestTemplate m_restTemplate;

    @Value("${api.openweather.location.geocoding.name.url}")
    private String m_geocodingByNameUrl;

    @Value("${api.openweather.location.geocoding.code.url}")
    private String m_geocodingByCodeUrl;

    public GeocodingApiConsumer(RestTemplate restTemplate)
    {
        m_restTemplate = restTemplate;
    }

    public Coord getCoordinatesByName(String name, String appid)
    {
        var response = m_restTemplate.exchange(m_geocodingByNameUrl.formatted(name, appid), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Coord>>() {});
        var coords = response.getBody();

        if (coords == null || coords.isEmpty())
            throw new NoSuchPlaceException("No place can be found for given query: %s".formatted(name));

        return coords.getFirst();
    }

    // BURADA KALDIK
    public Coord getCoordinatesByCode(String code, String appid)
    {
        var response = m_restTemplate.exchange(m_geocodingByCodeUrl.formatted(code, appid), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Coord>>() {});
        var coords = response.getBody();

        if (coords == null || coords.isEmpty())
            throw new NoSuchPlaceException("No place can be found for given query: %s".formatted(code));

        return coords.getFirst();
    }
}
