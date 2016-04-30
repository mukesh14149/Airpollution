package com.example.mukesh.airpollution;

/**
 * Created by sujeet on 29/3/16.
 */

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 * Created by mukesh on 6/3/16.
 */
public class Web_crawling {
    Web_crawling(Context context)
    {
        mContext = context;
    }
    static public String LOG_TAG = "Web_crawling";
    static Context mContext;

    public static String web_crawl(String Area) throws IOException {
        String qa="";
        ArrayList<String> data = new ArrayList<String>();
        try {

            String url = "http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=" + Area + "&StateId=6&CityId=85";
            System.out.println("dfdzgfdg");
            Log.e(LOG_TAG,Area);
            //HashMap<String, String> map = new HashMap<String, String>();
           //FileWriter fin = new FileWriter(Area + ".csv", true);
           //BufferedWriter bin = new BufferedWriter(fin);
           // StringWriter bin = new StringWriter();




            Document doc = Jsoup.connect("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=" + Area + "&StateId=6&CityId=85").timeout(10 * 10000).get();
            ArrayList<String> downServers = new ArrayList<>();

          //  Log.e(LOG_TAG, doc.toString());


            // creates the file

            // creates a FileWriter Object
            // FileWriter fin = null;
            //BufferedWriter bin = null;
                //fin = new FileWriter("/home/sujeet/evsmp/Airpollution/"+Area + ".csv", true);
                File file = new File(mContext.getFilesDir(),Area);
                file.createNewFile();
                // creates a FileWriter Object
                FileWriter writer = new FileWriter(file);
            //    Log.e(LOG_TAG, "mml1");
          //  bin = new BufferedWriter(fin);
            Element table = doc.getElementById("Td1");//select the first table.
       //    Log.e(LOG_TAG,table.toString());
            //Log.e(LOG_TAG, "mml");
         //   System.out.println(doc);
            Iterator<Element> ite = table.select("td").iterator();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
          //  bin.write("\n");
                writer.write("\n");
int c=0;

                int i = 0;

            while ((ite.hasNext())&&(c<100 )){
                i++;
                if (i == 8) {
                   // bin.write("\n");
                    writer.write("\n");

                    i = 1;
                }

                String qw=ite.next().text()+"," ;
                qa=qa+qw;
               writer.write(qw);
                data.add(qw);
               // Log.e(LOG_TAG,qa);

                c++;
             //  bin.write(ite.next().text() + ",");
               /// fin.write(bin.toString());
              //  Log.e(LOG_TAG, "kn,kjnkjkhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
           //    Log.e(LOG_TAG,ite.next().text());
            }

            //if(bin != null|| fin !=null) bin.close();
                writer.flush();
                writer.close();

           // bin.close();
           // fin.close();

        } catch (IOException  e) {
            e.printStackTrace();
        }
        String asq="";
     /*   ArrayList<String> fina =new ArrayList<String>();
        String a = "/";
      String mml="";
        for (int i = 0; i < data.size(); i++) {
           int c=0;
            final int len = data.get(i).length();
            for (int j = 0; j < len; j++) {
                if (data.get(i).charAt(j) <= '/') {
                    c++;
                }
                if(c==2)
                {
                fina.add(data.get(i-1));if(i<data.size())
                    asq=asq+data.get(i-1)+","+data.get(i+2);
                }
            }


        }*/



        String str=qa;





        String r = "";
try {

    int k = 0;
    int count = 0;
    String[] t = str.split(",");
    int i = 0, a = 1, b = 3, c = 5, p = 0;
    for (i = 0; i < t.length; i++) {
        if (count < 9) {
            if ((i % 7 == 0) || (i == b) || (i == c)) {
                //System.out.println(i +"  "+ b+ "  " +t[i]);
                if (i == c)
                    k = 1;
                if (i == b) {
                    b = b + 7;
                    p = 1;
                }
                if (i == c)
                    c = c + 7;
                if (k == 1) {
                    if (t[i].length() > 15) {
                        String[] l = t[i].split(":");
                        r = r + l[1] + ",";
                        //	System.out.println(i +"  "+ b+ "  " +t[i]);

                    } else
                        r = r + " " + ",";
                    k = 0;
                    count++;
                } else if (p == 1) {
                    r = r + " " + t[i] + " µg/m3,";
                    p = 0;
                } else
                    r = r + t[i] + ",";
            }
        }
    }
}
catch (Exception e)
{

}
            //System.out.println(r);
            return r;








    }

   /* public static void main(String[] args) throws Exception {
        ArrayList<String> area = new ArrayList<String>();
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
