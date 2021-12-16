package com.jclapel.banksystem.data;

// Imports
import com.jclapel.banksystem.back_end.*;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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
	private final ConnectionString DATABASE_CONNECTION = new ConnectionString("mongodb+srv://JCLAPEL:IuXyiBQNVp40FVM8@clusterjclapel.5onkg.mongodb.net/test?authSource=admin&replicaSet=atlas-9p5bw4-shard-0&readPreference=primary&ssl=true");
	private final JsonWriterSettings jsonSettings = JsonWriterSettings.builder()
		.int64Converter((value, writer) -> writer.writeNumber(value.toString()))
		.build();
	private final CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
	private final CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
	private final MongoClientSettings clientSettings = MongoClientSettings.builder()
		.applyConnectionString(DATABASE_CONNECTION)
		.codecRegistry(codecRegistry)
		.build();
	
	private Gson gson = new Gson();

	private final boolean USE_LOCAL_STORAGE = true;
	private final String DATABASE_NAME = "test";

	private FileInputStream cacheSource;
	private FileOutputStream cacheTarget;

	private HashMap<String, Object> dataCache;

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

	public Cache() throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}
	}

	public Cache(Set<String> keySet) throws Exception {
		// Executes initial procedure on program start
		dataCache = new HashMap<String, Object>();

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}
	}

	public Cache(HashMap<String, Object> presetCache) throws Exception {
		// Executes initial procedure on program start
		dataCache = presetCache;

		if (USE_LOCAL_STORAGE) {
			setupLocalStorage();
		}
	}
	
	public boolean appendData(Customer customer) {
		// Creates an entry to the database for one customer
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Customer> customerCollection = database.getCollection("customers", Customer.class);			

			customerCollection.insertOne(customer);
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public boolean appendData(Account account) {
		// Adds new document of account to accounts collection in database
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Account> accountCollection = database.getCollection("accounts", Account.class);			

			accountCollection.insertOne(account);
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public boolean appendData(Transaction transaction) {
		// Adds new document of transaction to transactions collection in database
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Transaction> transactionCollection = database.getCollection("transactions", Transaction.class);			

			transactionCollection.insertOne(transaction);
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public void updateData(Customer customer) {
		// Updates object to cache
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Customer> customerCollection = database.getCollection("customers", Customer.class);

			Bson filter = eq("customer_id", customer.getId());
			FindOneAndReplaceOptions findOneAndReplaceOptions = new FindOneAndReplaceOptions().upsert(true);

			customerCollection.findOneAndReplace(filter, customer, findOneAndReplaceOptions);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateData(Account account) {
		// Updates object to cache
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Account> accountCollection = database.getCollection("accounts", Account.class);

			Bson filter = eq("account_id", account.getId());
			FindOneAndReplaceOptions findOneAndReplaceOptions = new FindOneAndReplaceOptions().upsert(true);

			accountCollection.findOneAndReplace(filter, account, findOneAndReplaceOptions);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateData(Transaction transaction) {
		// Updates object to cache -- WARNING: Do not use!
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Transaction> transactionCollection = database.getCollection("transactions", Transaction.class);

			Bson filter = eq("transaction_id", "");
			FindOneAndReplaceOptions findOneAndReplaceOptions = new FindOneAndReplaceOptions().upsert(true);

			transactionCollection.findOneAndReplace(filter, transaction, findOneAndReplaceOptions);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public Customer getCustomerData(String customerId) {
		// Returns customer object constructed from database
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Customer> customerCollection = database.getCollection("customers", Customer.class);

			Customer customer = customerCollection.find(eq("customer_id", customerId)).first();
			return customer;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public Account getAccountData(String accountId) {
		// Returns account object constructed from database
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			MongoCollection<Account> accountCollection = database.getCollection("accounts", Account.class);

			Account account = accountCollection.find(eq("account_id", accountId)).first();
			return account;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public boolean updateAllData(Map<Integer, ?> collection) {
		// Creates an entry to the database for one customer
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			
			for (var object : collection.values()) {
				System.out.println(object.getClass().getName() + "s");
				// MongoCollection<Document> dataCollection = database.getCollection("");			
				// TODO: Sorting this out...
			}
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public boolean saveAllData() {
		// Serializes and sends data to database
		try {
			MongoClient client = MongoClients.create(clientSettings);
			MongoDatabase database = client.getDatabase(DATABASE_NAME);
			// MongoIterable<String> collectionNameList = database.listCollectionNames();
			
			MongoCollection<Customer> customerCollection = database.getCollection("customers", Customer.class);
			MongoCollection<Account> accountCollection = database.getCollection("accounts", Account.class);
			MongoCollection<Transaction> transactionCollection = database.getCollection("transactions", Transaction.class);

			// TODO: Iterate through the cache. Insert to the collections.
			return true;
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public void close() {
		// Performs the necessary final steps before program closes?
	}
}
