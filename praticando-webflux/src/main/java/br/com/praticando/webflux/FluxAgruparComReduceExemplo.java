package br.com.praticando.webflux;

import java.util.HashSet;
import reactor.core.publisher.Flux;

public class FluxAgruparComReduceExemplo {
  public static void main(String[] args) {

    var modelos = Flux.just("gol", "palio", "siena", "uno", "308", "cronos", "prisma", "siena", "gol");
    Flux.fromIterable(modelos.toIterable())
      .groupBy(modelo -> modelo.length())
      .flatMap(grupo -> grupo.reduceWith(
          () -> new HashSet<>(), (set, modelo) -> {
          set.add(modelo);
          return set;
        }))
      .subscribe(set -> System.out.println(set.toString()));
  }
}
