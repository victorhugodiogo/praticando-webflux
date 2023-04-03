package br.com.praticando.webflux;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class OperadorSubscribeOnExemplo {
  public static void main(String[] args) {

    //SubscribeOn
    Flux.fromIterable(List.of("A","B"))
      .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
      .subscribeOn(Schedulers.newSingle("primeira thread #01"))
      .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
      .map(letra -> letra.concat("***"))
      .subscribeOn(Schedulers.newSingle("segunda thread #02"))
      .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
      .map(letra -> letra.concat("###"))
      .subscribeOn(Schedulers.newSingle("terceira thread #03"))
      .doOnNext(letra -> System.out.println(letra + " na thread [" + Thread.currentThread().getName() + "]"))
      .subscribe();
  }
}
