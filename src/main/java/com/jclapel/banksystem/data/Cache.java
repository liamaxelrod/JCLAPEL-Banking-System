package com.jclapel.banksystem.data;

// Imports
// import com.google.code.gson;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Cache implements Serializable {
	/*

	Cache
	A class to manage data and streams between the system and an assumed existing database. 
	This will serialize Java objects into JSON objects and upload the JSON objects into a database(temporarily will be locally stored).
	Also will deserialize JSON objects after requesting from the same database.

	*/
	private final boolean USE_LOCAL_STORAGE = true;
	private final boolean USE_DATABASE = false;

	private FileInputStream cacheSource;
	private FileOutputStream cacheTarget;

	private HashMap<String, Object> dataCache;
	
	// Gson gson = new Gson();

	private void setupLocalStorage() throws Exception {
		try {
			cacheSource = new FileInputStream("data.json");
			cacheTarget = new FileOutputStream("data.json");
		} catch(Exception e) {
			// Error handling here
		} finally {
			if (cacheSource != null) {
				cacheSource.close();
			}
			
			if (cacheTarget != null) {
				cacheTarget.close();
			}
		}
	}

	public void initialize() throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		setupLocalStorage();
	}

	public void initialize(Set<String> keySet) throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		setupLocalStorage();
	}

	public void initialize(HashMap<String, Object> presetCache) throws Exception {
		// Executes initial procedure on program start
		dataCache = presetCache;

		setupLocalStorage();
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

		if (USE_LOCAL_STORAGE) {
			// Save data locally
		}

		if (USE_DATABASE) {
			// Save data on database
		}

		//System.out.println(serializedData); // Needs further testing
	}

	public void loadData(String serializedData) {
		// Deserializes inbound data acquired from database
		// TODO: Convert the string into a map
		if (USE_LOCAL_STORAGE) {
			// Get data locally
		}

		if (USE_DATABASE) {
			// Get data on database
		}
	}

	public void close() {
		// Performs the necessary final steps before program closes?
	}
}
