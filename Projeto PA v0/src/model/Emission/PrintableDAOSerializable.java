/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Emission;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AndreLaptop
 */
public class PrintableDAOSerializable implements PrintableDAO{

    private List<Printable> list;
    private String fileName;

    public PrintableDAOSerializable(String fileName) {
        this.fileName = fileName;
        list = new ArrayList<>();
        loadAll();
    }

    private void loadAll() {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.list = (ArrayList<Printable>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException | ClassNotFoundException i) {
            return;
        }
    }
    
    private void saveAll() {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        }
        catch (IOException ex) {
            Logger.getLogger(PrintableDAOSerializable.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public Printable loadPrintable(int id, String type) {
        for(Printable p : list) {
            if(p.getID() == id && p.getType().equals(type)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void savePrintable(Printable p) {
        if(list.contains(p)) {
            return;
        }
        list.add(p);
        saveAll();
    }
}
