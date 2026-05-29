package com.example.dao;

import com.example.entity.Category;
import com.example.jdbc.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Categories";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getString("Id"));
                c.setName(rs.getString("Name"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category findById(String id) {
        Category c = null;
        String sql = "SELECT * FROM Categories WHERE Id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Category();
                c.setId(rs.getString("Id"));
                c.setName(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
