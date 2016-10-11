package com.cooksys.socket.assignment;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the
     * @param jaxb
     * @return
     * @throws JAXBException 
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
        File studentFile = new File(studentFilePath);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        return (Student) unmarshaller.unmarshal(studentFile);
    }
    
    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     */
    public static void main(String[] args) {
    	JAXBContext jaxb = getJAXBContext();
    	Config config = loadConfig("config/config.xml", jaxb);
    	
    	try {
    		
    		
			ServerSocket serverSocket = new ServerSocket(config.getRemote().getPort());
			Student student = loadStudent("config/student.xml", jaxb);
			
			Socket connection = serverSocket.accept();
			System.out.println("Connection recieved from " + connection.getRemoteSocketAddress());
			
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.marshal(student, connection.getOutputStream());
			
			connection.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
