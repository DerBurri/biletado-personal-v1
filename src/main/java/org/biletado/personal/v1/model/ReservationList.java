package org.biletado.personal.v1.model;

import java.util.ArrayList;
import java.util.List;

public class ReservationList {

    private List<Reservation> reservations;

    public ReservationList() {
        reservations = new ArrayList<>();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
