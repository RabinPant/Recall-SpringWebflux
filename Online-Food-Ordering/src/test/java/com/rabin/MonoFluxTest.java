package com.rabin;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;

public class MonoFluxTest {

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
