package br.com.praticando.webflux;

import reactor.core.publisher.Flux;

public class FluxAgruparExemplo {
  public static void main(String[] args) {

    var modelos = Flux.just("gol", "palio", "siena", "uno", "308", "cronos", "prisma", "siena", "gol");
    Flux.fromIterable(modelos.toIterable())
      .groupBy(modelo -> modelo.length())
      .flatMap(Flux::collectList)
      .subscribe(set -> System.out.println(set.toString()));


  }
}
