package br.com.praticando.webflux.conc.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.praticando.webflux.conc.domain.CarDocument;
import br.com.praticando.webflux.conc.domain.ICarRepository;
import br.com.praticando.webflux.conc.util.SpringContext;
import reactor.core.publisher.Mono;

@Repository
@Profile({"local", "dev", "hml", "ppr", "prd"})
public class DynamoCarRepository implements ICarRepository {

//  DynamoDbAsyncTable<CarDocument> 
  
  @Override
  public Mono<CarDocument> findLastCarDocumentByModelo(String modelo) {
    final var query = new Query(Criteria.where("car.modelo").is(modelo));
    query.with(Sort.by(Sort.Direction.DESC, "dataCriacao"));
    query.limit(1);
    return SpringContext.getBean(ReactiveMongoTemplate.class).findOne(query, CarDocument.class);
  }

}
