package com.example.internpart2;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InternPart2Application {

    private static List<Model> winnerList  = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(InternPart2Application.class, args);
        function2();
    }

       public static void function2(){

        String html = "https://www.nlb.lk/results/govisetha";
        try {
           Document doc = Jsoup.connect(html).get();
           Elements tableElements = doc.select("table");

           Elements tableHeaderEles = tableElements.select("thead tr th");
           System.out.println("headers");
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
//                      System.out.println(rowItems.get(1).text());
                      Model m = new Model(rowItems.get(1).text());
                      winnerList.add(m);
                  }
              }
//              System.out.println();
           }

        } catch (IOException e) {
           e.printStackTrace();
        }

           if(!winnerList.isEmpty())
           {
               setUpDBSConnection();
           }
           else {
               System.out.println("list is empty cannot set up a database connection");
           }
     }

     public static void setUpDBSConnection()
     {
         Connection conn = null;
         Statement stmt = null;
         PreparedStatement preparedStatement = null;
         try {
             try {

                 Class.forName("com.example.internpart2.InternPart2Application");
             } catch (Exception e) {
                 System.out.println(e);
             }

             conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/a1", "sasi", "sasiruushan");
             System.out.println("Connection is created successfully:");
             stmt = (Statement) conn.createStatement();


             preparedStatement= conn.prepareStatement("INSERT INTO winners(winnerId) VALUES (?)");
             for(int i=0;i<winnerList.size();i++)
             {

                 String d = winnerList.get(i).winnerId;
                 preparedStatement.setObject(1, d);
                 preparedStatement.addBatch();

             }


             preparedStatement.executeBatch();
             System.out.println("Record is inserted in the table successfully..................");
         } catch (SQLException excep) {
             excep.printStackTrace();
         } catch (Exception excep) {
             excep.printStackTrace();
         } finally {
             try {
                 if (preparedStatement != null)
                     conn.close();
             } catch (SQLException se) {}
             try {
                 if (conn != null)
                     conn.close();
             } catch (SQLException se) {
                 se.printStackTrace();
             }
         }
         System.out.println("Please check it in the MySQL Table......... ……..");
     }

}
