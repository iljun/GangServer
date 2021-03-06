package com.gang.domain.Article;

import com.gang.domain.Comment.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iljun on 2017-03-17.
 */
@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public ArticleService(Facebook faceBook, ConnectionRepository connectionRepository){
        this.facebook = faceBook;
        this.connectionRepository = connectionRepository;
    }

    public boolean checkFacebook(){
        if(connectionRepository.findPrimaryConnection(Facebook.class)==null)
            return false;
        return true;
    }
    @Transactional(readOnly = false)
    public Article saveArticle(ArticleDto articleDto){
        return articleRepository.save(Article.of(articleDto,facebook.userOperations().getUserProfile().getLastName()));
    }

    public List<Article> findAllArticleList(){
        return articleRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        articleRepository.delete(id);
    }

    public ArticleListDto findArticleList(int currentPage){
        List<Article> list = articleRepository.articleList(currentPage);
        List<ListDto> list2 = new ArrayList<ListDto>();
        for(int i=0; i<list.size(); i++){
            list2.add(ListDto.of(list.get(i),commentService.countByArticleId(list.get(i).getId())));
        }
        return ArticleListDto.of(list2,currentPage, articleRepository.totalCount());
    }

    public Article findById(Long id){
        return articleRepository.findOne(id);
    }

    public ArticleDetailDto findArticle(Long articleId){
        return ArticleDetailDto.of(articleRepository.findOne(articleId),commentService.findByArticleId(articleId));
    }
}
