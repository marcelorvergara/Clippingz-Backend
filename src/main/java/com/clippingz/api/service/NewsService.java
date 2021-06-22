/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clippingz.api.service;

import com.clippingz.api.model.NewsModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author marcelo
 */
@Service
@FeignClient(url = "https://newsapi.org/v2/", name = "NewsService")
public interface NewsService {

    @RequestMapping("everything?{onde}={kw}&language={lang}&from={ini}&to={fim}&sortBy=publishedAt&pageSize={pgSize}&apiKey=6dfcde728d4041529026d9ccd3dff352")
    NewsModel newsSearch(
            @RequestParam("onde") String onde,
            @RequestParam("kw") String kw,
            @RequestParam("lang") String lang,
            @RequestParam("ini") String ini,
            @RequestParam("fim") String fim,
            @RequestParam("pgSize") String pgSize);

}
