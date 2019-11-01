package com.example.newsrssfeed.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enclosure {

    private String link;

    @JsonProperty("link")
    public String getLink() { return link; }
    @JsonProperty("link")
    public void setLink(String value) { this.link = value; }
}
