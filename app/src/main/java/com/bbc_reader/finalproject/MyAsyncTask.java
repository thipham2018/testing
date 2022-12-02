package com.bbc_reader.finalproject;

import android.os.AsyncTask;

import com.bbc_reader.finalproject.model.Feed;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MyAsyncTask extends AsyncTask<String, String, List<Feed>> {

    @Override
    protected List<Feed> doInBackground(String... strings) {
        List<Feed> result = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringBuilder xmlStringBuilder = new StringBuilder();
            URL  urls = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
            conn.setReadTimeout(150000); //milliseconds
            conn.setConnectTimeout(15000); // milliseconds
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    xmlStringBuilder.append(line);
                }

                ByteArrayInputStream input = new ByteArrayInputStream(
                        xmlStringBuilder.toString().getBytes("UTF-8"));
                Document document = builder.parse(input);
                NodeList nList = document.getElementsByTagName("item");
                for(int i = 0; i < nList.getLength(); i++){
                    Node node = nList.item(i);
                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        Element el = (Element) node;
                        result.add(new Feed(el));
                    }
                }
            } else {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
