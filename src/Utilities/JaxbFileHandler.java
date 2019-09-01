package Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbFileHandler {

    public JaxbFileHandler() {
    }

    public static void importXML(ArrayList<Student> students,
            File selectedFile) throws IOException, JAXBException {
        JAXBContext context;
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(selectedFile));
        context = JAXBContext.newInstance(Students.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new Students(students), writer);
        writer.close();
    }

    public static ArrayList<Student> exportXML(File importFile)
            throws JAXBException {
        Students students = new Students();
        JAXBContext context = JAXBContext.newInstance(Students.class);
        Unmarshaller um = context.createUnmarshaller();
        students = (Students) um.unmarshal(importFile);
        return students.getStudents();
    }
}