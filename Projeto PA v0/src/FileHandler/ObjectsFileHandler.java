package FileHandler;

import Logger.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Gestor.Connection;
import model.Gestor.Place;

/**
 *
 * Abstract Class ObjectsFileHandler loads the information of file in a specific
 * format
 *
 * @author (Daniel Afonso e André Ribeiro)
 * @version (19/11/18)
 */
public abstract class ObjectsFileHandler implements java.io.Serializable {

    /**
     * ID for serialization
     */
    private static final long serialVersionUID = 1L;

    private ObjectsFileHandler() {

    }

    /**
     * Reloads the information
     *
     * @return Objects - the object that is loaded
     */
    static public Objects load() {
        Objects objects = new Objects();
        ArrayList<Objects> listOfObjects = null;
        BufferedReader reader;
        String filename=loadConfigFile();

        /*//creation of the filechooser to choose what file to load
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text or dat", "dat", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        filename = chooser.getSelectedFile().getAbsolutePath();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: "
                    + filename);   
        }*/
        try {
            listOfObjects = new ArrayList<>();
            reader = new BufferedReader(new FileReader(filename));
            //Starts reading file by line
            String line = reader.readLine();
            int numberOfPlaces = Integer.parseInt(line.trim());
            System.out.println("numberOfPlaces :" + numberOfPlaces);

            for (int i = 0; i < numberOfPlaces; i++) {
                //read the n lines that are places, n = first line of file
                line = reader.readLine();
                System.out.println("Place :" + line);
                String[] split = line.split(", ");
                Place place = new Place(Integer.parseInt(split[0]), split[1]);
                objects.addObject(place);
            }
            line = reader.readLine();
            int numberOfConnections = Integer.parseInt(line.trim());
            System.out.println("numberOfConnections :" + numberOfConnections);
            for (int i = 0; i < numberOfConnections; i++) {
                //read the n lines that are connections, n = first line of file
                line = reader.readLine();
                System.out.println("Connection :" + line);
                String[] split = line.split(", ");
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(split[3]));
                list.add(Integer.parseInt(split[4]));
                Connection connection = new Connection(Integer.parseInt(split[0]), split[1], split[2], list, Boolean.parseBoolean(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]));
                objects.addObject(connection);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            listOfObjects = new ArrayList<>();
        }
        //returns the Gestor loaded
        return objects;
    }

    private static String loadConfigFile() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");

            // loads the properties file
            prop.load(input);
            String dir = System.getProperty("user.dir");
            String filename = dir+"\\load\\" + prop.getProperty("LoadMap");
            System.out.println("You chose to open this file: " + filename);
            //visual check of the properties
            System.out.println(prop.getProperty("LoadMap"));
            System.out.println(prop.getProperty("LogTickets"));
            System.out.println(prop.getProperty("LogPaths"));
            System.out.println(prop.getProperty("LogStats"));
            Logger.getInstance().setConfig(Boolean.parseBoolean(prop.getProperty("LogTickets")),
                    Boolean.parseBoolean(prop.getProperty("LogPaths")),
                    Boolean.parseBoolean(prop.getProperty("LogStats")));
            return filename;
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }
}
