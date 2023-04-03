package br.com.praticando.webflux;

import reactor.core.publisher.Flux;

public class LogsWithSubscribeFluxExample {
  public static void main(String[] args) {

    Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5)
        .map(n -> {
           if( n >= 3 ) {
             throw new RuntimeException("EEEEEE");
           }
          return n;
        })
        .doOnEach(e -> System.out.println("doOnEach - Cada item"))
        .doOnError(e -> System.out.println("doOnError - Com erro"))
        .doOnComplete(() -> System.out.println("doOnComplete - completado com sucesso"))
        .doOnTerminate(() -> System.out.println("doOnTerminate - terminado com sucesso ou error"));

//    numbers.subscribe();
    numbers.subscribe(
        n -> System.out.println("Executou o NEXT"),
        e -> System.out.println("Executou o ERROR"),
        () -> System.out.println("Executou o COMPLETE"));
  }
}
