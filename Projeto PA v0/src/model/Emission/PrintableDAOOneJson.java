/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Emission;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Gestor.ResultadoPercurso;

/**
 *
 * @author AndreLaptop
 */
public class PrintableDAOOneJson implements PrintableDAO {

    private String basePath;
    private List<Printable> list;
    private final static String fileName = "printables.json";

    public PrintableDAOOneJson(String basePath,String type) {
        this.basePath = basePath;
        list = new ArrayList<>();
        loadAll(type);
    }

    private void loadAll(String type) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(basePath + fileName + type));
            Gson gson = new GsonBuilder().create();

            List<Printable> newList = gson.fromJson(br,
                    new TypeToken<List<Printable>>() {
                    }.getType());
            this.list = newList;
        } catch (IOException i) {
            return;
        }
    }

    private void saveAll(String type) {
        FileWriter writer = null;
        try {
            Gson gson = new GsonBuilder().create();
            writer = new FileWriter(basePath + fileName + type);
            gson.toJson(list, writer);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintableDAOOneJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Printable> selectAll() {
        return list;
    }

    @Override
    public Printable select(int id, String type) {
        for (Printable p : list) {
            if (id == p.getID() && type.equals(p.getType())) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean insert(Printable entry, String type) {
        if (list.contains(entry)) {
            return false;
        }
        list.add(entry);
        saveAll(type);
        return true;
    }

    @Override
    public boolean remove(int id, String type) {
        Printable entry = select(id,type);
        if (!list.contains(entry)) {
            return false;
        }
        list.remove(entry);
        saveAll(type);
        return true;
    }

    @Override
    public boolean updatePath(int id, String type, ResultadoPercurso path) {
        Printable entry = select(id,type);
        if (!list.contains(entry)) {
            return false;
        }
        entry.setPath(path);
        saveAll(type);
        return true;
    }
}
