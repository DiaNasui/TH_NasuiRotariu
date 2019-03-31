package TH_NasuiRotariuMV;

import TH_NasuiRotariuMV.domain.Student;
import TH_NasuiRotariuMV.repository.StudentXMLRepo;
import TH_NasuiRotariuMV.service.Service;
import TH_NasuiRotariuMV.validation.StudentValidator;
import org.junit.Before;
import org.junit.Test;


public class AppTest {

    private Service service;
    private StudentXMLRepo repoXml;
    private final static String ID = "123";

    @Before
    public void setup() {
        final StudentValidator validator = new StudentValidator();

        repoXml = new StudentXMLRepo("fisiere/Studenti.xml");
        service = new Service(repoXml, validator, null, null, null, null);

    }

    @Test
    public void simpleAddTest() {

        Student student = new Student(ID, "Diana", 934, "diana@scs.ubbcluj.ro");
        service.addStudent(student);
        assert (student.getNume().equals( service.findStudent(ID).getNume()));
    }

    @Test
    public void addEmptyId() {

        Student student = new Student("", "Danut", 934, "diana@scs.ubbcluj.ro");
        service.addStudent(student);
        assert (student.getNume().equals(service.findStudent(ID).getNume()));
    }

    @Test
    public void addNullId() {

        Student student = new Student(null, "Danut", 934, "danut@scs.ubbcluj.ro");
        service.addStudent(student);
        assert (student.getNume().equals(service.findStudent(ID).getNume()));
    }

    @Test
    public void addEmptyName() {

        Student student = new Student(ID, "", 934, "danut@scs.ubbcluj.ro");
        service.addStudent(student);
        assert (student.getNume().equals( service.findStudent(ID).getNume()));
    }

    @Test
    public void addNullName() {

        Student student = new Student(ID, null, 931, "mirela@gmail.com");
        service.addStudent(student);
        assert (student.getNume().equals( service.findStudent(ID).getNume()));
    }

    @Test
    public void addNegativeGroup() {

        Student student = new Student(ID, "Mirela", -3, "danut@scs.ubbcluj.ro");
        service.addStudent(student);
        assert (student.getNume().equals( service.findStudent(ID).getNume()));
    }

    @Test
    public void addNullEmail() {

        Student student = new Student(ID, "Diana", 931, null);
        service.addStudent(student);
        assert (student.getNume().equals( service.findStudent(ID).getNume()));
    }

    @Test
    public void addEmptyEmail() {

        Student student = new Student(ID, "Diana", 931, "");
        service.addStudent(student);
        assert (student.getNume().equals( service.findStudent(ID).getNume()));
    }

//    @Test
//    public void addSameStudentTwiceTest() {
//        Student student = new Student(ID, "Mirela", 934, "mirela@scs.ubbcluj.ro");
//        Student student1 = service.addStudent(student);
//        assert (student.getNume().equals( service.findStudent(ID).getNume()));
//
//        Student student2 = new Student(ID, "Mirela", 934, "mirela@scs.ubbcluj.ro");
//        service.addStudent(student2);
//        assert (student2.getNume().equals( service.findStudent(ID).getNume()));
//    }

//    @After
//    public void tearDown(){
//        service.deleteNota(ID);
//    }

}