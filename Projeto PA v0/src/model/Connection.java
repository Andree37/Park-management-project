package model;

import java.util.List;
import java.util.Objects;

/**
 *
 * Class Connection, is responsible for the behavior of a connection
 *
 * @author (Daniel Afonso & André Ribeiro)
 * @version (19/11/18)
 */
public class Connection {

    /**
     * Enum Type is an enum for two different types of connection
     */
    public enum Type {

        /**
         *  Abstraction of physical bridge
         */
        BRIDGE,

        /**
         * Abstraction of physical path
         */
        PATH;

        /**
         *
         * @return String of type
         */
        public String getUnit() {
            switch (this) {
                case BRIDGE:
                    return "ponte";
                case PATH:
                    return "caminho";
                default:
                    return "desconhecido";
            }
        }
    };

    private int id;
    private String name;
    private Type type;
    private List<Integer> id_c; // ids for connected places
    private boolean available;
    private int price;
    private int distance;

    /**
     * Constructor for Connection
     *
     * @param id - the id of the connection
     * @param type - the type of the connection
     * @param name - the name of the connection
     * @param list - the list of other connected connections to this
     * @param available - boolean if its available for bikes
     * @param price - the price of the connection
     * @param distance - the distance of the connection
     */
    public Connection(int id, String type, String name, List<Integer> list, boolean available, int price, int distance) {
        this.id = id;
        this.name = name;
        if (type.contains(Type.BRIDGE.getUnit())) {
            this.type = Type.BRIDGE;
        }
        if (type.contains(Type.PATH.getUnit())) {
            this.type = Type.PATH;
        }
        this.available = available;
        this.price = price;
        this.distance = distance;
        this.id_c = list;
    }

    /**
     * Constructor of a default Connection, path , id = 0, not available, price and distance = 0 and no connected connections to itself
     */
    public Connection() {
        this(0, "caminho", "desconhecido", null, false, 0, 0);
    }

    /**
     * Returns the id of the connection
     *
     * @return int - the id of the connection
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the connection
     *
     * @return String - the name of the connection
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the connection
     *
     * @return String - the type of the connection
     */
    public String getType() {
        return type.getUnit();
    }

    /**
     * Returns the connections that are connected to this
     *
     * @return List - Connections connected to this
     */
    public List<Integer> getConnections() {
        return id_c;
    }

    /**
     * Returns the availability of the connection, that is if it accepts bikes or not
     *
     * @return boolean - true if is available, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Returns the price of the connection
     *
     * @return int - the price of the connection
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the distance of the connection
     *
     * @return int - the distance of the connection
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the toString() for this object
     *
     * @return String - toString() of this object
     */
    @Override
    public String toString() {
        if (type.getUnit().equals("caminho")) {
            if (available) {
                return "Caminho " + name + " com via de bicicleta (Preco:" + price + "$  Distance:" + distance + " metros " + ')';
            } else {
                return "Caminho " + name + " sem via de bicicleta (Preco:" + price + "$  Distance:" + distance + " metros " + ')';
            }
        } else {
            if (available) {
                return "Ponte " + name + " com via de bicicleta (Preco:" + price + "$  Distance:" + distance + " metros " + ')';
            } else {
                return "Ponte " + name + " sem via de bicicleta (Preco:" + price + "$  Distance:" + distance + " metros " + ')';
            }

        }
    }

    /**
     * Returns the hashcode of a certain object of Connection
     *
     * @return int- the hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Returns if two Connections are equal
     *
     * @param obj - the other Connection
     * @return boolean - true if its the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Connection other = (Connection) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
