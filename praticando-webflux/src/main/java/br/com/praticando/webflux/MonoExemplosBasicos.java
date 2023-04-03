package br.com.praticando.webflux;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import reactor.core.publisher.Mono;

public class MonoExemplosBasicos {
  
  public static void main(String[] args) {
    
    //Future
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Oi, carro do futuro!");
    Mono<String> monoFuture = Mono.fromFuture(future);
    monoFuture.subscribe(System.out::println);
    
    //Just
    Mono<String> monoHello = Mono.just("Bem vindo ao fincanciamento!");
    monoHello.subscribe(System.out::println);

    //flatMap
    Mono<String> mono = Mono.just("Chevrolet");
    mono.flatMap(value -> Mono.just(value.split("")))
        .subscribe(value -> System.out.println("Splitado: [" + Arrays.toString(value) + "]"));
    
    //flatMap - Sync
    var serviceSMS = new ServiceSMS();
    Mono<String> monoSync = Mono.just("Texto do SMS!");
    monoSync.flatMap(serviceSMS::enviarSMS)
      /// -----------------> x
        .subscribe(value -> System.out.println("SMS enviado: [" + value + "]"));
    
    //flatMap - Async
    Mono<String> monoAsync = Mono.just("Texto do SMS!");
    monoAsync.flatMap(sms -> Mono.fromCallable(() -> serviceSMS.enviarSMS(sms)))
      /// -----------------> callable [FUTURE]
      /// -----------------> flatmap --->>> segue a rotina
        .subscribe(value -> System.out.println("SMS será enviado! ["+ value +"]"));
    
    //map
    Mono<String> monoString = Mono.just("Chevrolet");
    monoString.map(valorString -> valorString.length())
        .subscribe(valorInteiro -> System.out.println("Tamanho da palavra: [" + valorInteiro + "]"));
    
    //filter
    Mono<String> monoDefaultEmpty = Mono.just("Fiat");
    monoDefaultEmpty.filter(marca -> "Fiat".equals(marca))
        .subscribe(value -> System.out.println("Filtrado: [" + value + "]"));
    
    //switchIfEmpty
    Mono<String> monoSwitchIfEmpty = Mono.just("Volkswagen");
    monoSwitchIfEmpty.filter(marca -> "Fiat".equals(marca))
        .switchIfEmpty(Mono.just("Objeto não era Fiat!")) //MONO<?>
        .subscribe(value -> System.out.println("SwitchIfEmpty: [" + value + "]"));
    
    //defaultIfEmpty
    Mono<String> monoFilter = Mono.just("Volkswagen");
    monoFilter.filter(marca -> "Fiat".equals(marca))
        .defaultIfEmpty("Não existe FIAT")//objeto do MONO
        .subscribe(value -> System.out.println("DefaultIfEmpty: [" + value + "]"));
    
    //fromSupplier
    String strSupplier = "Carro criado por Supplier!";
    Mono<String> monoSupplier = Mono.fromSupplier(() -> strSupplier);
    monoSupplier.subscribe(System.out::println);

    //defer
    AtomicReference<String> strAtomic = new AtomicReference<>("Mono defer só é finalizado com o subscribe!");
    Mono<String> monoDefer = Mono.defer(() -> Mono.just(strAtomic.get()));
    monoDefer.subscribe(System.out::println);

    //zip
    Mono<String> monoMarca = Mono.just("Fiat");
    Mono<String> monoModelo = Mono.just("Marea");
    Mono<String> monoTotal = Mono.zip(monoMarca, monoModelo, (marca, modelo) -> marca + " - " + modelo);
    monoTotal.subscribe(System.out::println);

    //then
    Mono<String> monoChassi = Mono.just("1289397842");
    Mono<String> monoCompleto = monoChassi.map(f -> f + " abcdefg")
                                      .then(Mono.just("Número do Chassi: "))
                                      //.thenReturn("Número do Chassi: ")
                                      .map(chassi -> chassi + monoChassi.block());
    monoCompleto.subscribe(System.out::println);

  }
}

class ServiceSMS{
  Mono<String> enviarSMS(String texto){
    return Mono.just(texto);
  }
}
