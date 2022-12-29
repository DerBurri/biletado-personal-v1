package org.biletado.personal.v1.api;


import org.biletado.personal.v1.model.Reservation;
import org.biletado.personal.v1.model.ReservationList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservationsCaller {

    private final RestTemplate restTemplate;

    @Value("${BACKEND_URL}")
    private String url;
    public ReservationsCaller(RestTemplateBuilder builder)
    {
        this.restTemplate = builder.build();
    }

    public List<Reservation> getReservations()
    {
        ReservationList wrapper = restTemplate.getForObject(url + "/reservations/", ReservationList.class);

        List<Reservation> list = wrapper.getReservations();
        return list;
    }
}
