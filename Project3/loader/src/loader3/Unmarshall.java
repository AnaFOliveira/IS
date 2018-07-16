package loader3;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import classes_xml.Catalog;

public class Unmarshall {
	
	public static Catalog unmarshall(String directory_xml) {
		try {
			
			JAXBContext jcontext=JAXBContext.newInstance(Catalog.class);
			Unmarshaller unmarshal = jcontext.createUnmarshaller();
			
			File xml_file=new File(directory_xml+".xml");
			File schema_file=new File("Projeto1_xsd.xsd");
			
			SchemaFactory schemaf=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema=schemaf.newSchema(schema_file);
			unmarshal.setSchema(schema);
			
			Catalog catalog=(Catalog)unmarshal.unmarshal(xml_file);;
			
			return catalog;
			
		} catch (JAXBException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
