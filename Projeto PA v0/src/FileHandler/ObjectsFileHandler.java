
package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Connection;
import model.Place;
/**
 * Classe ObjectsFileHandler faz a serialização dos objetos, guarda 
 * e recarrega a sua informação dos mesmos 
 *
 * @author (Daniel Afonso & André Ribeiro)
 * @version (19/11/18)
 */
public abstract class ObjectsFileHandler implements java.io.Serializable
{
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObjectsFileHandler()
    {
       
    }
    
    /**Imprime os objetos para um ficheiro 
     *
     * @param filename - o nome do ficheiro
     * @param text - o texto que neste caso será a informação do objeto
     */
    static public void printToFile(String filename, String text){
      try {
            File file = new File(filename+".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /** Guarda o ficheiro com a lista de objetos
     *
     * @param filename - nome do ficheiro em que guarda a informação
     * @param listOfObjects - a lista de objetos e os seus estados 
     */
    static public void save(String filename, ArrayList<Objects> listOfObjects){    
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filename));
            
            oos.writeObject(listOfObjects);
           
            oos.flush();
            oos.close();
            } catch (IOException e) {
            System.out.println(e.getMessage());
       }
    }
   
    /**Recarrega a informação dum ficheiro
     *
     * @param filename - ficheiro onde carregar e informação sobre os objetos
     * @return lista dos objetos e os seus estados
     */
    static public Objects load() 
        {
            Objects objects = new Objects();
            ArrayList<Objects> listOfObjects = null;
            BufferedReader reader;
            String filename = "";
            JFileChooser chooser = new JFileChooser();  
            chooser.setCurrentDirectory(new java.io.File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text or dat", "dat", "txt");
             chooser.setFileFilter(filter);
             int returnVal = chooser.showOpenDialog(null);
             filename = chooser.getSelectedFile().getAbsolutePath();
            if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                   filename);
            }
            try {
            listOfObjects = new ArrayList<>();
            reader = new BufferedReader(new FileReader(filename));
            //Starts reading file by line
            String line = reader.readLine();
            int numberOfPlaces = Integer.parseInt(line.trim());
            System.out.println("numberOfPlaces :" + numberOfPlaces);
            
                for(int i=0; i<numberOfPlaces; i++){
                 //read the n lines that are places, n = first line of file
                 line = reader.readLine();
                 System.out.println("Place :"+ line);  
                 String[] split = line.split(", ");              
                 Place place = new Place(Integer.parseInt(split[0]),split[1]);
                 objects.addObject(place);                 
                }
                line = reader.readLine();
                int numberOfConnections = Integer.parseInt(line.trim());
                System.out.println("numberOfConnections :" + numberOfConnections);
                for(int i=0; i<numberOfConnections; i++){
                 //read the n lines that are connections, n = first line of file
                 line = reader.readLine();
                 System.out.println("Connection :"+ line);  
                 String[] split = line.split(", ");  
                 List<Integer> list = new ArrayList<>();
                 list.add(Integer.parseInt(split[3]));
                 list.add(Integer.parseInt(split[4]));
                 Connection connection = new Connection(Integer.parseInt(split[0]),split[1],split[2],list,Boolean.parseBoolean(split[5]),Integer.parseInt(split[6]),Integer.parseInt(split[7]));
                 objects.addObject(connection);
                }
            } catch (IOException e) {
            System.out.println(e.getMessage());
            listOfObjects = new ArrayList<>();
            }
        return objects;
    }
}
