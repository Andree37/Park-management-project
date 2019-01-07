/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Emission;

import java.util.List;
import model.Gestor.ResultadoPercurso;

/**
 *
 * @author AndreLaptop
 */
public interface PrintableDAO {
    List<Printable> selectAll();
    Printable select(int id, String type);
    boolean insert(Printable entry, String type);
    boolean remove(int id, String type);
    boolean updatePath(int id, String type,ResultadoPercurso path);
}
