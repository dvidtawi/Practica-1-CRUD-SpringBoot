package com.universidad.controller; // Define el paquete al que pertenece esta clase

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.service.IEstudianteService; // Importa la interfaz IEstudianteService del paquete service
import org.springframework.http.HttpStatus;
import com.universidad.service.impl.EstudianteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity de Spring para manejar respuestas HTTP
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring para controladores web

import java.util.List; // Importa la interfaz List para manejar listas

@RestController // Anotación que indica que esta clase es un controlador REST de Spring
@RequestMapping("/api") // Define la ruta base para las solicitudes HTTP a este controlador
public class EstudianteController { // Define la clase EstudianteController

    private final IEstudianteService estudianteService; // Declara una variable final para el servicio de estudiantes

    @Autowired // Anotación que indica que el constructor debe ser usado para inyección de dependencias
    public EstudianteController(IEstudianteService estudianteService) { // Constructor que recibe el servicio de estudiantes
        this.estudianteService = estudianteService; // Asigna el servicio de estudiantes a la variable de instancia
    }

    @GetMapping("/estudiantes") // Anotación que indica que este método maneja solicitudes GET
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() { // Método para obtener una lista de todos los EstudianteDTO
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes(); // Llama al servicio para obtener todos los estudiantes
        return ResponseEntity.ok(estudiantes); // Retorna una respuesta HTTP 200 OK con la lista de estudiantes
    }

    @PostMapping("/estudiantes/save")
    public ResponseEntity<Void> save(@RequestBody EstudianteDTO estudiante) {
        estudianteService.saveOrUpdate(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201 sin cuerpo
    }

    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorId(id);
        if (estudiante == null) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
        return ResponseEntity.ok(estudiante); // 200 + cuerpo
    }

    @PutMapping("/estudiantes/{id}")  // <- Cambio clave: PUT + ID en URL
    public ResponseEntity<EstudianteDTO> update(
            @PathVariable Long id,  // ID desde la URL
            @RequestBody EstudianteDTO estudiante) {
        if (!id.equals(estudiante.getId())) {
            return ResponseEntity.badRequest().build(); // HTTP 400
        }
        estudianteService.saveOrUpdate(estudiante);
        return ResponseEntity.ok(estudiante); // HTTP 200 + cuerpo
    }

    @DeleteMapping("/estudiantes/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

}