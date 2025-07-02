package com.piezod.abonadoservice.application;

import com.piezod.abonadoservice.domain.Abonado;
import com.piezod.abonadoservice.domain.AbonadoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonadoService {

    private final AbonadoRepositoryPort repository;

    @Autowired
    public AbonadoService(AbonadoRepositoryPort repository) {
        this.repository = repository;
    }

    public Abonado createAbonado(Abonado abonado) {
        return repository.save(abonado);
    }

    public Optional<Abonado> getAbonadoById(String id) {
        return repository.findById(id);
    }

    public List<Abonado> getAbonadosByUser(String userId) {
        return repository.findAllByUserId(userId);
    }

    public void deleteAbonado(String id) {
        repository.deleteById(id);
    }

    public List<Abonado> getAllAbonados() {
        return repository.findAll();
    }
}
