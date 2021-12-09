package com.jclapel.banksystem.data;

// Imports
import com.google.gson.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.jclapel.banksystem.back_end.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Cache implements Serializable {
	/*

	Cache
	A class to manage data and streams between the system and an assumed existing database. 
	This will serialize Java objects into JSON objects and upload the JSON objects into a database(temporarily will be locally stored).
	Also will deserialize JSON objects after requesting from the same database.

	Main Contributor(s): Conny
	Contributor(s): 

	TODO: Read the documents as follows:
	https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
	https://docs.mongodb.com/mongodb-vscode/
	https://docs.mongodb.com/manual/reference/bson-types/
	

	*/

	private final boolean USE_LOCAL_STORAGE = true;
	private final boolean USE_DATABASE = false;
	private final String DEFAULT_DATABASE = "test";

	private FileInputStream cacheSource;
	private FileOutputStream cacheTarget;

	private HashMap<String, Object> dataCache;
	
	Gson gson = new Gson();

	MongoClient mongoClient;
	DB database;

	DBCollection accounts;
	// TODO: More collections for later...

	private Document toBSONDocument(Customer customer) {
		return new Document("_id", new ObjectId())
			.append("customer_id", customer.getId())
			.append("name", customer.getName())
			.append("password", customer.getPassword())
			.append("accounts", getAccountsDocumentList(customer.getAccounts()));
	}

	private List<Document> getAccountsDocumentList(Map<Integer, Account> accounts) {
		List<Document> accountsDocumentList = new ArrayList<Document>();

		for (Account account : accounts.values()) {
			// TODO: isSavings needs to redefined, could it possibly be more than 1 type? Also transactions.
			accountsDocumentList.add(new Document("type", "default")
				.append("accountId", account.getId())
				.append("balance", account.getBalance())
				.append("transactions", getTransactionsDocumentList(account.getTransactions())));
		}

		return accountsDocumentList;
	} 

	private List<Document> getTransactionsDocumentList(Stack<Transaction> transactions) {
		List<Document> transactionsDocumentList = new ArrayList<Document>();

		for (Transaction transaction : transactions) {
			transactionsDocumentList.add(new Document("type", "")
				.append("date", transaction.getDate())
				.append("time", transaction.getTime())
				.append("amount", transaction.getAmount()));
		}

		return transactionsDocumentList;
	}

	private Document toBSONDocument(Account account) {
		return null;
	}

	private Document toBSONDocument(Transaction transaction) {
		return null;
	}

	private void setupLocalStorage() throws Exception {
		// Sets up the local storaging, if applicable
		try {
			cacheSource = new FileInputStream("data.json");
			cacheTarget = new FileOutputStream("data.json");
		} catch(Exception exception) {
			// Error handling here
			exception.printStackTrace();
		} finally {
			if (cacheSource != null) {
				cacheSource.close();
			}
			
			if (cacheTarget != null) {
				cacheTarget.close();
			}
		}
	}

	private void setupDatabase() throws Exception {
		// Sets up the database client and connection, if applicable
		try {
			mongoClient = new MongoClient();

			database = mongoClient.getDB(DEFAULT_DATABASE);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

//	private void setupDatabase(String database) throws Exception {
//		try {
//			mongoClient = new MongoClient();
//
//			this.database = mongoClient.gseetDB(database);
//		} catch(Exception exception) {
//			exception.printStackTrace();
//		}
//	}

	public void initialize() throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}

		if (USE_DATABASE) {
			setupDatabase();
			
			accounts = database.getCollection("accounts");
		}
	}

	public void initialize(Set<String> keySet) throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}

		if (USE_DATABASE) {
			setupDatabase();
		}
	}

	public void initialize(HashMap<String, Object> presetCache) throws Exception {
		// Executes initial procedure on program start
		dataCache = presetCache;

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}

		if (USE_DATABASE) {
			setupDatabase();
		}
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
		String serializedData = gson.toJson(dataCache);

		if (USE_LOCAL_STORAGE) {
			// Save data locally
		}

		if (USE_DATABASE) {
			// Save data on database
		}

		System.out.println(serializedData); // Needs further testing
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
