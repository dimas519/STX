package com.dimas519.api3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class APIController3 {
    WebClient clientTodos;
    public APIController3(){
        this.clientTodos = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos")
                .build();
    }

    @GetMapping("/todos")
    public String getTodos() {
        return this.clientTodos.get()
                .retrieve()
                .bodyToMono(String.class)
                .block(); // sinkron (blocking)
    }



}
