package pl.playerony.service;

import pl.playerony.BlogXML.exception.XmlException;
import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;

public interface XmlService {
	void exportXml(String filePath) throws DatabaseException, XmlException, InputException;
	
	void importXml(String filePath) throws DatabaseException, XmlException, InputException;
}
