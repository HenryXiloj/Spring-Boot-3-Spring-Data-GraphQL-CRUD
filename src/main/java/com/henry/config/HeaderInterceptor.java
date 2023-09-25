package com.henry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class HeaderInterceptor implements WebGraphQlInterceptor {
    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
      final HttpHeaders headers = request.getHeaders();
        Map<String, List<String>> data = new HashMap<>(headers);
        request.configureExecutionInput(((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("headers", data)).build()
        ));
        return chain.next(request);
    }

    @Override
    public WebGraphQlInterceptor andThen(WebGraphQlInterceptor nextInterceptor) {
        return WebGraphQlInterceptor.super.andThen(nextInterceptor);
    }

    @Override
    public Chain apply(Chain chain) {
        return WebGraphQlInterceptor.super.apply(chain);
    }
}
