package dom;

import model.Library;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public interface IDomService {
    void onSaveDoc(String newFilePath, Library library) throws TransformerException, ParserConfigurationException;
    Library onLoad();
}
