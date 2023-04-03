package br.com.praticando.webflux;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;

public class FluxIntStreamExamples {
  public static void main(String[] args) {
    
    // IntStream
    Flux<Integer> flux =
        Flux.fromIterable(IntStream.rangeClosed(1, 10)
                              .boxed()
                              .collect(Collectors.toList()));
    flux.subscribe(System.out::println);
    
    // DoubleStream
    Flux<Double> fluxDouble =
        Flux.fromIterable(DoubleStream.of(1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d) // LongStream
                              .boxed()
                              .collect(Collectors.toList()));
    fluxDouble.subscribe(System.out::println);
    


    

    
    
  }
}
