package pti.sb_sigmod_mvc.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;

@Component
public class XmlReader {

    public Map<String, Integer> getAuthors() {

        Map<String, Integer> authorMap = new HashMap<>();

        try {
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(new File("C:\\sigmodRecords.xml"));
            Element rootElement = doc.getRootElement();
            Namespace ns = rootElement.getNamespace();

            List<Element> issueList = rootElement.getChildren("issue", ns);
            for (Element issue : issueList) {
                Element articles = issue.getChild("articles", ns);

                List<Element> articleList = articles.getChildren("article", ns);
                for (Element article : articleList) {
                    Element authors = article.getChild("authors", ns);

                    List<Element> authorList = authors.getChildren("author", ns);
                    boolean authorPos01Found = false;
                    for (Element author : authorList) {

                        if (author.getAttributeValue("position").equals("01")) {

                            authorPos01Found = true;
                            break;
                        }
                    }


                    if (authorPos01Found == true) {
                        for (Element author : authorList) {

                            String authorName = author.getValue();
                            if (authorMap.containsKey(authorName)) {

                                Integer counter = authorMap.get(authorName);
                                counter++;
                                authorMap.put(authorName, counter);
                            } else {

                                authorMap.put(authorName, 1);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }

        return authorMap;
    }

    public Map<String, Integer> getSearch(String word) {

        Map<String, Integer> authorMap = new HashMap<>();

        try {
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(new File("C:\\sigmodRecords.xml"));
            Element rootElement = doc.getRootElement();
            Namespace ns = rootElement.getNamespace();

            List<Element> issueList = rootElement.getChildren("issue", ns);

            for (Element issue : issueList) {

                Element articles = issue.getChild("articles", ns);

                List<Element> articleList = articles.getChildren("article", ns);

                for (Element article : articleList) {

                    Element authors = article.getChild("authors", ns);

                    List<Element> authorList = authors.getChildren("author", ns);

                    boolean authorPos01Found = false;

                    for (Element author : authorList) {

                        if (author.getAttributeValue("position").equals("01")) {

                            authorPos01Found = true;
                            break;
                        }
                    }

                    if (authorPos01Found == true) {

                        for (Element author : authorList) {

                            String authorName = author.getValue().trim();

                            if (authorName.contains(word)) {

                                if (authorMap.containsKey(authorName)) {

                                    Integer counter = authorMap.get(authorName);
                                    counter++;
                                    authorMap.put(authorName, counter);

                                } else {

                                    authorMap.put(authorName, 1);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return authorMap;
    }
}





























