package com.piezod.abonadoservice.domain;

import java.util.List;
import java.util.Optional;

public interface AbonadoRepositoryPort {
    Abonado save(Abonado abonado);

    Optional<Abonado> findById(String id);

    List<Abonado> findAllByUserId(String userId);

    void deleteById(String id);

    List<Abonado> findAll();
}
