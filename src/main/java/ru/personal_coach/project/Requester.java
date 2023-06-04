package ru.personal_coach.project;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class Requester {
    private final RestTemplate restTemplate;

    private URI uri = null;
    public Requester(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken (String token) throws ResourceAccessException, HttpClientErrorException, URISyntaxException {
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.add( "Content-type", "application/json; charset=UTF-8");

            uri = new URI("http://localhost:8081/api/v1/auth/validation");
            RequestEntity req = new RequestEntity(headers, HttpMethod.GET, uri);

            ResponseEntity response = restTemplate.exchange(req,String.class);
            if (response.getStatusCode() != HttpStatus.OK){
                return false;
            }
            return true;
    }
}
