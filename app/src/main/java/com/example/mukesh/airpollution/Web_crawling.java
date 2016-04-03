package com.example.mukesh.airpollution;

/**
 * Created by sujeet on 29/3/16.
 */
        import android.util.Log;
        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Iterator;
/**
 * Created by mukesh on 6/3/16.
 */
public class Web_crawling {
    static public String LOG_TAG = "Web_crawling";
    public String web_crawl(String Area) throws IOException {
        try {
            Log.e(LOG_TAG, Area + ".csv");
            String url = "http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=" + Area + "&StateId=6&CityId=85";
            System.out.println("dfdzgfdg");
            HashMap<String, String> map = new HashMap<String, String>();
            FileWriter fin = new FileWriter(Area + ".csv", true);
            BufferedWriter bin = new BufferedWriter(fin);
            Log.e(LOG_TAG, Area + ".csv");
            Document doc = Jsoup.connect(url).get();
            ArrayList<String> downServers = new ArrayList<>();
            Element table = doc.getElementById("Td1");//select the first table.
            Iterator<Element> ite = table.select("td").iterator();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            bin.newLine();
            int i = 0;
            while (ite.hasNext()) {
                i++;
                if (i == 8) {
                    bin.newLine();
                    i = 1;
                }
                bin.write(ite.next().text() + ",");
            }
            bin.close();
        } catch (Exception e) {
            System.out.println("EEEEError");
        }
        String a="ads";
        return a;
    }
/*public static void main(String[] args) throws Exception
{
ArrayList<String> area=new ArrayList<String>();
area.add("D.C.E.");
area.add("Punjabi5Bagh");
area.add("Shadipur");
area.add("Dwarka");
area.add("Mandir5Marg");
area.add("ITO");
area.add("Anand5Vihar");
area.add("R5K5Puram");
area.add("Ihbas");
area.add("Civil5Lines");
area.add("IGI5Airport");
for (String temp : area) {
web_crawl(temp);
}
}
*/
}
