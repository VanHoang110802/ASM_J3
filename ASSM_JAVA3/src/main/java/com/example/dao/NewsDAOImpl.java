package com.example.dao;

import com.example.entity.News;
import com.example.jdbc.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    @Override
    public List<News> findAll() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getString("Id"));
                n.setTitle(rs.getString("Title"));
                n.setPostedDate(rs.getDate("PostedDate"));
                n.setViewCount(rs.getInt("ViewCount"));
                list.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public News findById(String id) {
        News n = null;
        String sql = "SELECT * FROM News WHERE Id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = new News();
                n.setId(rs.getString("Id"));
                n.setTitle(rs.getString("Title"));
                n.setContent(rs.getString("Content"));
                n.setImage(rs.getString("Image"));
                n.setPostedDate(rs.getDate("PostedDate"));
                n.setAuthor(rs.getString("Author"));
                n.setViewCount(rs.getInt("ViewCount"));
                n.setCategoryId(rs.getString("CategoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<News> findHotNews(int limit) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT TOP ? * FROM News ORDER BY ViewCount DESC";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getString("Id"));
                n.setTitle(rs.getString("Title"));
                n.setViewCount(rs.getInt("ViewCount"));
                list.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<News> findLatestNews(int limit) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT TOP ? * FROM News ORDER BY PostedDate DESC";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getString("Id"));
                n.setTitle(rs.getString("Title"));
                n.setPostedDate(rs.getDate("PostedDate"));
                list.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<News> findByCategory(String categoryId) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE CategoryId=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getString("Id"));
                n.setTitle(rs.getString("Title"));
                n.setCategoryId(rs.getString("CategoryId"));
                list.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
