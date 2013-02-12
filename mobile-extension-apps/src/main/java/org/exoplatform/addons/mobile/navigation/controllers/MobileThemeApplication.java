package org.exoplatform.addons.mobile.navigation.controllers;

import juzu.Action;
import juzu.Path;
import juzu.Resource;
import juzu.View;
import juzu.bridge.portlet.JuzuPortlet;
import juzu.request.RenderContext;
import juzu.template.Template;
import org.exoplatform.addons.mobile.model.Feed;
import org.exoplatform.addons.mobile.model.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MobileThemeApplication {

  @Inject
  PortletPreferences portletPreferences;

  @Inject
  @Path("index.gtmpl")
  Template indexTemplate;

  @Inject
  @Path("edit.gtmpl")
  Template editTemplate;

  @Inject
  @Path("feed.gtmpl")
  Template feedTemplate;

  String url_ = "https://kuler-api.adobe.com/rss/get.cfm?key=9A2E468872FE17525244F66635D22B83&listtype=";
  int size_ = 20;

  @View
  public void index(RenderContext renderContext) throws IOException
  {
    String logoURL = portletPreferences.getValue("logo", "http://www.exoplatform.com/rest/jcr/repository/collaboration/sites%20content/live/website/web%20contents/10.Homepage/newhomepage/images/LogoHeaderTop.png");
    String color1 = portletPreferences.getValue("color1", "#3366FF");
    String color2 = portletPreferences.getValue("color2", "#333333");
    String color3 = portletPreferences.getValue("color3", "#666666");
    String color4 = portletPreferences.getValue("color4", "#AAAAAA");
    String color5 = portletPreferences.getValue("color5", "#FFFFFF");

    PortletMode portletMode = renderContext.getProperty(JuzuPortlet.PORTLET_MODE);
    if (portletMode.equals(PortletMode.VIEW))
    {
      indexTemplate.with().set("logo", logoURL)
            .set("color1", color1)
            .set("color2", color2)
            .set("color3", color3)
            .set("color4", color4)
            .set("color5", color5)
            .render();
    }
    else
    {
      editTemplate.with().set("logo", logoURL)
              .set("color1", color1)
              .set("color2", color2)
              .set("color3", color3)
              .set("color4", color4)
              .set("color5", color5)
              .render();
    }
  }

  @Action
  public void save(String logo, String color1, String color2, String color3, String color4, String color5)
  {
    try {
      portletPreferences.setValue("logo", logo);
      portletPreferences.setValue("color1", color1);
      portletPreferences.setValue("color2", color2);
      portletPreferences.setValue("color3", color3);
      portletPreferences.setValue("color4", color4);
      portletPreferences.setValue("color5", color5);
      portletPreferences.store();
    } catch (ReadOnlyException e) {
    } catch (ValidatorException e) {
    } catch (IOException e) {
    }
  }

  @Resource
  public void getFeed(String type) throws Exception {

    Document document_ = getData(url_+type);

    NodeList nodeList = document_.getElementsByTagName("item");

    String title = ((Element)document_.getElementsByTagName("channel").item(0)).getElementsByTagName("title").item(0).getTextContent();
    String more = ((Element)document_.getElementsByTagName("channel").item(0)).getElementsByTagName("link").item(0).getTextContent();

    Feed feed_ = new Feed();
    feed_.setTitle(title);
    feed_.setMore(more);

    for (int i = 0; i < nodeList.getLength() && i < size_; i++)
    {
      Node node = nodeList.item(i);

      Element fstElmnt = (Element) node;

      title = ((Element)fstElmnt.getElementsByTagName("title").item(0)).getTextContent();
      String link = ((Element)fstElmnt.getElementsByTagName("link").item(0)).getTextContent();
      String pubDate = ((Element)fstElmnt.getElementsByTagName("pubDate").item(0)).getTextContent();
      String desc = ((Element)fstElmnt.getElementsByTagName("description").item(0)).getTextContent();
      Element themeItem = ((Element)fstElmnt.getElementsByTagName("kuler:themeItem").item(0));

      String themeImage = ((Element)themeItem.getElementsByTagName("kuler:themeImage").item(0)).getTextContent();
      String[] colors = new String[5];

      Element themeSwatches = ((Element)themeItem.getElementsByTagName("kuler:themeSwatches").item(0));
      NodeList swatches = themeSwatches.getElementsByTagName("kuler:swatch");
      for (int j = 0; j < swatches.getLength() ; j++)
      {
        Element swatch = (Element)swatches.item(j);
        String color = ((Element)swatch.getElementsByTagName("kuler:swatchHexColor").item(0)).getTextContent();
        colors[j] = color;
      }

      Item item = new Item();
      item.setTitle(title);
      item.setLink(link);
      item.setDescription(desc);
      item.setPubDate(pubDate);

      item.setKulerImage(themeImage);
      item.setKulerColors(colors);

      feed_.addItem(item);

    }

    feedTemplate.with().set("feed", feed_).render();

  }

  private Document getData(String uri) {

    Document doc = null;

    try {
      URL url = new URL(uri);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/xml");

      InputStream xml = connection.getInputStream();

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse(xml);


    } catch (IOException e) {
    } catch (ParserConfigurationException e) {
    } catch (SAXException e) {
    }

    return doc;
  }


}
