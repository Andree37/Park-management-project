/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

import Logger.Logger;
import java.util.Date;
import java.util.List;
import model.Emission.Printable;

/**
 *
 * @author AndreLaptop
 */
public class ShowStatistics {

    //private PrintableDAOOneJson printables;

    public ShowStatistics() {
        //this.printables = new PrintableDAOOneJson("", "Ticket");
        Logger.getInstance().writeToLog("Stat " + " consultado " + new Date().toString());
    }

    public void getMostVisitedPlaces() {
       
        /*List<Printable> prints = printables.selectAll();
        //create bar graph for tickets
        ChartBar br = new ChartBar();
        for (Printable p : prints) {
            if (p.getType().equals("Ticket")) {
                br.addResult(p.getPath());
            }
        }
*/
    }
}
