package com.cooksys.serialization.assignment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

	@XmlElement
	private Contact contact;

	public Student() {}

	public Student(Contact contact) {
		this.contact = contact;
	}
}
