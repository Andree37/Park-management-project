/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Darfkman
 */
public class Connection {

	private enum Type {
		BRIDGE, PATH;

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

	public Connection(int id, Type type, String name, boolean available, int price, int distance) {
		this.id = id;
		this.name = name;
		if (type.getUnit().equals("ponte") || type.getUnit().equals("caminho")) {
			this.type = type;
		} else {
			throw new InputMismatchException("Type of connection is invalid!" + type);
		}
		this.id_c = new ArrayList<>();
		this.available = available;
		this.price = price;
		this.distance = distance;
	}

	public Connection() {
		this(0, Type.PATH, "desconhecido", true, 0, 0);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type.getUnit();
	}

	public List<Integer> getConnections() {
		return id_c;
	}

	public boolean isAvailable() {
		return available;
	}

	public int getPrice() {
		return price;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		if (type.equals("caminho")) {
			if (available) {
				return "Caminho " + name + " com via de bicicleta " + price + "€ " + distance + " metros " + ')';
			} else {
				return "Caminho " + name + " sem via de bicicleta " + price + "€ " + distance + " metros " + ')';
			}
		} else {
			if (available) {
				return "Ponte " + name + " com via de bicicleta " + price + "€ " + distance + " metros " + ')';
			} else {
				return "Ponte " + name + " sem via de bicicleta " + price + "€ " + distance + " metros " + ')';
			}

		}
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + this.id;
		hash = 97 * hash + Objects.hashCode(this.name);
		return hash;
	}

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
