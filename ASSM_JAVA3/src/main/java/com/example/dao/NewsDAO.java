package com.example.dao;

import com.example.entity.News;

import java.util.List;

public interface NewsDAO {
    public List<News> findAll();
    public News findById(String id);
    public List<News> findHotNews(int limit);
    public List<News> findLatestNews(int limit);
    public List<News> findByCategory(String categoryId);
}
