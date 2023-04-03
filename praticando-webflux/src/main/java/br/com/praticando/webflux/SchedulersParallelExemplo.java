package br.com.praticando.webflux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersParallelExemplo {
  public static void main(String[] args) {
    // Schedulers - parallel - executa num pool de thread as tarefas em paralelo
    Flux<String> tarefa =
        Flux.just("tarefa1", "tarefa2", "tarefa3", "tarefa4", "tarefa5", "tarefa6", "tarefa7",
            "tarefa8", "tarefa9", "tarefa10", "tarefa11", "tarefa12", "tarefa13", "tarefa10",
            "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10",
            "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10",
            "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10",
            "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10",
            "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10", "tarefa10");
    tarefa.parallel() // transforma em fluxo paralelo
        .runOn(Schedulers.parallel()) // programa a execução do fluxo paralelo
        .doOnNext(d -> System.out.println("Valor impresso: " + d + ". Pela Thread: " + Thread.currentThread().getName()))
        .sequential() // retoma ao fluxo sequencial, com execução paralela
        .blockLast();
  }

}
