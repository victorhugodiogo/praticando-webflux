package br.com.praticando.webflux;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;
import reactor.core.publisher.Flux;

public class FluxContadorExemplo {
  public static void main(String[] args) {
   
    // Contando itens
    Flux<Usuario> integerFlux = Flux.just(new Usuario(1),new Usuario(22),new Usuario(333));
    
    //Mantendo os objetos
    AtomicInteger quantidade = new AtomicInteger(0);
    integerFlux
    	.doOnNext(u -> quantidade.incrementAndGet())
    	.doOnComplete(() -> System.out.println("Quantidade por ATOMIC: " + quantidade.get()))
    	.subscribe();
    
    //Obtendo apenas a quantidade
    integerFlux
    	.count()
    	.subscribe(e -> System.out.println("Quantidade por COUNT: " + e));
    
    
  }
}

@Data
class Usuario {
	private int id;
	public Usuario( int id ) {
		this.id = id;
	}
}