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
    private final int IVA = 23;

    private final int number;
    private final String nif;
    private final Date date;
    private static final String name = "Bill";

    private ResultadoPercurso path;

    public Bill(String nif, Date date, ResultadoPercurso path) {
        number = BILL_NUMBER++;
        this.nif = nif;
        this.date = date;
        this.path = path;
    }

    @Override
    public String getBody() {
        String output = String.format("Bill Nº %d | Cliente NIF: %s \n", number, nif);
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
        double totalWithIva = totalWithoutIva * IVA;

        output += String.format("Total No IVA: %.2f€ \n", totalWithoutIva);
        output += String.format("Total Tax: %.2f€ \n", (totalWithIva - totalWithoutIva));
        output += String.format("Total Bill: %.2f€ \n", totalWithIva);

        return output;
    }

    @Override
    public String getType() {
        return name;
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
