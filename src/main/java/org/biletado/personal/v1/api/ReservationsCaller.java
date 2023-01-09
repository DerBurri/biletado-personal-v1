package org.biletado.personal.v1.api;


import org.biletado.personal.v1.model.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ReservationsCaller {

    private final RestTemplate restTemplate;

    @Value("${BACKEND_URL}")
    private String url;

    public ReservationsCaller(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public Reservation getReservationsFromId(UUID reservation_id) throws HttpClientErrorException {
        return restTemplate.getForObject(url + reservation_id.toString() + "/", Reservation.class);
    }
}
