package com.example.newsrssfeed.Model;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class RssObject {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Rss> rss;

    public List<Rss> getRss ()
    {
        return rss;
    }

    public void setRss (List<Rss> rss)
    {
        this.rss = rss;
    }

    @Override
    public String toString() {
        return "RssObject{" +
                "rss=" + rss +
                '}';
    }
}
