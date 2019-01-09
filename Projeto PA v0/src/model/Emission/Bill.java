/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Emission;

import java.util.Date;
import model.Gestor.Connection;
import model.Gestor.Place;
import model.Gestor.ResultadoPercurso;

/**
 *
 * @author AndreLaptop
 */
public class Bill extends Printable {

    private static int BILL_NUMBER = 1;
    private static final String TYPE_NAME = "Bill";
    private final double IVA = 0.23;

    private final int number;
    private final String nif;
    private final String clientName;
    private final String address;
    
    

    private ResultadoPercurso path;

    public Bill(String nif,String clientName, String address, ResultadoPercurso path) {
        super();
        number = BILL_NUMBER++;
        this.nif = nif;
        this.path = path;
        this.clientName = clientName;
        this.address = address;      
    }
   
    @Override
    public String getBody() {
        String output = String.format("Bill Nº %d | Cliente NIF: %s \n", number, nif);
        output += String.format("Client Name: %s | Client Address: %s\n", clientName,address);
        output += "----------------\n";
        for (Place p : path.getListPlacesCopy()) {
            output += p.toString() + "\n";
        }
        output += "----------------\n";
        for (Connection c : path.getListConnectionsCopy()) {
            output += c.toString() + "\n";
        }
        output += "----------------\n";
        double totalWithoutIva = path.getCost();
        double totalWithIva = totalWithoutIva * IVA + totalWithoutIva;

        output += String.format("Total No IVA: %.2f€ \n", totalWithoutIva);
        output += String.format("Total Tax: %.2f€ \n", (totalWithIva - totalWithoutIva));
        output += String.format("Total Bill: %.2f€ \n", totalWithIva);

        return output;
    }

    @Override
    public String getType() {
        return TYPE_NAME;
    }

    @Override
    public void setPath(ResultadoPercurso path) {
        if (path != null) {
            this.path = path;
        }
    }

    @Override
    public int getID() {
        return number;
    }

    @Override
    public ResultadoPercurso getPath() {
        return path;
    }
}
