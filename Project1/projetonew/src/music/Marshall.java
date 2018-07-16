package music;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class Marshall {

	public static JAXB marshall(Catalog result) {
		// TODO Auto-generated constructor stub

		try {

			JAXBContext jc = JAXBContext.newInstance(Catalog.class);
			Marshaller m = jc.createMarshaller();

			File schema_file = new File("Projeto1_xsd.xsd");
			SchemaFactory schemaf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaf.newSchema(schema_file);
			File file = new File("XML_2.xml");

			m.setSchema(schema);
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(result, file);

		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JAXB marshall_2(Catalognew result) {
		// TODO Auto-generated constructor stub

		try {

			JAXBContext jc = JAXBContext.newInstance(Catalognew.class);
			Marshaller m = jc.createMarshaller();

			File schema_file = new File("Projeto1_xsd2.xsd");
			SchemaFactory schemaf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaf.newSchema(schema_file);
			File file = new File("XML_Result.xml");

			m.setSchema(schema);
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(result, file);

			TransformerFactory transf = TransformerFactory.newInstance();
			StreamSource xsl = new StreamSource("Projeto1_xsl.xsl");
			Transformer transformer = transf.newTransformer(xsl);

			// Source
			JAXBSource jaxbsource = new JAXBSource(jc, result);

			// Resultado
			File html_file = new File("Result_Vinyl.html");
			StreamResult result_htlm = new StreamResult(html_file);

			// Transformação
			transformer.transform(jaxbsource, result_htlm);

		} catch (JAXBException | SAXException | TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

}
