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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ParseData {

    public static List<Data> listData() throws ParserConfigurationException, IOException, SAXException {
        List<Data> dataList = new ArrayList<>();
        Data data = null;
        File inputFile = new File(System.getProperty("user.dir") + "\\data_java-2.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        NodeList nodeList = doc.getElementsByTagName("bar");

        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            data = new Data();
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                data.setId(element.getAttribute("id"));
                data.setStartdate(convertStringToDate(element.getAttribute("startdate")));
                data.setEnddate(convertStringToDate(element.getAttribute("enddate")));
            }
            dataList.add(data);
        }

        List<Data> result = dataList.stream().sorted().collect(Collectors.toList());

        return result;
    }

    public static List<List<LocalDateTime>> parseDataToList(List<Data> dataList){
        List<List<LocalDateTime>> list = new ArrayList<>();
        for (Data i : dataList) {
            List<LocalDateTime> bar = new ArrayList<>();
            bar.add(i.getStartdate());
            bar.add(i.getEnddate());
            list.add(bar);
        }

        return list;
    }

    public static List<List<LocalDateTime>> getEmptyIntervals(List<List<LocalDateTime>> listData){
        List<List<LocalDateTime>> result = new ArrayList<>();
        for(int i = 0;i < listData.size(); i++){
            if(i<listData.size()-1 && listData.get(i+1).get(0).compareTo(listData.get(i).get(1)) > 0){
                List<LocalDateTime> re = new ArrayList<>();
                re.add(listData.get(i).get(1));
                re.add(listData.get(i+1).get(0));
                result.add(re);
            }
        }
        return result;
    }

    public static LocalDateTime convertStringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);

        return localDateTime;
    }
}
