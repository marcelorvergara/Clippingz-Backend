/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clippingz.repository;

import com.clippingz.api.model.NewsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marcelo
 */
@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    //método para retornar as notícias do dia (listagem da página principal)
    public List<NewsEntity> findByDataPublicacao(String dataPublicacao);

    //método para persquisar notícias por palavra no título
    public List<NewsEntity> findByTituloContaining(String pattern);

}
