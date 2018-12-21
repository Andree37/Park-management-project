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

    public PrintableDAOOneJson(String basePath) {
        this.basePath = basePath;
        list = new ArrayList<>();
        loadAll();
    }

    private void loadAll() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(basePath + fileName));
            Gson gson = new GsonBuilder().create();

            List<Printable> newList = gson.fromJson(br,
                    new TypeToken<List<Printable>>() {
                    }.getType());
            this.list = newList;
        } catch (IOException i) {
            return;
        }
    }

    private void saveAll() {
        FileWriter writer = null;
        try {
            Gson gson = new GsonBuilder().create();
            writer = new FileWriter(basePath + fileName);
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
    public Printable select(int id, char type) {
        for (Printable p : list) {
            if (id == p.getID() && type == p.getType()) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean insert(Printable entry) {
        if (list.contains(entry)) {
            return false;
        }
        list.add(entry);
        saveAll();
        return true;
    }

    @Override
    public boolean remove(int id, char type) {
        Printable entry = select(id,type);
        if (!list.contains(entry)) {
            return false;
        }
        list.remove(entry);
        saveAll();
        return true;
    }

    @Override
    public boolean updatePath(int id, char type, ResultadoPercurso path) {
        Printable entry = select(id,type);
        if (!list.contains(entry)) {
            return false;
        }
        entry.setPath(path);
        saveAll();
        return true;
    }
}
