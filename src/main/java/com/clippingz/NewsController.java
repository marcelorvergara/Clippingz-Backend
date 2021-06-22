package com.clippingz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.clippingz.api.exception.NewsNotFoundException;
import com.clippingz.api.model.Dia;
import com.clippingz.api.model.NewsEntity;
import com.clippingz.api.model.NewsModel;
import com.clippingz.api.model.NewsSearchModel;
import com.clippingz.repository.NewsRepository;
import com.clippingz.api.service.NewsService;
import com.clippingz.auth.model.User;
import com.clippingz.auth.service.SecurityService;
import com.clippingz.auth.service.UserService;
import com.clippingz.auth.web.UserValidator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcelo
 */
@EnableFeignClients
@RestController
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    ///////////////////////////////////////////////////////////////
    ///// autenticação  ///////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {

        System.out.println("isAuthenticated: " + securityService.isAuthenticated());

        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null) {
            return "erro na autenticacao";
        }

        if (logout != null) {
            return "logout_com_sucesso";
        }

        return "login";

    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    //criação de login
    @PostMapping("/registro")
    public String regsitration(@RequestBody User userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("Há erros no registro");
            return "registro";
        }

        userService.save(userForm);

        //já loga logo depois de criar o usuário
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "logado";
        //return "redirect:/welcome";
    }

    ///////////////////////////////////////////////////////////////
    ///// repositório db  /////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    private final NewsRepository repository;

    NewsController(NewsRepository repository) {
        this.repository = repository;
    }

    // métodos para banco de dados
    // Agregate root
    // tag::get-aggregate-root[]
    @GetMapping("/news")
    List<NewsEntity> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/news")
    NewsEntity novaNews(@RequestBody NewsEntity novaNews) {
        return repository.save(novaNews);
    }

    //listagem das notícias da página principal
    @PostMapping("/newsDay")
    List<NewsEntity> listNewsDay(@RequestBody Dia dia) {
        log.info("Dia: " + dia.getDia());
        return repository.findByDataPublicacao(dia.getDia());
    }

    //pesquis de notícia por patterns no título
    @GetMapping("/newsPat/{pat}")
    List<NewsEntity> getNewsPattern(@PathVariable String pat) throws NewsNotFoundException {
        log.info("pattern: " + pat);
        return repository.findByTituloContaining(pat);
    }

    @GetMapping("/news/{id}")
    NewsEntity one(@PathVariable Long id) throws NewsNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException(id));
    }

    @PutMapping("/news/{id}")
    NewsEntity replaceNews(@RequestBody NewsEntity newNews, @PathVariable Long id) {
        return repository.findById(id).map(news -> {
            news.setDataPublicacao(newNews.getDataPublicacao());
            news.setTitulo(newNews.getTitulo());
            news.setDescricao(newNews.getDescricao());
            news.setConteudo(newNews.getConteudo());
            news.setUrlToImage(newNews.getUrlToImage());
            news.setUrl(newNews.getUrl());
            news.setAutor(newNews.getAutor());
            return repository.save(news);
        })
                .orElseGet(() -> {
                    newNews.setId(id);
                    return repository.save(newNews);
                });
    }

    @DeleteMapping("/news/{id}")
    void deleteNews(@PathVariable Long id) {
        repository.deleteById(id);
    }

    ///////////////////////////////////////////////////////////////
    ///// consulta api news  //////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    //serviço de consulta a api news.org
    @Autowired
    public NewsService newsService;

    @PostMapping("/newsSearch")
    public NewsModel getNews(@RequestBody NewsSearchModel newsSearch) {

        System.out.println(newsSearch);
        NewsModel newsResultList;
        newsResultList = newsService.newsSearch(
                newsSearch.getOnde(),
                newsSearch.getPalavraChave(),
                newsSearch.getLanguage(),
                newsSearch.getFrom(),
                newsSearch.getTo(),
                newsSearch.getPageSize());
        return newsResultList;
    }

}
