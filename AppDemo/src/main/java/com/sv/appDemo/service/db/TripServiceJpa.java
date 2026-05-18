package com.sv.appDemo.service.db;

import com.sv.appDemo.model.Trip;
import com.sv.appDemo.repository.TripRepository;
import com.sv.appDemo.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
public class TripServiceJpa implements ITripService {
    
    @Autowired
    private TripRepository tripRepository;
    
    @Override
    public List<Trip> buscarTodos() {
        return tripRepository.findAll();
    }
    
    @Override
    public Trip buscarPorId(Long idTrip) {
        return tripRepository.findById(idTrip).orElse(null);
    }
    
    @Override
    public void guardar(Trip trip) {
        tripRepository.save(trip);
    }
    
    @Override
    public void eliminar(Long idTrip) {
        tripRepository.deleteById(idTrip);
    }
}