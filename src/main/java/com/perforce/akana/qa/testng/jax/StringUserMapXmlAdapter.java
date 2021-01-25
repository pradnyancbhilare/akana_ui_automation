package com.perforce.akana.qa.testng.jax;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.perforce.akana.qa.exception.TestCodeException;
import com.perforce.akana.qa.testng.datamodel.jax.User;

public class StringUserMapXmlAdapter extends XmlAdapter<User, Map<String, User>> {
	private static Map<String, User> map = new HashMap<>();

	@Override
	public User marshal(Map<String, User> map) throws Exception {
		System.out.println("Marshal");
		throw new TestCodeException("Marshalling is not supported, please implement");
	}

	@Override
	public Map<String, User> unmarshal(User entry) throws Exception {
		if (entry == null) {
			return map;
		}

		map.put(entry.getKey(), entry);

		return map;
	}
}
