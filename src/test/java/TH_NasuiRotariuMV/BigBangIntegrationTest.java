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

public class BigBangIntegrationTest {

    private Service serviceStudent;
    private StudentXMLRepo repoXmlStudent;
    private final static String IDStudent = "123";

    private Service serviceTema;
    private TemaXMLRepo repoXmlTema;
    private final static String IDTema = "1234";

    private Service serviceNota;
    private NotaXMLRepo repoXmlNota;
    private final static String IDNota = "111";
    private final static LocalDate d = LocalDate.parse("2018-11-16");

    @Before
    public void setUp()  {
        final StudentValidator validatorStudent = new StudentValidator();

        repoXmlStudent = new StudentXMLRepo("fisiere/Studenti.xml");
        serviceStudent = new Service(repoXmlStudent, validatorStudent, null, null, null, null);

        final TemaValidator validatorTema = new TemaValidator();

        repoXmlTema = new TemaXMLRepo("fisiere/Teme.xml");
        serviceTema = new Service(null, null, repoXmlTema, validatorTema, null, null);

        final NotaValidator validatorNota = new NotaValidator(repoXmlStudent,repoXmlTema);

        repoXmlNota = new NotaXMLRepo("fisiere/Note.xml");
        serviceNota = new Service(null,  null, null,null,repoXmlNota,validatorNota);
    }

    @Test
    public void simpleAddTestStudent() {

        Student student = new Student(IDStudent, "Diana", 934, "diana@scs.ubbcluj.ro");
        serviceStudent.addStudent(student);
        assert (student.getNume().equals( serviceStudent.findStudent(IDStudent).getNume()));
    }

    @Test
    public void simpleAddTestTema() {
        Tema tema = new Tema(IDTema, "Adauga test case", 13, 3);
        serviceTema.addTema(tema);
        assert (tema.getID() == serviceTema.findTema(IDTema).getID());
    }

    @Test
    public void simpleAddTestNota() {
        Nota nota = new Nota(IDNota,IDStudent,IDTema,10,d);

        serviceNota.addNota(nota,"good");
        assert (nota.getID() == serviceNota.findNota(IDNota).getID());
    }

    @Test
    public void checkTest(){
        simpleAddTestNota();
        simpleAddTestStudent();
        simpleAddTestTema();
    }

//    @After
//    public void tearDown() {
//    }
}
