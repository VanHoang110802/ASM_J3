package com.example.dao;

import com.example.entity.Category;
import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Category findById(String id);
}
