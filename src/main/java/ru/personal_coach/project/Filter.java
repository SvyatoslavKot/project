package ru.personal_coach.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
public class Filter implements javax.servlet.Filter {

    private final RestTemplate restTemplate;
    private final Requester requester;

    public Filter(RestTemplate restTemplate, Requester requester) {
        this.restTemplate = restTemplate;
        this.requester = requester;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("start");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        URI uri = null;

        Cookie[] cookies = httpRequest.getCookies();
        try {
            if (cookies != null && cookies.length > 0) {
                Optional<Cookie> a = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("Authorization")).findFirst();
                if (a.isPresent()) {
                    if (!a.get().getValue().isEmpty()) {
                        if (!requester.validateToken(a.get().getValue())) {
                            Cookie cookie = new Cookie("Authorization", "");
                            cookie.setMaxAge(0);
                            httpResponse.addCookie(cookie);
                        }
                    }
                }
            }
            System.out.println("token success");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            log.error("Auth Server is not available!", e.getCause());
            Cookie cookie = new Cookie("Authorization", "");
            httpResponse.addCookie(cookie);
            filterChain.doFilter(servletRequest, httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
