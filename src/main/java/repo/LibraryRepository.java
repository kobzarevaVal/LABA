package repo;

import dom.DomService;
import dom.IDomService;
import model.Genre;
import model.Library;
import model.Serial;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryRepository implements ILibraryRepository {
    private Library library;
    private IDomService service;

    public LibraryRepository(IDomService service) {
        this.service = service;
        this.library = service.onLoad();
    }

    @Override
    public Library getLibrary() {

        return library;
    }

    @Override
    public ArrayList<Genre> getGenres() {
        return library.getGenres();
    }

    @Override
    public ArrayList<Serial> getSerialsByGenre(String genreName) {
        return library.getGenres().stream().filter(it->it.getName().equals(genreName)).findFirst().get().getSerials();
    }

    @Override
    public ArrayList<Serial> getAllSerials() {
        ArrayList<Serial> serials=new ArrayList<>();
        library.getGenres().forEach(it->serials.addAll(it.getSerials()));
        return serials;
    }

    @Override
    public LibraryRepository save(String newFilePath) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        service.onSaveDoc(newFilePath,library);
        return new LibraryRepository(new DomService(newFilePath));
    }
}
