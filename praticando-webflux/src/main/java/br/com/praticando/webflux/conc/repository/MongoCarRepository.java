package br.com.praticando.webflux.conc.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.praticando.webflux.conc.domain.CarDocument;
import br.com.praticando.webflux.conc.domain.ICarRepository;

public interface MongoCarRepository
    extends ICarRepository, ReactiveMongoRepository<CarDocument, String> {

}
