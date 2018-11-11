
package FileHandler;

import java.util.*;
import model.Connection;
import model.Place;
/**
 * Classe Objetos representa uma classe que controla a lista de objetos usados no programa
 *
 * @author (Daniel Afonso)
 * @version (11/11/18)
 */
public class Objects
{
    private ArrayList<Object> listOfObjects;
    private ArrayList<Connection> listOfConnections;
    private ArrayList<Place> listOfPlaces;

    /** Inicializa a lista de objetos
     *
     */
    public Objects()
    {
        listOfObjects = new ArrayList<>();
        listOfConnections = new ArrayList<>();
        listOfPlaces = new ArrayList<>();
    }

    /** Adiciona um objeto á lista
     *
     * @param object - objeto a adicionar
     */
    public void addObject(Object object){   
        listOfObjects.add(object);    
    }

    /** Retorna a lista de objetos
     *
     * @return - a lista 
     */
    public ArrayList listObjects(){
      return listOfObjects;
    }
    /** Retorna a lista de Places
     *
     * @return - a lista 
     */
    public ArrayList listPlaces(){
        
        for(Object obj : listOfObjects){
                if(obj.getClass().equals(new Place().getClass())){
                    listOfPlaces.add((Place)obj);
                 }
                }
      return listOfPlaces;
    }
    public ArrayList listConnections(){        
        for(Object obj : listOfObjects){
                if(obj.getClass().equals(new Connection().getClass())){
                    listOfConnections.add((Connection)obj);
                 }
                }
      return listOfConnections;
    }
    /** Retorna uma string de objetos e os seus estados
     *
     * @return string - lista dos objetos e os seus estados
     */
    public String listOfObjects(){
        String result = "";
        for(Object object : listOfObjects){
            
             result +=object.toString()+"\r\n";   
            
      }
      return result;
    }
    }