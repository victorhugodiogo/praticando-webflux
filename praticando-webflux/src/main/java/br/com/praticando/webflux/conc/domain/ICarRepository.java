package br.com.praticando.webflux.conc.domain;

import reactor.core.publisher.Mono;

public interface ICarRepository {

  Mono<CarDocument> findLastCarDocumentByModelo(final String modelo);
//  Mono<Void> save(CarDocument document);
}
