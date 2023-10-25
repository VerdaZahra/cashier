package com.smk.cashierapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class StokDao {
    private Connection connection;

    public StokDao() {
        try {
            // Ganti URL, username, dan password sesuai dengan basis data Anda
            String url = "jdbc:mysql://localhost:3306/nama_database";
            String username = "username_database";
            String password = "password_database";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Stok stok) {
        try {
            String query = "INSERT INTO Stok (id, nama_barang, jumlah) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, stok.getId());
            preparedStatement.setString(2, stok.getNamaBarang());
            preparedStatement.setInt(3, stok.getJumlah());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Stok get(int id) {
        Stok stok = null;
        try {
            String query = "SELECT * FROM Stok WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                stok = new Stok(resultSet.getInt("id"), resultSet.getString("nama_barang"), resultSet.getInt("jumlah"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stok;
    }
}

class Stok {
    private int id;
    private String namaBarang;
    private int jumlah;

    public Stok(int id, String namaBarang, int jumlah) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }
}

class StokService {
    private StokDao stokDao;

    public StokService() {
        stokDao = new StokDao();
    }

    public void saveStokDatabase(Stok stok) {
        stokDao.save(stok);
    }

    public Stok getStok(int id) {
        return stokDao.get(id);
    }
}