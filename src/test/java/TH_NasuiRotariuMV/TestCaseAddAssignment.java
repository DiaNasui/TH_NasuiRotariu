package TH_NasuiRotariuMV;

import TH_NasuiRotariuMV.domain.Tema;
import TH_NasuiRotariuMV.repository.TemaXMLRepo;
import TH_NasuiRotariuMV.service.Service;
import TH_NasuiRotariuMV.validation.TemaValidator;
import org.junit.Before;
import org.junit.Test;



public class TestCaseAddAssignment {

    private Service service;
    private TemaXMLRepo repoXml;
    private final static String ID = "1234";

    @Before
    public void setup() {
        final TemaValidator validator = new TemaValidator();

        repoXml = new TemaXMLRepo("fisiere/Teme.xml");
        service = new Service(null, null, repoXml, validator, null, null);

    }

    @Test
    public void simpleAddTest() {
        Tema tema = new Tema(ID, "Adauga test case", 13, 3);
        service.addTema(tema);
        assert (tema.getID() == service.findTema(ID).getID());
    }

    @Test
    public void addNullId() {
        Tema tema = new Tema(null, "Adauga test case", 12, 3);
        service.addTema(tema);
        assert (tema.getID() == service.findTema(ID).getID());
    }

    @Test
    public void addEmptyDescription() {
        Tema tema = new Tema(ID, "", 12, 3);
        service.addTema(tema);
        assert (tema.getID() == service.findTema(ID).getID());
    }

    @Test
    public void addInvalidDeadline() {
        Tema tema = new Tema(ID, "Aduga 2 test case-uri", 0, 13);
        service.addTema(tema);
        assert (tema.getID() == service.findTema(ID).getID());
    }
    @Test
    public void addInvalidReceivedDate() {
        Tema tema = new Tema(ID, "Aduga 2 test case-uri", 5, 15);
        service.addTema(tema);
        assert (tema.getID() == service.findTema(ID).getID());
    }


    @Test
    public void addNullEntity() {
        Tema tema = null;
        service.addTema(tema);
        assert (tema.getID() == service.findTema(ID).getID());
    }


//    @After
//    public void tearDown() {
//        service.deleteTema(ID);
//    }

}