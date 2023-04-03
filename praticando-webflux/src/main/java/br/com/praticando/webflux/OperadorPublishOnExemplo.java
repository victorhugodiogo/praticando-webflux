package br.com.praticando.webflux;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class OperadorPublishOnExemplo {
  public static void main(String[] args) {

    //PublishOn
    Flux.fromIterable(List.of("A","B"))
      .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
      .publishOn(Schedulers.newSingle("thread #01"))
      .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
      .map(letra -> letra.concat("***"))
      .publishOn(Schedulers.newSingle("thread #02"))
      .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
      .subscribe();
  }
}
