package com.example.lottery;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CallApi {

    public static void main(String[] args) {
        SpringApplication.run(CallApi.class, args);
        function2();
    }

       public static void function2(){

        String html = "https://www.nlb.lk/results/govisetha";
        try
        {
           Document doc = Jsoup.connect(html).get();
           Elements tableElements = doc.select("table");

           Elements tableHeaderEles = tableElements.select("thead tr th");
//           System.out.println("headers");
           for (int i = 0; i < tableHeaderEles.size(); i++) {
              System.out.println(tableHeaderEles.get(i).text());
           }
           System.out.println();

           Elements tableRowElements = tableElements.select(":not(thead) tr");

           for (int i = 0; i < tableRowElements.size(); i++) {
              Element row = tableRowElements.get(i);
//              System.out.println("row");
              Elements rowItems = row.select("td");
              for (int j = 0; j < rowItems.size(); j++) {
                  if(j==1) {
                      System.out.println(rowItems.get(1).text());
                  }
              }
              System.out.println();
           }

        } catch (IOException e) {
           e.printStackTrace();
        }
     }

}
