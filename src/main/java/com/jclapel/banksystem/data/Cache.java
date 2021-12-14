package com.jclapel.banksystem.data;

// Imports
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.zip.DataFormatException;

import com.jclapel.banksystem.back_end.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class Cache implements Serializable {
	/*

	Cache
	A class to manage data and streams between the system and an assumed existing database. 
	This will serialize Java objects into JSON objects and upload the JSON objects into a database(temporarily will be locally stored).
	Also will deserialize JSON objects after requesting from the same database.

	Additionally, Cache should be running asynchronously to the software, and only synchronize changes upon conditions of opening and closing.

	Main Contributor(s): Conny
	Contributor(s): 

	TODO: Read the documents as follows:
	https://www.mongodb.com/developer/quickstart/java-setup-crud-operations/
	https://docs.mongodb.com/mongodb-vscode/
	https://docs.mongodb.com/manual/reference/bson-types/
	
	*/
	private final boolean USE_LOCAL_STORAGE = true;
	private final boolean USE_DATABASE = false;
	private final String DATABASE_NAME = "test";
	private final String DATABASE_CONNECTION = "mongodb+srv://JCLAPEL:IuXyiBQNVp40FVM8@clusterjclapel.5onkg.mongodb.net/test?authSource=admin&replicaSet=atlas-9p5bw4-shard-0&readPreference=primary&ssl=true";

	private FileInputStream cacheSource;
	private FileOutputStream cacheTarget;

	private HashMap<String, Object> dataCache;

	private Document toBSONDocument(Customer customer) {
		// Serializes a customer object into a BSON document
		return new Document("_id", new ObjectId())
			.append("customer_id", customer.getId())
			.append("name", customer.getName())
			.append("password", customer.getPassword())
			.append("accounts", getAccountsDocumentList(customer.getAccounts()));
	}

	private List<Document> getAccountsDocumentList(Map<Integer, Account> accounts) {
		// Serializes a list of accounts into a BSON document containing only the IDs to the accounts
		List<Document> accountsDocumentList = new ArrayList<Document>();

		for (Account account : accounts.values()) {
			// TODO: isSavings needs to redefined, could it possibly be more than 1 type? Also transactions.
			accountsDocumentList.add(new Document("account_id", account.getId()));
		}

		return accountsDocumentList;
	} 

	private Document toBSONDocument(Account account) {
		// Serializes an account object into a BSON document
		return new Document("_id", new ObjectId())
			.append("type", "default")
			.append("account_id", account.getId())
			.append("balance", account.getBalance())
			.append("transactions", getTransactionsDocumentList(account.getTransactions()));
	}

	private List<Document> getTransactionsDocumentList(Stack<Transaction> transactions) {
		// Serializes a list of transaction object into a BSON document
		List<Document> transactionsDocumentList = new ArrayList<Document>();

		for (Transaction transaction : transactions) {
			transactionsDocumentList.add(new Document("type", "default")
				.append("date", transaction.getDate())
				.append("time", transaction.getTime())
				.append("amount", transaction.getAmount()));
		}

		return transactionsDocumentList;
	}

	private Document toBSONDocument(Transaction transaction) {
		// Serializes a transaction object into a BSON document
		return new Document("_id", new ObjectId())
			.append("type", "default")
			.append("date", transaction.getDate())
			.append("time", transaction.getTime())
			.append("amount", transaction.getAmount());
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

	public void initialize() throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}

		if (USE_DATABASE) {
			// setupDatabase();
		}
	}

	public void initialize(Set<String> keySet) throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}

		if (USE_DATABASE) {
			// setupDatabase();
		}
	}

	public void initialize(HashMap<String, Object> presetCache) throws Exception {
		// Executes initial procedure on program start
		dataCache = presetCache;

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}

		if (USE_DATABASE) {
			// setupDatabase();
		}
	}
	
	public void appendData(Customer customer) {
		// Creates an entry to the database for one customer
		if (USE_DATABASE) {
			try {
				MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
				MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
				MongoCollection<Document> dataCollection = database.getCollection("customers");			
				Document customerDocument = toBSONDocument(customer);

				dataCollection.insertOne(customerDocument);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public void appendData(Account account) {
		// Creates an entry to the database for one account
		if (USE_DATABASE) {
			try {
				MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
				MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
				MongoCollection<Document> dataCollection = database.getCollection("accounts");			
				Document accountDocument = toBSONDocument(account);

				dataCollection.insertOne(accountDocument);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public void appendData(Transaction transaction) {
		// Adds object to cache TODO: UPDATE!!!
		if (USE_DATABASE) {
			try {
				MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
				MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
				MongoCollection<Document> dataCollection = database.getCollection("transactions");			
				Document transactionDocument = toBSONDocument(transaction);

				dataCollection.insertOne(transactionDocument);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public void updateData(Customer customer) {
		// Updates object to cache
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("customers");

			Document targetDocument = dataCollection.find(new Document("customer_id", customer.getId())).first();
			// TODO: Update the target document
		} catch(Exception exception) {
			// TODO: Error handling
			exception.printStackTrace();
		}
	}

	public void updateData(Account account) {
		// Updates object to cache
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("accounts");

			Document targetDocument = dataCollection.find(new Document("account_id", account.getId())).first();
			// TODO: Update the target document
		} catch(Exception exception) {
			// TODO: Error handling
			exception.printStackTrace();
		}
	}

	public void updateData(Transaction transaction) {
		// Updates object to cache -- WARNING: This is hard to identify!
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("transactions");

			Document targetDocument = dataCollection.find(new Document("", "")).first(); // TODO: How do we identify this?
			// TODO: Update the target document
		} catch(Exception exception) {
			// TODO: Error handling
			exception.printStackTrace();
		}
	}

	public Object getData(String collection, String identifier, String dataId) {
		// Returns object from the data map from a key
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection(collection);

			Document targetDocument = dataCollection.find(new Document(identifier, dataId)).first();
			// TODO: Update the target document
		} catch(Exception exception) {
			// TODO: Error handling
			exception.printStackTrace();
		}

		return null;
	}

	public void saveData() {
		// Serializes and sends data to database
		// TODO: Send data with this string
		if (USE_LOCAL_STORAGE) {
			// Save data locally
		}

		if (USE_DATABASE) {
			// Save data on database
			try {
				MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
				MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

				MongoCollection<Document> customerCollection = database.getCollection("customers");
				MongoCollection<Document> accountCollection = database.getCollection("accounts");
				MongoCollection<Document> transactionsCollection = database.getCollection("transactions");

				// TODO: Iterate through the cache. Insert to the collections.
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
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

	public void syncData() {
		// Synchronizes data from database to local
	}

	public void close() {
		// Performs the necessary final steps before program closes?
	}
}
