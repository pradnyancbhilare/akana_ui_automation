package com.perforce.akana.qa.testng.datamodel.jax;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.perforce.akana.qa.testng.jax.StringUserMapXmlAdapter;

@XmlRootElement(name = "users")
public class Users {

	private Map<String, User> users = new HashMap<>();

	@XmlJavaTypeAdapter(StringUserMapXmlAdapter.class)
	@XmlElement(name = "user")
	public Map<String, User> getUsersMap() {
		return users;
	}

	public void setUsersMap(Map<String, User> users) {
		this.users = users;
	}

	public User getUser(String key) {
		return users.get(key);
	}

}
