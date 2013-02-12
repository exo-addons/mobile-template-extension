package org.exoplatform.addons.mobile.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: benjamin
 * Date: 07/06/12
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class Feed {
  String title;
  String more;

  List<Item> items;

  public Feed() {
    items = new ArrayList<Item>();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMore() {
    return more;
  }

  public void setMore(String more) {
    this.more = more;
  }

  public List<Item> getItems() {
    return items;
  }

  public void addItem(Item item) {
    this.items.add(item);
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}
