package org.exoplatform.addons.mobile.model;

/**
 * Created with IntelliJ IDEA.
 * User: benjamin
 * Date: 07/06/12
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public class Item {
  String title;
  String description;
  String pubDate;
  String link;

  String kulerImage;
  String[] kulerColors;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPubDate() {
    return pubDate;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getKulerImage() {
    return kulerImage;
  }

  public void setKulerImage(String kulerImage) {
    this.kulerImage = kulerImage;
  }

  public String[] getKulerColors() {
    return kulerColors;
  }

  public String color(int i) {
    return kulerColors[i];
  }

  public void setKulerColors(String[] kulerColors) {
    this.kulerColors = kulerColors;
  }
}
