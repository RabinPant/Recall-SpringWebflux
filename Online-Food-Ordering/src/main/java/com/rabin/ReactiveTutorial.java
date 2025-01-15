package com.rabin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

class Employee{
    private String fName;
    private String lName;

    public Employee(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}

public class ReactiveTutorial {
//    Creating a publisher with one data using Mono
    public static Mono<String> testMono(){
        return Mono.just("java").log();
    }

    public static Flux<String> testFlux(){

        List<Employee> list = List.of(new Employee("rabin","pant"),
                new Employee("rabin","mainali"),
                new Employee("rabin","devkota"),
                new Employee("rabin","neupane")
                );

        List<String> empList =list.stream().map(Employee::getlName).collect(Collectors.toList());
        return Flux.fromIterable(empList);
    }

    public static Flux<Integer> testConcat(){

        Flux<Integer> f1 = Flux.range(1,5)
                .delayElements(Duration.ofSeconds(1));
        Flux<Integer> f2 = Flux.range(10,5)
                .delayElements(Duration.ofSeconds(1));
        return Flux.merge(f1,f2);
    }

    public static Flux<Tuple2<Integer,Integer>> testZip(){

        Flux<Integer> f1 = Flux.range(1,5).delayElements(Duration.ofSeconds(1));
        Flux<Integer> f2 = Flux.range(5,5).delayElements(Duration.ofSeconds(1));

        return Flux.zip(f1,f2);
    }

    public static Flux<Integer> testErrorHandling(){

        Flux<Integer> flux = Flux.range(1,10)
                .map(num-> {
                    if(num==5)
                        throw new RuntimeException("I am number 5");
                    return num;
                });
        return flux.onErrorContinue((throwable, o) -> System.out.println("Dont worry about"+ o));
    }

    public static Mono<List<Integer>> testCollect(){
        Flux<Integer> f1 = Flux.range(1,10);

        return f1.collectList();
    }
    public static void main(String[] args) {

        testErrorHandling().subscribe(System.out::println);

//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
