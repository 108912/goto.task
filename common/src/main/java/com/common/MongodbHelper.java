package com.common;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongodbHelper {

	public MongodbHelper(String host, int port) {
		this.MongodbHost = host;
		this.MongodbPort = port;
	}

	public MongodbHelper(String host, int port, String user, String pwd, String dbname) {
		this.MongodbHost = host;
		this.MongodbPort = port;
		this.MongodbUser = user;
		this.MongodbPwd = pwd;
		this.MongodbDBName = dbname;
	}

	private String MongodbHost = "172.0.0.1";
	private int MongodbPort = 27017;
	private String MongodbUser = "";
	private String MongodbPwd = "";
	private String MongodbDBName = "";
	private MongoClient MongodbService = null;

	public MongoClient LoadMongodbService() {
		if (MongodbService != null) {
			return MongodbService;
		}
		ServerAddress serverAddress = new ServerAddress(MongodbHost, MongodbPort);
		List<MongoCredential> credentialList = new ArrayList<MongoCredential>();

		if (MongodbUser != null && MongodbUser != "" && MongodbUser.trim().length() > 0) {
			MongoCredential credential = MongoCredential.createScramSha1Credential(MongodbUser, MongodbDBName,
					MongodbPwd.toCharArray());
			credentialList.add(credential);
		}
		return new MongoClient(serverAddress, credentialList);
	}
}
