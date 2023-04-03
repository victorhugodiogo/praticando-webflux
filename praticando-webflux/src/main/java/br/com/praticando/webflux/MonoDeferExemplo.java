package br.com.praticando.webflux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoDeferExemplo {

	public static void main( String[] args ) {

		readConfigFile("arquivosUtils/config.txt")
			.subscribe(content -> System.out.println("################## ARQUIVO LIDO COM SUCESSO: " + content),
							 	 error -> System.err.println("################## FALHA NA LEITURA: " + error.getMessage()));
	}

	public static Mono<String> readConfigFile( String filename ) {
		return Mono.defer(() -> lerArquivo(filename))
				/* É necessário a alteração do arquivo para que o FILTER seja acionado*/
				.filter(e -> "CONCLUIDO".equals(e))
				.repeatWhenEmpty(repetir -> repetir
						.zipWith(Flux.range(0, 2))
						.delayElements(Duration.of(2000, ChronoUnit.MILLIS), Schedulers.newSingle("teste"))
						.doOnEach(l -> System.out.println(">>>>>> Executou repeat! <<<<<<<")))
				.switchIfEmpty(getDefault());
				//.defaultIfEmpty("Agora foi pelo DefaultIfEmpty!");
	}

	private static Mono<String> getDefault() {
		return Mono.just("Arquivo não está CONCLUIDO!")
				.doOnEach(e -> {
					if (e.isOnNext())
						System.out.println("****************** NEXT -> Criou pelo Switch! ******************");
					if (e.isOnComplete())
						System.out.println("****************** SUCCESS -> Criou pelo Switch! ******************");
				});
				//.doOnNext(e -> System.out.println("NEXT -> Criou pelo Switch!"));
				//.doOnSuccess(e -> System.out.println("SUCCESS -> Criou pelo Switch!"));
				
	}

	private static Mono<String> lerArquivo( String filename ) {
		try {
			String content = Files.readString(Paths.get(filename));
			System.out.println("Leu o arquivo texto: " + content);
			return Mono.just(content);
		} catch (IOException e) {
			return Mono.error(e);
		}
	}
}
