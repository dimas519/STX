package com.dimas519.api1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class TodosBook {
    private WebClient.Builder webClient;

    private final  String api1="https://jsonplaceholder.typicode.com/todos";
    private String api2;

    @Value("${stx.api2.url}")
    private String api2Url;

    @Value("${stx.api2.port}")
    private int api2Port;

    @Value("${stx.api2.ssl}")
    private boolean api2Ssl;


    public TodosBook(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder;
    }


    private String getTodos() {
        WebClient client = this.webClient
                .baseUrl(api1)
                .build();

        return client.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    private String getPosts(){
        if(api2==null) {
            this.api2 = (api2Ssl ? "https://" : "http://") + api2Url + ":" + api2Port + "/posts";
        }
        WebClient client = this.webClient
                .baseUrl(this.api2)
                .build();

        return client.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public getTasks


}
