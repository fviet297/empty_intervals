import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        List<Data> dataList = ParseData.listData();
        List<List<LocalDateTime>> list = ParseData.parseDataToList(dataList);
        List<List<LocalDateTime>> result = ParseData.getEmptyIntervals(list);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("Empty intervals: ");
        result.forEach(i -> {
            for (LocalDateTime j : i) {
                String e = j.format(dateTimeFormatter);
                System.out.print(e + "    ");
            }
            System.out.println();
        });
    }

}