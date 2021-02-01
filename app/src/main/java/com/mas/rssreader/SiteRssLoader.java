package com.mas.rssreader;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SiteRssLoader {
    private SiteRssLoader() {
    }

    public static List<RssItem> load(String url) {
        try {
            URLConnection connection;

            if (url.contains("https"))
                connection = (HttpsURLConnection) new URL(url).openConnection();
            else
                connection = (HttpURLConnection) new URL(url).openConnection();

            InputStream stream = new BufferedInputStream(connection.getInputStream());

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            ArrayList<RssItem> list = new ArrayList<>();

            DefaultHandler handler = new DefaultHandler() {
                final String TITLE = "title";
                final String DESCRIPTION = "description";
                final StringBuilder builder = new StringBuilder();
                String getTITLE;
                String getDESCRIPTION;
                boolean item;
                boolean title;
                boolean description;

                //parser starts parsing a specific element inside the document
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase("item"))
                        item = true;
                    if (qName.equalsIgnoreCase(TITLE) && item)
                        title = true;
                    if (qName.equalsIgnoreCase(DESCRIPTION) && item)
                        description = true;
                }

                //parser ends parsing the specific element inside the document
                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (qName.equalsIgnoreCase(DESCRIPTION) && item) {
                        description = false;
                        getDESCRIPTION = builder.toString().replaceAll("\r", "").replaceAll("\n", "");
                        builder.delete(0, builder.length());
                    }

                    if (qName.equalsIgnoreCase(TITLE) && item) {
                        title = false;
                    }

                    if (qName.equalsIgnoreCase("item")) {
                        item = false;
                        list.add(new RssItem(getTITLE, getDESCRIPTION));
                    }
                }

                //reads the text value of the currently parsed element
                @Override
                public void characters(char[] ch, int start, int length) {
                    if (item) {
                        if (title)
                            getTITLE = new String(ch, start, length);
                        if (description)
                            builder.append(ch, start, length);
                    }
                }
            };

            String response = connection.getHeaderField(0);
            System.out.println(response);

            if (response.contains("200"))
                parser.parse(stream, handler);
            else
                throw new IOException(response);

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
