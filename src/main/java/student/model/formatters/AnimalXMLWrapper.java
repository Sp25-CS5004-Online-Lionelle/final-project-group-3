package student.model.formatters;

import java.util.Collection;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import student.model.IModel.AnimalRecord;

/**
 * This wrapper helps when using Jackson to serialize a list of domain records to xml. Without this,
 * it tries to use <ArrayList> and <item> tags instead of <animalList> and <animal> tags.
 * 
 * Suggested use (note you need try/catch with this)
 * 
 * <pre>
 * XmlMapper mapper = new XmlMapper();
 * mapper.enable(SerializationFeature.INDENT_OUTPUT);
 * AnimalXmlWrapper wrapper = new AnimalXmlWrapper(records);
 * mapper.writeValue(out, wrapper);
 * </pre>
 */
@JacksonXmlRootElement(localName = "animalList")

public final class AnimalXMLWrapper {
    
}
