package dom;

import model.Genre;
import model.Library;
import model.Serial;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class DomService implements IDomService {
    private Document document;

    public DomService(String filePath) throws IOException, SAXException, ParserConfigurationException {
        this.document = getNewDocument(filePath);
    }

    // подключение к существующему файлу
    private Document getNewDocument(String filePath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    // создаёт новый документ
    private Document getNewDocument(Library library) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element libraryElement = document.createElement("library");
        document.appendChild(libraryElement);
        Attr libraryAttr = document.createAttribute("title");
        libraryAttr.setValue(library.getTitle());
        libraryElement.setAttributeNode(libraryAttr);

        Element genresElement = document.createElement("genres");
        libraryElement.appendChild(genresElement);

        library.getGenres().forEach(genre -> {
            Element genreElement = document.createElement("genre");
            genresElement.appendChild(genreElement);
            genreElement.setAttribute("name",genre.getName());

            Element genreSerials = document.createElement("serials");
            genreElement.appendChild(genreSerials);

            genre.getSerials().forEach(serial -> {
                Element serialElement = document.createElement("serial");
                genreSerials.appendChild(serialElement);
                serialElement.setAttribute("name", serial.getName());
                serialElement.setAttribute("season_count", String.valueOf(serial.getSeasonCount()));
                serialElement.setAttribute("rating", String.valueOf(serial.getRating()));
            });
        });
        return document;
    }

    // сохроняет данные библиотеки в файл
    @Override
    public void onSaveDoc(String newFilePath, Library library) throws TransformerException, ParserConfigurationException {
        Document document = getNewDocument(library);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(newFilePath));
        transformer.transform(source,result);
    }

    // при инициализации класса запускает загрузку данных из файла
    @Override
    public Library onLoad() {
        Node libraryNode = document.getDocumentElement();
        return getLibrary(libraryNode.getAttributes());
    }

    private Library getLibrary(NamedNodeMap attributes) {
        return new Library( attributes.getNamedItem("title").getNodeValue(),getGenres());
    }

    private ArrayList<Genre> getGenres() {
        ArrayList<Genre> genres = new ArrayList<>();
        String tag = "genre";
        NodeList genreNodes = document.getDocumentElement().getElementsByTagName(tag);
        for (int i = 0; i < genreNodes.getLength(); i++) {
            Node node=genreNodes.item(i);
            NamedNodeMap attr = node.getAttributes();
            genres.add(getGenre(attr));
        }
     return genres;
    }

    private Genre getGenre(NamedNodeMap attr) {
        String genreName = attr.getNamedItem("name").getNodeValue();
        return new Genre(genreName, getSerialsByGenre(genreName));
    }

    private ArrayList<Serial> getSerialsByGenre(String genreName) {
        ArrayList<Serial> serials = new ArrayList<>();
        String tag = "serial";
        NodeList serialNodes = document.getDocumentElement().getElementsByTagName(tag);
        for (int i = 0; i < serialNodes.getLength(); i++) {
            Element serial = (Element) serialNodes.item(i);
            if(serial.getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals(genreName)){
                serials.add(getSerial(serial.getAttributes()));
            }
        }
        return serials;
    }

    private Serial getSerial(NamedNodeMap attributes) {
        return new Serial(attributes.getNamedItem("name").getNodeValue(),
                Integer.parseInt(attributes.getNamedItem("season_count").getNodeValue()),
                Float.parseFloat(attributes.getNamedItem("rating").getNodeValue()));
    }
}
