package com.example.dao;
import com.example.entity.Newsletter;
import java.util.List;
public interface NewsletterDAO {
    void insert(Newsletter newsletter);
    void delete(String email);
    List<Newsletter> findAll();
}
