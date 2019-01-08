/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Emission;

/**
 *
 * @author AndreLaptop
 */
public interface PrintableDAO {

    public void savePrintable(Printable p);
    public Printable loadPrintable(int id, String type);
}
