package com.mohammed.url_shortner.models;


import javax.persistence.*;

@Entity
@Table(name = "urls_info")
public class URLInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "shorten_url", nullable = false)
    private String shortenUrl;
    @Column(name = "original_url", nullable = false, length = 1000)
    private String originalUrl;
    @Column
    private long count;


    public URLInfo() {
    }

    public URLInfo(String shortenUrl, String originalUrl){
        this.shortenUrl = shortenUrl;
        this.originalUrl = originalUrl;
        this.count = 0L;
    }

    public long getId() {
        return id;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public long getCount() {
        return count;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
