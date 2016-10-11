package com.cooksys.socket.assignment;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.LocalConfig;
import com.cooksys.socket.assignment.model.RemoteConfig;
import com.cooksys.socket.assignment.model.Student;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     * @throws JAXBException 
     */
    public static JAXBContext getJAXBContext() {
        ;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(
					Config.class, 
					LocalConfig.class, 
					RemoteConfig.class, 
					Student.class);
			return jaxbContext;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
        
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) {
        try {
			File file = new File(configFilePath);
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			return (Config) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
    }
}
