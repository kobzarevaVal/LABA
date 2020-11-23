package repo;

import model.Genre;
import model.Library;
import model.Serial;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public interface ILibraryRepository {
    Library getLibrary();
    ArrayList<Genre> getGenres();
    ArrayList<Serial> getSerialsByGenre(String genreName);
    ArrayList<Serial> getAllSerials();
    LibraryRepository save(String newFilePath) throws ParserConfigurationException, SAXException, IOException, TransformerException;
}
