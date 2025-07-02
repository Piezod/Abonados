package com.piezod.abonadoservice.infrastructure.persistence;

import com.piezod.abonadoservice.domain.Abonado;
import com.piezod.abonadoservice.domain.AbonadoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoAbonadoRepository implements AbonadoRepositoryPort {
    private final SpringDataAbonadoRepository repository;

    @Autowired
    public MongoAbonadoRepository(SpringDataAbonadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Abonado save(Abonado abonado) {
        repository.save(abonado);
        return abonado;
    }

    @Override
    public Optional<Abonado> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Abonado> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Abonado> findAll() {
        return repository.findAll();
    }
}
