package com.sv.appDemo.service;

import java.util.List;
import com.sv.appDemo.model.Trip;

public interface ITripService {
    List<Trip> buscarTodos();
    Trip buscarPorId(Long idTrip);
    void guardar(Trip trip);
    void eliminar(Long idTrip);
}