/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clippingz.api.model;

/**
 *
 * @author marcelo
 */
public class NewsSearchModel {

    //http://newsapi.org/v2/everything?`+`${od}`+`="`+`${kw}`+`"&language=`+`${id}`+`&from=`+`${data1}`+`&to=`+`${data0}`+`&pageSize=`+ `${mpNum}` +`&apiKey=6
    private String onde;
    private String palavraChave;
    private String language;
    private String from;
    private String to;
    private String pageSize;

    public NewsSearchModel(String onde, String palavraChave, String language, String from, String to, String pageSize) {
        this.onde = onde;
        this.palavraChave = palavraChave;
        this.language = language;
        this.from = from;
        this.to = to;
        this.pageSize = pageSize;
    }

    public NewsSearchModel() {

    }

    /**
     * @return the onde
     */
    public String getOnde() {
        return onde;
    }

    /**
     * @param onde the onde to set
     */
    public void setOnde(String onde) {
        this.onde = onde;
    }

    /**
     * @return the palavraChave
     */
    public String getPalavraChave() {
        return palavraChave;
    }

    /**
     * @param palavraChave the palavraChave to set
     */
    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the pageSize
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        //everything?`+`${od}`+`="`+`${kw}`+`"&language=`+`${id}`+`&from=`+`${data1}`+`&to=`+`${data0}`+`&pageSize=`+ `${mpNum}` +`&apiKey=6dfcde728d4041529026d9ccd3dff352
        StringBuilder sb = new StringBuilder();
        sb.append("everything?").append(getOnde()).append("=").append(getPalavraChave())
                .append("&language=").append(getLanguage())
                .append("&from=").append(getFrom()).append("&to=")
                .append(getTo()).append("&pageSize=").append(getPageSize())
                .append("&apiKey=6dfcde728d4041529026d9ccd3dff352");
        return sb.toString();
    }

}
