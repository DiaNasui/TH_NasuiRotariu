package TH_NasuiRotariuMV;

import TH_NasuiRotariuMV.domain.Nota;
import TH_NasuiRotariuMV.domain.Student;
import TH_NasuiRotariuMV.domain.Tema;
import TH_NasuiRotariuMV.repository.NotaXMLRepo;
import TH_NasuiRotariuMV.repository.StudentXMLRepo;
import TH_NasuiRotariuMV.repository.TemaXMLRepo;
import TH_NasuiRotariuMV.service.Service;
import TH_NasuiRotariuMV.validation.NotaValidator;
import TH_NasuiRotariuMV.validation.StudentValidator;
import TH_NasuiRotariuMV.validation.TemaValidator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TopDownIntegrationTest {

    private Service service;

    //private Service serviceStudent;
    private StudentXMLRepo repoXmlStudent;
    private final static String IDStudent = "111";

    //private Service serviceTema;
    private TemaXMLRepo repoXmlTema;
    private final static String IDTema = "222";

    //private Service serviceNota;
    private NotaXMLRepo repoXmlNota;
    private final static String IDNota = "333";
    private final static LocalDate d = LocalDate.parse("2019-01-04");

    @Before
    public void setUp()  {
        final StudentValidator validatorStudent = new StudentValidator();

        repoXmlStudent = new StudentXMLRepo("fisiere/Studenti.xml");
        //serviceStudent = new Service(repoXmlStudent, validatorStudent, null, null, null, null);

        final TemaValidator validatorTema = new TemaValidator();

        repoXmlTema = new TemaXMLRepo("fisiere/Teme.xml");
        //serviceTema = new Service(null, null, repoXmlTema, validatorTema, null, null);

        final NotaValidator validatorNota = new NotaValidator(repoXmlStudent,repoXmlTema);

        repoXmlNota = new NotaXMLRepo("fisiere/Note.xml");
        //serviceNota = new Service(null,  null, null,null,repoXmlNota,validatorNota);

        service = new Service(repoXmlStudent,validatorStudent,repoXmlTema,validatorTema,repoXmlNota,validatorNota);
    }

    @Test
    public void simpleAddTestStudent() {

        Student student = new Student(IDStudent, "Mirela", 934, "mirela@scs.ubbcluj.ro");
        service.addStudent(student);
        assert (student.getNume().equals( service.findStudent(IDStudent).getNume()));
    }

    @Test
    public void simpleAddTestAssignment() {
        Tema tema = new Tema(IDTema, "Adauga new assignment", 13, 12);
        service.addTema(tema);
        assert (tema.getID().equals(service.findTema(IDTema).getID()));
    }

    @Test
    public void simpleAddTestGrade() {
        Nota nota = new Nota(IDNota,IDStudent,IDTema,9,d);
        simpleAddTestStudent();
        simpleAddTestAssignment();
        service.addNota(nota,"good");
        assert (nota.getID().equals(service.findNota(IDNota).getID()));
    }

    @Test
    public void integrationAddAssignmentTest(){
        simpleAddTestStudent();
        simpleAddTestAssignment();
    }

    @Test
    public void integrationAddGradeTest(){
        simpleAddTestStudent();
        simpleAddTestAssignment();
        simpleAddTestGrade();
    }

//    @After
//   public void tearDown(){
//        service.deleteStudent(IDStudent);
//        service.deleteTema(IDTema);
//        service.deleteNota(IDNota);
//    }

}
