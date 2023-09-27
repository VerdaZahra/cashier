package com.smk.cashierapp.service;

import com.smk.cashierapp.model.Barang;
import com.smk.cashierapp.service.BarangService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BarangServiceTest {

    @Test
    @Order(2)
    void getBarangList() {
        List<Barang> barangList = BarangService.getInstance().getBarangList();
        assertEquals(barangList.size(),3);
    }

    @Test
    @Order(3)
    void findByName() {
        List<Barang> resultList = BarangService.getInstance().findByName("Laptop Gaming");
        assertEquals(resultList.size(),2);
    }

    @Test
    @Order(1)
    void addBarang() {
        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(5000000);
        BarangService.getInstance().addBarang(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("M0001");
        mouse.setNamaBarang("Mouse");
        mouse.setHargaBarang(100000);
        BarangService.getInstance().addBarang(mouse);

        Barang laptopGaming = new Barang();
        laptop.setKodeBarang("LP002");
        laptop.setNamaBarang("Laptop Gaming");
        laptop.setHargaBarang(20000000);
        BarangService.getInstance().addBarang(laptopGaming);

    }
}