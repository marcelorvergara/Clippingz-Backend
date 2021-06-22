/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clippingz.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author marcelo
 */
public class NewsModel {

    @JsonIgnoreProperties(ignoreUnknown = true)

    @JsonProperty("status")
    private String status;
    @JsonProperty("totalResults")
    private int totalResults;
    @JsonProperty("articles")
    private List<Article> articles;

    public static class Article {

        private String author;
        private String title;
        private String url;
        private String content;
        private String urlToImage;
        private String description;

        public Article() {

        }

        /**
         * @return the author
         */
        public String getAuthor() {
            return author;
        }

        /**
         * @param author the author to set
         */
        public void setAuthor(String author) {
            this.author = author;
        }

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url the url to set
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * @return the content
         */
        public String getContent() {
            return content;
        }

        /**
         * @param content the content to set
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * @return the urlToImage
         */
        public String getUrlToImage() {
            return urlToImage;
        }

        /**
         * @param urlToImage the urlToImage to set
         */
        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }
    }

    public NewsModel() {
    }

    /**
     * @return the status
     */
    @JsonProperty("")
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    @JsonProperty("")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the totalResults
     */
    @JsonProperty("")
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults the totalResults to set
     */
    @JsonProperty("")
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return the articles
     */
    @JsonProperty("")
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    @JsonProperty("")
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("News Search [status=").append(status).append(", totalResults=").append(totalResults).append(", tot. articles=").append(articles.size()).append("\n ] ");
        articles.stream().map(art -> {
            if (art.getAuthor() == null) {
                sb.append("Autor: ").append("Autor Desconhecido").append("\n");
            } else {
                sb.append("Autor: ").append(art.getAuthor()).append("\n");
            }
            return art;
        }).map(art -> {
            sb.append("Matéria: ").append(art.getTitle()).append("\n");
            return art;
        }).map(art -> {
            sb.append("Url: ").append(art.getUrl()).append("\n");
            return art;
        }).map(art -> {
            sb.append("Img Url: ").append(art.getUrlToImage()).append("\n");
            return art;
        }).forEachOrdered(art -> {
            sb.append("Conteúdo: ").append("\n");
            sb.append(art.getContent()).append("\n----------------------------------\n\n");
        });
        return sb.toString();
    }
}
