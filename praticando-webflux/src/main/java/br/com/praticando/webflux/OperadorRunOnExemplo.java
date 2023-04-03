package br.com.praticando.webflux;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class OperadorRunOnExemplo {
  public static void main(String[] args) {
    
    //RunOn
    Flux.fromIterable(List.of("A", "B", "C"))
        .parallel()
        .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
        .runOn(Schedulers.newParallel("primeira thread"))
        .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
        .map(letra -> letra.concat("***"))
        .runOn(Schedulers.newParallel("segunda thread"))
        .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
        .map(letra -> letra.concat("###"))
        .runOn(Schedulers.newParallel("terceira thread"))
        .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
        .sequential()
        .subscribe();
  }
}
