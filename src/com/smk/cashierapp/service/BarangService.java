package com.smk.cashierapp.service;

import com.smk.cashierapp.model.Barang;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BarangService {
    FileReader barangServiceReader;
    FileWriter barangServiceWriter;

    List<Barang> barangList = new LinkedList<>();
    private static BarangService barangService = null;
    private BarangService() {
        try {
            barangServiceWriter = new FileWriter("barang.txt");
            barangServiceReader = new FileReader("barang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized BarangService getInstance(){
        if(barangService == null){
            barangService = new BarangService();
        }
        return barangService;
    }

    private void readFile(){
        BufferedReader bufferedReader = new BufferedReader(barangServiceReader);
        List<String> stringList = bufferedReader.lines().toList();
        barangList = new LinkedList<>();
        for (String string: stringList){
            barangList.add(parsingLineToBarang(string));
        }
    }

    private void writeFile(){
        try {
            barangServiceWriter = new FileWriter("barang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(barangServiceWriter);
        for (int i = 0; i < barangList.size(); i++) {
            Barang barang = barangList.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(barang.getKodeBarang());
            sb.append("|");
            sb.append(barang.getNamaBarang());
            sb.append("|");
            sb.append(barang.getHargaBarang());
            try {
                bufferedWriter.write(sb.toString());
                if(i < barangList.size() - 1){
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Barang parsingLineToBarang(String string){
        StringTokenizer st = new StringTokenizer(string, "|");
        int id = 0;
        Barang barang = new Barang();
        while (st.hasMoreElements()){
            if (id == 0){
                barang.setKodeBarang(st.nextToken());
            } else if (id == 1){
                barang.setNamaBarang(st.nextToken());
            } else if (id == 2) {
                barang.setHargaBarang(Integer.parseInt(st.nextToken()));
            }
            id ++;
        }
        return barang;
    }

    public List<Barang> getBarangList(){
        readFile();
        return barangList;
    }

    public void addBarang(Barang barang){
        barangList.add(barang);
        writeFile();
    }

    public List<Barang> findByName(String laptop_gaming) {
        readFile();
        return barangList;
    }
}