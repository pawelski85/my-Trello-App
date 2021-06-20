package com.app.trello.client;

import com.app.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;


    private URI getUrl() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/me/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name","id")
                .build()
                .encode()
                .toUri();
        return url;


    }

    public List<TrelloBoardDto> getTrelloBoards(){

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(getUrl(), TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse).map(arr-> { for(TrelloBoardDto t: arr) {
            if(t.getName().contains("MyAppBoard") && t.getId()!=null) {
                return t;
            } }
            return null;
        }).map(Arrays::asList).orElse(Collections.emptyList());
    }
}
