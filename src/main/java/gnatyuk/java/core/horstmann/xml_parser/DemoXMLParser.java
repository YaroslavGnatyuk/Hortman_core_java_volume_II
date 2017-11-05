package gnatyuk.java.core.horstmann.xml_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoXMLParser {
    private static final String XML_FILE = "test_xml.xml";
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        logger.setLevel(Level.ALL);
        List<Book> books = new ArrayList<>();
        DemoXMLParser xmlParser = new DemoXMLParser();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlParser.getResourceFile(XML_FILE));

            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Book book = xmlParser.getBook(node);

                if (book.getId() != null) {
                    books.add(book);
                }
            }
            books.forEach(System.out::println);
        } catch (ParserConfigurationException | SAXException | NullPointerException | IOException e) {
            logger.warning(e.getCause().getMessage());
            System.out.println();
        }
    }

    private File getResourceFile(String fileName) throws NullPointerException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    private Book getBook(Node nodeBook){
        Book book = new Book();
        if(nodeBook instanceof Element) {
            book.setId(nodeBook.getAttributes().getNamedItem("id").getNodeValue());
            book.setAuthor(nodeBook.getChildNodes().item(0).getNodeName());

            NodeList bookFields = nodeBook.getChildNodes();
            for (int i = 0; i < bookFields.getLength(); i++) {
                Node bookField = bookFields.item(i);
                if(bookField instanceof Element){
                    switch (((Element) bookField).getTagName()){
                        case "author":{
                            String author = bookField.getTextContent();
                            book.setAuthor(author);
                            break;
                        }
                        case "title":{
                            String title = bookField.getTextContent();
                            book.setTitle(title);
                            break;
                        }
                        case "genre":{
                            String genre = bookField.getTextContent();
                            book.setGenre(genre);
                            break;
                        }
                        case "price":{
                            float price = Float.parseFloat(bookField.getTextContent());
                            book.setPrice(price);
                            break;
                        }
                        case "publish_date":{
                            String date = bookField.getTextContent();
                            book.setPublishDate(date);
                            break;
                        }
                        case "description":{
                            String description = bookField.getTextContent();
                            book.setDescription(description);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        }
        return book;
    }
}
