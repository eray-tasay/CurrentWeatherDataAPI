package com.eraytasay.service.weather.current.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "current_weather_data_api_queries")
public class CurrentWeatherDataApiQuery {
    @Id
    @Column(name = "current_weather_data_api_query_id")
    public int id;

    @Column(nullable = false)
    public int count;

    @Column(name = "query_datetime", nullable = false)
    public LocalDateTime queryDatetime = LocalDateTime.now();

    @JoinColumn(name = "key_query_id")
    @ManyToOne
    public KeyQuery key;
}
