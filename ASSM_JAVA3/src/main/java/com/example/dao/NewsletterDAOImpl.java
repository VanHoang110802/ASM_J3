package com.example.dao;
import com.example.entity.Newsletter;
import com.example.jdbc.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsletterDAOImpl implements NewsletterDAO {
    @Override
    public void insert(Newsletter newsletter) {
        String sql = "INSERT INTO Newsletters(Email, Enabled) VALUES(?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newsletter.getEmail());
            ps.setBoolean(2, newsletter.isEnabled());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String email) {
        String sql = "DELETE FROM Newsletters WHERE Email=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Newsletter> findAll() {
        List<Newsletter> list = new ArrayList<>();
        String sql = "SELECT * FROM Newsletters";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Newsletter n = new Newsletter();
                n.setEmail(rs.getString("Email"));
                n.setEnabled(rs.getBoolean("Enabled"));
                list.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
