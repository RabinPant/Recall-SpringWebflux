package com.rabin;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
       Mono<?> monoString = Mono.just("javatechie")
               .then(Mono.error(new RuntimeException("Exception Occured")))
               .log();
       monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));

    }

    @Test
    public void testFlux(){

        Flux<String> just = Flux.just("spring", "spring boot", "micrsoervices")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occured in flux")))
                .concatWithValues("cloud")
                .log();

        just.subscribe(System.out::println);

    }

}
