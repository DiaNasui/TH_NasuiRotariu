package TH_NasuiRotariuMV.app;


import TH_NasuiRotariuMV.repository.NotaXMLRepo;
import TH_NasuiRotariuMV.repository.StudentXMLRepo;
import TH_NasuiRotariuMV.repository.TemaXMLRepo;
import TH_NasuiRotariuMV.service.Service;
import TH_NasuiRotariuMV.validation.NotaValidator;
import TH_NasuiRotariuMV.validation.StudentValidator;
import TH_NasuiRotariuMV.validation.TemaValidator;
import TH_NasuiRotariuMV.view.UI;




public class MainApplication {

    public static void main(String[] args) {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

       //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        UI ui = new UI(service);
        ui.run();
    }

}
