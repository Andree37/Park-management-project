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
public class Ticket extends Printable {

    private static int TICKET_NUMBER = 1;
    private static final String TYPE_NAME = "Ticket";
    private final int IVA = 23;
    private final int number;
    private final boolean bikeAccess;
    private boolean bridgesAllowed;
    private final Date date;
    private final String clientName;
    private ResultadoPercurso path;

    /* o tipo de percurso selecionado, uma descrição detalhada
    do mesmo, indicando todas as conexões a percorrer e a distância das mesmas, assim como o valor
    total do percurso e a data e hora de emissão do mesmo.*/

    public Ticket(String clientName, ResultadoPercurso path) {
        number = TICKET_NUMBER++;
        this.date = new Date();
        this.path = path;
        this.clientName = clientName;
        this.bikeAccess = path.getBikeAccess();
        this.bridgesAllowed = path.isBridgesAllowed();
    }

    @Override
    public String getBody() {
        String output = String.format("Ticket Nº %d | Cliente : %s \n", number, clientName);
        output += date.toString();//needs formating
        output += "----------------\n";
        
        for (Place p : path.getListPlacesCopy()) {
            output += p.toString() + "\n";
        }
        output += "----------------\n";
        for (Connection c : path.getListConnectionsCopy()) {
            output += c.toString()+ "Distance to travel through - "+c.getDistance()+ "\n";
        }
        
        
        if (bridgesAllowed) {
            output += "Path will have bridges & ";
        }else {
            output += "Path will not have bridges & ";
        }
        if (bikeAccess) {
            output += "has bike access";
        }
        else {
            output += "has no need for bike access";
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
