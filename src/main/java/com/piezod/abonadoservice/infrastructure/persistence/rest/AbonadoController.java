package com.piezod.abonadoservice.infrastructure.persistence.rest;

import com.piezod.abonadoservice.application.AbonadoService;
import com.piezod.abonadoservice.domain.Abonado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/abonados")
public class AbonadoController {

    private final AbonadoService service;

    @Autowired

    public AbonadoController(AbonadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Abonado> createAbonado(@RequestBody Abonado abonado) {
        System.out.println("🔍 ABONADO RECIBIDO: " + abonado);
        Abonado saved = service.createAbonado(abonado);
        return ResponseEntity.ok(saved); // 👈 Esto está bien
    }

    @CrossOrigin(origins = "http://localhost:4200") // ¡Importante para CORS!
    @GetMapping
    public ResponseEntity<List<Abonado>> getAllAbonados() {
        List<Abonado> abonados = service.getAllAbonados();
        return ResponseEntity.ok(abonados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Abonado> getAbonadoById(@PathVariable String id) {
        return service.getAbonadoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Abonado> updateAbonado(@PathVariable String id, @RequestBody Abonado abonadoActualizado) {
        Optional<Abonado> abonadoOpt = service.getAbonadoById(id);

        if (abonadoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Abonado existente = abonadoOpt.get();

        // Actualiza campos necesarios
        existente.setNombre(abonadoActualizado.getNombre());
        existente.setDni(abonadoActualizado.getDni());
        existente.setTipo(abonadoActualizado.getTipo());
        existente.setFechaNacimiento(abonadoActualizado.getFechaNacimiento());
        existente.setDireccion(abonadoActualizado.getDireccion());
        existente.setFotoUrl(abonadoActualizado.getFotoUrl());

        Abonado actualizado = service.createAbonado(existente); // reutilizamos save

        return ResponseEntity.ok(actualizado);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Abonado>> getAbonadosByUser(@PathVariable String userId) {
        return ResponseEntity.ok(service.getAbonadosByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonado(@PathVariable String id) {
        service.deleteAbonado(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{abonadoId}/asignar-abono/{abonoId}")
    public ResponseEntity<String> asignarAbono(
            @PathVariable String abonadoId,
            @PathVariable String abonoId) {

        Optional<Abonado> abonadoOpt = service.getAbonadoById(abonadoId);
        if (abonadoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Abonado abonado = abonadoOpt.get();
        abonado.setAbonoId(abonoId);
        service.createAbonado(abonado); // Reutiliza el método save existente

        return ResponseEntity.ok("Abono asignado correctamente al abonado.");
    }
}