package com.aardwark.coc.four.spring_vs_micronaut.rest;

import io.micronaut.http.client.annotation.Client;

@Client("/book")
public interface BookClient extends BookOperations {

}
