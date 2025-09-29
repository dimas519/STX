package com.dimas519.api1;




import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.reactive.function.client.WebClient;



@RestController
public class API1Controller {

    private final TodosPost todosPost;

    private final RedisConnector redisConnector;



    public API1Controller(WebClient.Builder webClientBuilder,RedisConnector redisConnector, TodosPost todosPost) {
        this.todosPost=todosPost;
        this.redisConnector=redisConnector;


    }





    @GetMapping(value = "/bookCombine",produces = "text/json")
    public String getBookCombine() throws JsonProcessingException {



        System.out.println("getTodos");
        String todos=todosPost.getTodos();
//        System.out.println(todos);
//

        System.out.println("getposts");
        String posts=todosPost.getPosts();
//        System.out.println(posts);

        System.out.println("getUser");
        String getUser=redisConnector.getData("user:1");
//        System.out.println(getUser);


        System.out.println("combining");
        String result=CombineUser.getResult(getUser,todos,posts);


        System.out.println(result);








        return result;
    }


}
