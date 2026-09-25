package pti.sb_sigmod_mvc.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Component;


import java.io.FileWriter;
import java.util.Map;

@Component
public class XmlWriter {

public void writeAuthors(Map<String, Integer> authorMap) {

    try {

        FileWriter writer = new FileWriter("C:\\Users\\HorvathK\\Desktop\\output.xml");

        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

        Document doc = new Document();

        Element rootElement = new Element("authors");

        for (Map.Entry<String, Integer> author : authorMap.entrySet()) {

            Element authorElement = new Element("author");

            authorElement.setText(author.getKey());

            authorElement.setAttribute(
                    "counter",
                    author.getValue().toString()
            );

            rootElement.addContent(authorElement);
        }

        doc.setRootElement(rootElement);

        outputter.output(doc, writer);

        writer.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    }
