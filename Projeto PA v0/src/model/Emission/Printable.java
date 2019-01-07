/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Emission;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import model.Gestor.ResultadoPercurso;

/**
 *
 * @author AndreLaptop
 */
public abstract class Printable {
    
    public abstract String getBody();
    
    public String print() {
        String output = String.format("Date: %s \n", LocalDate.now(ZoneId.of("Europe/Lisbon")));
        output += String.format("Time of emition: %s", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
        output += "\n";
        output += getBody();
        output += "\n\n";
        output += "Thank you for choosing this place to spend some time out! Gestor.inc";
        
        return output;
    }
    
    public abstract int getID();
    
    public abstract String getType();
    
    public abstract void setPath(ResultadoPercurso path);
    
    public abstract ResultadoPercurso getPath();
}
