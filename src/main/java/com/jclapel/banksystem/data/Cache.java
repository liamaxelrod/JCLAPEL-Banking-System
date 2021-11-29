package com.jclapel.banksystem.data;

// Imports
//import com.google.gson.*;
import java.io.Serializable;
import java.util.HashMap;

public class Cache implements Serializable {
	/*

	Cache
	A class to manage data and streams between the system and an assumed existing database.

	*/
	private HashMap<String, Object> dataCache = new HashMap<String, Object>();
	
	//Gson gson = new Gson();

	public void initialize() {
		// Executes initial procedure on program start
	}

	public void appendData(String key, Object object) {
		// Adds object to cache
		if (dataCache.get(key) != null) {
			return;
		}

		dataCache.put(key, object);
	}

	public void updateData(String key, Object object) {
		// Updates object to cache
		if (dataCache.get(key) == null) {
			return;
		}

		dataCache.put(key, object);
	}

	public Object getData(String key) {
		// Returns object from the data map from a key
		return dataCache.get(key);
	}

	public void saveData() {
		// Serializes and sends data to database
		// TODO: Send data with this string
		//String serializedData = gson.toJson(dataCache);

		//System.out.println(serializedData); // Needs further testing
	}

	public void loadData(String serializedData) {
		// Deserializes inbound data acquired from database
		// TODO: Convert the string into a map
		
	}

	public void close() {
		// Performs the necessary final steps before program closes?
	}
}
