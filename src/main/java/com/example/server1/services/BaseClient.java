package com.example.server1.services;

import com.example.server1.dto.LocationDto;
import com.example.server1.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BaseClient {

    private final WebClient client = WebClient.create("http://localhost:8081");

    public Flux<UserDto> findUsersByIdList(List<String> list) {
        return client.post()
                .uri("/user/getByIds")
                .body(Mono.just(list), List.class)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(UserDto.class);
    }


    public Flux<LocationDto> findLocationByZipList(List<Long> list) {
        return client.post()
                .uri("/locations/getByZips")
                .body(Mono.just(list), List.class)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(LocationDto.class);
    }

}
