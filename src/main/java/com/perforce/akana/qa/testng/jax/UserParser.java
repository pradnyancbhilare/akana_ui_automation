package com.perforce.akana.qa.testng.jax;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.perforce.akana.qa.exception.TestCodeException;
import com.perforce.akana.qa.testng.datamodel.jax.Users;
import com.perforce.akana.qa.utils.Loader;

public class UserParser {

	public static Users parseXml(String filePath) {
		try {
			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Users users = (Users) jaxbUnmarshaller.unmarshal(file);
			return users;
		} catch (Exception e) {
			throw new TestCodeException(e);
		}
	}

	public static Users getUsers(String file) {
		String filePath = Loader.findAbsolutePath(file);
		return parseXml(filePath);
	}

}
