package pl.playerony.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.playerony.BlogXML.exception.XmlException;
import pl.playerony.BlogXML.exportXml.ExportToXml;
import pl.playerony.BlogXML.importXml.ImportToDb;
import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.service.XmlService;

@Service
public class XmlServiceImpl implements XmlService {
	private ExportToXml exportToXml;
	private ImportToDb importToDb;
	
	@Autowired
	public XmlServiceImpl(ExportToXml exportToXml, ImportToDb importToDb) {
		this.exportToXml = exportToXml;
		this.importToDb = importToDb;
	}
	
	@Override
	public void exportXml(String filePath) throws DatabaseException, XmlException, InputException {
		exportToXml.toXmlFile(filePath);
	}

	@Override
	public void importXml(String filePath) throws DatabaseException, XmlException, InputException {
		importToDb.fromXmlFileToDb(filePath);
	}
	
}
