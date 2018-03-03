package com.common;

import redis.clients.jedis.Jedis;

public class RedisHelper {
	public RedisHelper(String hostname, int hostport, String authpwd) {
		HostName = hostname;
		HostPort = hostport;
		AuthPwd = authpwd;
		redismanager = GetConnection();
	}

	private String HostName;
	private int HostPort;
	private String AuthPwd;
	private Jedis redismanager;

	public Jedis GetConnection() {
		Jedis redis = new Jedis(HostName, HostPort);
		if (CommonHelper.StrIsNotNull(AuthPwd)) {
			redis.auth(AuthPwd);
		}
		return redis;
	}

	public String Get(String key) {
		return redismanager.get(key);
	}

	public void Set(String key, String data) {
		redismanager.set(key, data);
	}

	public void Set(String key, String data, int seconds) {
		redismanager.set(key, data);
		redismanager.expire(key, seconds);
	}

	public void del(String key) {
		redismanager.del(key);
	}

	public void QueuePush(String queuename, String value) {
		redismanager.rpush(queuename, value);
	}

	public String QueuePop(String queuename) {
		return redismanager.rpop(queuename);
	}
}
