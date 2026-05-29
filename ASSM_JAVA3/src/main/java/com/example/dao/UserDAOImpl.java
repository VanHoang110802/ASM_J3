package com.example.dao;

import com.example.entity.User;
import com.example.jdbc.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UsersDAO {
    @Override
    public User findById(String id) {
        User u = null;
        String sql = "SELECT * FROM Users WHERE Id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = mapUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public User login(String id, String password) {
        User u = null;
        String sql = "SELECT * FROM Users WHERE Id=? AND Password=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = mapUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO Users(Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
            ps.setBoolean(5, user.isGender());
            ps.setString(6, user.getMobile());
            ps.setString(7, user.getEmail());
            ps.setBoolean(8, user.isRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Users SET Password=?, Fullname=?, Birthday=?, Gender=?, Mobile=?, Email=?, Role=? WHERE Id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getFullname());
            ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            ps.setBoolean(4, user.isGender());
            ps.setString(5, user.getMobile());
            ps.setString(6, user.getEmail());
            ps.setBoolean(7, user.isRole());
            ps.setString(8, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Users WHERE Id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getString("Id"));
        u.setPassword(rs.getString("Password"));
        u.setFullname(rs.getString("Fullname"));
        u.setBirthday(rs.getDate("Birthday"));
        u.setGender(rs.getBoolean("Gender"));
        u.setMobile(rs.getString("Mobile"));
        u.setEmail(rs.getString("Email"));
        u.setRole(rs.getBoolean("Role"));
        return u;
    }
}
