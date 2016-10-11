package com.cooksys.socket.assignment;

import java.io.IOException;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

public class Client {

    public static void main(String[] args) {
    	JAXBContext jaxb = Utils.getJAXBContext();
        Config config = Utils.loadConfig("config/config.xml", jaxb);
        try {
        	Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			Socket socket = new Socket(config.getRemote().getHost(), config.getRemote().getPort());
			
			Student student = (Student) unmarshaller.unmarshal(socket.getInputStream());
			System.out.print(student);
			
			socket.close();
			
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
