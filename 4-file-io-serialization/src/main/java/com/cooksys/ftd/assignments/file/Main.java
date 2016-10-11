package com.cooksys.serialization.assignment;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.serialization.assignment.model.Contact;
import com.cooksys.serialization.assignment.model.Instructor;
import com.cooksys.serialization.assignment.model.Session;
import com.cooksys.serialization.assignment.model.Student;

public class Main {

	/**
	 * Creates a {@link Student} object using the given studentContactFile. The
	 * studentContactFile should be an XML file containing the marshaled form of
	 * a {@link Contact} object.
	 *
	 * @param studentContactFile
	 *            the XML file to use
	 * @param jaxb
	 *            the JAXB context to use
	 * @return a {@link Student} object built using the {@link Contact} data in
	 *         the given file
	 */
	public static Student readStudent(File studentContactFile, JAXBContext jaxb) {

		try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			Student student = new Student((Contact) jaxbUnmarshaller.unmarshal(studentContactFile));
			return student;

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Creates a list of {@link Student} objects using the given directory of
	 * student contact files.
	 *
	 * @param studentDirectory
	 *            the directory of student contact files to use
	 * @param jaxb
	 *            the JAXB context to use
	 * @return a list of {@link Student} objects built using the contact files
	 *         in the given directory
	 */
	public static List<Student> readStudents(File studentDirectory, JAXBContext jaxb) {
		List<Student> list = new LinkedList<>();
		try {
			if (studentDirectory.isDirectory()) {
				Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
				
				for (File contactFile : studentDirectory.listFiles()) {
					Contact contact = (Contact) jaxbUnmarshaller.unmarshal(contactFile);
					list.add(new Student(contact));
				}
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Creates an {@link Instructor} object using the given
	 * instructorContactFile. The instructorContactFile should be an XML file
	 * containing the marshaled form of a {@link Contact} object.
	 *
	 * @param instructorContactFile
	 *            the XML file to use
	 * @param jaxb
	 *            the JAXB context to use
	 * @return an {@link Instructor} object built using the {@link Contact} data
	 *         in the given file
	 */
	public static Instructor readInstructor(File instructorContactFile, JAXBContext jaxb) {

		Instructor instructor;
		try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			instructor = new Instructor((Contact) jaxbUnmarshaller.unmarshal(instructorContactFile));

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
		return instructor;
	}

	/**
	 * Creates a {@link Session} object using the given rootDirectory. A
	 * {@link Session} root directory is named after the location of the
	 * {@link Session}, and contains a directory named after the start date of
	 * the {@link Session}. The start date directory in turn contains a
	 * directory named `students`, which contains contact files for the students
	 * in the session. The start date directory also contains an instructor
	 * contact file named `instructor.xml`.
	 *
	 * @param rootDirectory
	 *            the root directory of the session data, named after the
	 *            session location
	 * @param jaxb
	 *            the JAXB context to use
	 * @return a {@link Session} object built from the data in the given
	 *         directory
	 */
	public static Session readSession(File rootDirectory, JAXBContext jaxb) {

		try {

			File directory = rootDirectory;
			String location = rootDirectory.getName();

			directory = FileUtil.enterDirectory(directory)[0];
			String startDate = directory.getName();

			File[] subDirectory = FileUtil.enterDirectory(directory);

			List<Student> students = null;
			Instructor instructor = null;
			for (File file : subDirectory) {
				if (file.isDirectory())
					students = readStudents(file, JAXBContext.newInstance(Contact.class));
				else
					instructor = readInstructor(file, JAXBContext.newInstance(Instructor.class));
			}

			return new Session(location, startDate, instructor, students);

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns all of the contents of a given directory
	 * 
	 * @param directory
	 * @return File array of contents of directory. If file argument is not a
	 *         directory, returns an empty file array.
	 */

	/**
	 * Writes a given session to a given XML file
	 *
	 * @param session
	 *            the session to write to the given file
	 * @param sessionFile
	 *            the file to which the session is to be written
	 * @param jaxb
	 *            the JAXB context to use
	 */
	public static void writeSession(Session session, File sessionFile, JAXBContext jaxb) {

		try {
			Marshaller jaxbMarshaller = jaxb.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(session, sessionFile);
			jaxbMarshaller.marshal(session, System.out);
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Main Method Execution Steps: 1. Configure JAXB for the classes in the
	 * com.cooksys.serialization.assignment.model package 2. Read a session
	 * object from the <project-root>/input/memphis/ directory using the methods
	 * defined above 3. Write the session object to the
	 * <project-root>/output/session.xml file.
	 *
	 * JAXB Annotations and Configuration: You will have to add JAXB annotations
	 * to the classes in the com.cooksys.serialization.assignment.model package
	 *
	 * Check the XML files in the <project-root>/input/ directory to determine
	 * how to configure the {@link Contact} JAXB annotations
	 *
	 * The {@link Session} object should marshal to look like the following:
	 * <session location="..." start-date="..."> <instructor> <contact>...
	 * </contact> </instructor> <students> ...
	 * <student> <contact>...</contact> </student> ... </students> </session>
	 */
	public static void main(String[] args) {

		File inputFile = new File("input/memphis");
		File outputFile = new File("output/session.xml");

		try {
			Session session = readSession(inputFile, JAXBContext.newInstance(Session.class));
			writeSession(session, outputFile, JAXBContext.newInstance(Session.class));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
