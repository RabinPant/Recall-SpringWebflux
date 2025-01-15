package com.rabin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class MainController {
    @GetMapping("/")
    public Mono<String> mainController(){
        return Mono.just("home");
    }
}
