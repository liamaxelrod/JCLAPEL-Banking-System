package com.jclapel.banksystem.data;

// Imports
import com.jclapel.banksystem.back_end.*;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;

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

	private final JsonWriterSettings jsonSettings = JsonWriterSettings.builder()
		.int64Converter((value, writer) -> writer.writeNumber(value.toString()))
		.build();
	
	private Gson gson = new Gson();

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
			accountsDocumentList.add(new Document("account_id", account.getId()));
		}

		return accountsDocumentList;
	} 

	private Document toBSONDocument(Account account) {
		// Serializes an account object into a BSON document
		return new Document("_id", new ObjectId())
			.append("is_saving", account.isSavings())
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

	public boolean appendData(Account account) {
		// Adds new document of account to accounts collection in database
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("accounts");			
			Document accountDocument = toBSONDocument(account);

			dataCollection.insertOne(accountDocument);
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public boolean appendData(Transaction transaction) {
		// Adds new document of transaction to transactions collection in database
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("transactions");			
			Document transactionDocument = toBSONDocument(transaction);

			dataCollection.insertOne(transactionDocument);
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public boolean updateAll(Map<Integer, ?> collection) {
		// Creates an entry to the database for one customer
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			
			for (var object : collection.values()) {
				System.out.println(object.getClass());
				// MongoCollection<Document> dataCollection = database.getCollection("");			
				// TODO: Sorting this out...
			}
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public void updateData(Customer customer) {
		// Updates object to cache
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("customers");

			Document customerDocument = toBSONDocument(customer);
			Bson filter = eq("customer_id", customer.getId());
			ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);

			dataCollection.replaceOne(filter, customerDocument, replaceOptions);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateData(Account account) {
		// Updates object to cache
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("accounts");

			Document accountDocument = toBSONDocument(account);
			Bson filter = eq("account_id", account.getId());
			ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);

			dataCollection.replaceOne(filter, accountDocument, replaceOptions);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateData(Transaction transaction) {
		// Updates object to cache -- WARNING: Do not use!
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("transactions");

			Document transactionDocument = toBSONDocument(transaction);
			Bson filter = eq("transaction_id", "");
			ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);

			dataCollection.replaceOne(filter, transactionDocument, replaceOptions);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public Customer getCustomerData(String customerId) {
		// Returns customer object constructed from database
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("customers");

			Document targetDocument = dataCollection.find(new Document("customer_id", customerId)).first();
			Customer customer = gson.fromJson(targetDocument.toJson(jsonSettings), Customer.class);
			return customer;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public Account getAccountData(String accountId) {
		// Returns account object constructed from database
		try {
			MongoClient mongoClient = MongoClients.create(DATABASE_CONNECTION);
			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
			MongoCollection<Document> dataCollection = database.getCollection("accounts");

			Document targetDocument = dataCollection.find(new Document("account_id", accountId)).first();
			Account account = gson.fromJson(targetDocument.toJson(jsonSettings), Account.class);
			return account;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void saveAllData() {
		// Serializes and sends data to database
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

	public HashMap<Integer, ?> loadDataCollection() {
		// Deserializes inbound data acquired from database
		// TODO: Convert the string into a map
		return null;
	}

	public void syncData() {
		// Synchronizes data from database to local
	}

	public void close() {
		// Performs the necessary final steps before program closes?
	}
}
