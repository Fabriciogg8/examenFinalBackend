package com.dh.catalogservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String genre;

    private String urlStream;

}
