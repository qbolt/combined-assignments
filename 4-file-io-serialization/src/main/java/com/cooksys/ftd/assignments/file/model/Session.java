package com.cooksys.serialization.assignment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Session {
	
	@XmlAttribute
    private String location;
	
	@XmlAttribute
    private String startDate;
    
    @XmlElement
    private Instructor instructor;
    
    @XmlElementWrapper
    @XmlElement(name="student")
    private List<Student> students;
   
    public Session() {}
    
    public Session(String location, String startDate, Instructor instructor, List<Student> students) {
		super();
		this.location = location;
		this.startDate = startDate;
		this.instructor = instructor;
		this.students = students;
	}
}
