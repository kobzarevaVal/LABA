package repo;

import dom.DomService;
import manager.SerialManager;
import model.Library;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Queue;

import static org.junit.Assert.*;

public class LibraryRepositoryTest {
    private LibraryRepository libraryRepository;
    private String fFilePath = "src\\test\\resources\\library.xml";
    private String sFilePath = "src\\test\\resources\\library-2.xml";

    @Before
    public void setUp() throws Exception {
        libraryRepository = new LibraryRepository(new DomService(fFilePath));
    }

    @Test
    public void changeArgs() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Library library = libraryRepository.getLibrary();
        SerialManager manager = new SerialManager();
        manager.addSeasons(library.getGenres().get(0).getSerials().get(0), 3);
        manager.changeRating(library.getGenres().get(1).getSerials().get(0), 5.0f);
        libraryRepository.save(sFilePath);
    }


}