package com.example.newsrssfeed.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "channel")

public class Channel {
    private Image image;

    private String copyright;

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Item> item;

    private String docs;

    private String link;

    private String description;

    private String language;

    private String title;

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image;
    }

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public List<Item> getItem ()
    {
        return item;
    }

    public void setItem (List<Item> item)
    {
        if (this.item == null){
            this.item = new ArrayList<Item>(item.size());
        }
        this.item.addAll(item);
    }

    public String getDocs ()
    {
        return docs;
    }

    public void setDocs (String docs)
    {
        this.docs = docs;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "image=" + image +
                ", copyright='" + copyright + '\'' +
                ", item=" + item +
                ", docs='" + docs + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
