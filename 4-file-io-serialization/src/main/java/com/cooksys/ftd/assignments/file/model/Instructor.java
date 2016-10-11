package com.cooksys.serialization.assignment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Instructor {
	
	@XmlElement
    private Contact contact;

    public Instructor() {}
    
    public Instructor(Contact contact) {
    	this.contact = contact;
    }
}
