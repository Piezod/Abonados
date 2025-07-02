package com.piezod.abonadoservice.infrastructure.persistence;

import com.piezod.abonadoservice.domain.Abonado;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataAbonadoRepository extends MongoRepository<Abonado, String> {
    List<Abonado> findAllByUserId(String userId);
}

