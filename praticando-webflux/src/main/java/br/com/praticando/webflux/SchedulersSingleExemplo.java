package br.com.praticando.webflux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersSingleExemplo {
  public static void main(String[] args) {
    //Schedulers - single - executa numa única thread e em ordem
    Flux<String> data = Flux.just("data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10");
    data.subscribeOn(Schedulers.single())
        .doOnNext(d -> System.out.println("Valor impresso: " + d + ". Pela Thread: " + Thread.currentThread().getName()))
        .blockLast(); //bloqueia o processo até o ultimo dado ser executado
  }
}