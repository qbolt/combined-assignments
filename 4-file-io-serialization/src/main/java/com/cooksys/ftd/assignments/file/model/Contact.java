package com.cooksys.serialization.assignment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {
	
	@XmlAttribute(name="first-name")
    private String firstName;
	
	@XmlAttribute(name="last-name")
    private String lastName;
	
	@XmlElement
    private String email;
	
	@XmlElement
    private String phoneNumber;

}
