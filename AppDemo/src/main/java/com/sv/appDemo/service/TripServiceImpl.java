package com.sv.appDemo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sv.appDemo.model.Trip;

@Service
public class TripServiceImpl implements ITripService {

    private List<Trip> lista = null;

    public TripServiceImpl() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<>();

        try {
            Trip trip1 = new Trip();
            trip1.setId(1L);
            trip1.setNombre("Rapel en Volcatenando");
            trip1.setDescripcion("Hacer rapel en los circuitos de Volcatenango");
            trip1.setFecha(sdf.parse("10-05-2022"));
            trip1.setCosto(5.0);
            trip1.setDestacado(1);
            trip1.setImagen("trip1.jpg");
            lista.add(trip1);

            Trip trip2 = new Trip();
            trip2.setId(2L);
            trip2.setNombre("Deslizadero en El picnic");
            trip2.setDescripcion("Deslizarte en un divertido tovagon sobre la colina");
            trip2.setFecha(sdf.parse("10-05-2022"));
            trip2.setCosto(5.0);
            trip2.setDestacado(1);
            trip2.setImagen("trip2.jpg");
            lista.add(trip2);

            Trip trip3 = new Trip();
            trip3.setId(3L);
            trip3.setNombre("Comida y Flores");
            trip3.setDescripcion("Disfrutar de un amplio jardín el cual podras comprar");
            trip3.setFecha(sdf.parse("10-05-2022"));
            trip3.setCosto(1.0);
            trip3.setDestacado(0);
            trip3.setImagen("trip3.jpg");
            lista.add(trip3);

            Trip trip4 = new Trip();
            trip4.setId(4L);
            trip4.setNombre("Caminatas");
            trip4.setDescripcion("Disfruta hacer senderismo por las montañas chaletacas");
            trip4.setFecha(sdf.parse("01-02-2022"));
            trip4.setCosto(10.0);
            trip4.setDestacado(1);
            trip4.setImagen("trip4.jpg");
            lista.add(trip4);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> buscarTodos() {
        return lista;
    }

    @Override
    public Trip buscarPorId(Long idTrip) {
        for (Trip t : lista) {
            if (t.getId().equals(idTrip)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void guardar(Trip trip) {
        if (trip.getId() == null) {
            Long nuevoId = (long) (lista.size() + 1);
            trip.setId(nuevoId);
            lista.add(trip);
        } else {

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId().equals(trip.getId())) {
                    lista.set(i, trip);
                    break;
                }
            }
        }
        
        System.out.println(" Trip guardado en el servicio:");
        System.out.println("   - ID: " + trip.getId());
        System.out.println("   - Nombre: " + trip.getNombre());
        System.out.println("   - Descripción: " + trip.getDescripcion());
        System.out.println("   - Detalles: " + trip.getDetalles());
        System.out.println("   - Fecha: " + trip.getFecha());
        System.out.println("   - Costo: " + trip.getCosto());
        System.out.println("   - Categoría: " + (trip.getCategoria() != null ? trip.getCategoria().getNombre() : "ninguna"));
        System.out.println("   - Destacado: " + trip.getDestacado());
        System.out.println("   - Imagen: " + trip.getImagen());
        System.out.println("   - Total de viajes ahora: " + lista.size());
    }

    @Override
    public void eliminar(Long idTrip) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(idTrip)) {
                Trip eliminado = lista.remove(i);
                System.out.println(" Trip eliminado: " + eliminado.getNombre());
                System.out.println("   - Total de viajes ahora: " + lista.size());
                return;
            }
        }
        System.out.println(" No se encontró trip con ID: " + idTrip);
    }
}