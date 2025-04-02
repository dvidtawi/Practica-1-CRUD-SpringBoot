package com.universidad.service; // Define el paquete al que pertenece esta interfaz

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import org.springframework.stereotype.Service;

import java.util.List; // Importa la interfaz List para manejar listas
public interface IEstudianteService { // Define la interfaz IEstudianteService
    
    List<EstudianteDTO> obtenerTodosLosEstudiantes(); // Método para obtener una lista de todos los EstudianteDTO
    EstudianteDTO obtenerEstudiantePorId(Long id);
    void saveOrUpdate(EstudianteDTO estudiante);
    void delete(Long id);
}