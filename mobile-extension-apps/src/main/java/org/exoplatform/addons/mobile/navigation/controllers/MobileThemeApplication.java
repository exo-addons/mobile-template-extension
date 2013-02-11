package org.exoplatform.addons.mobile.navigation.controllers;

import juzu.Path;
import juzu.View;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;
import java.io.IOException;

public class MobileThemeApplication {

  @Inject
  PortletPreferences portletPreferences;

  @Inject
  @Path("index.gtmpl")
  Template indexTemplate;

  @View
  public void index() throws IOException
  {
    String logoURL = portletPreferences.getValue("logo", "http://www.exoplatform.com/rest/jcr/repository/collaboration/sites%20content/live/website/web%20contents/10.Homepage/newhomepage/images/LogoHeaderTop.png");
    String color1 = portletPreferences.getValue("color1", "#3366FF");
    String color2 = portletPreferences.getValue("color2", "#333333");
    String color3 = portletPreferences.getValue("color3", "#666666");
    String color4 = portletPreferences.getValue("color4", "#AAAAAA");
    String color5 = portletPreferences.getValue("color5", "#FFFFFF");

    indexTemplate.with().set("logo", logoURL)
            .set("color1", color1)
            .set("color2", color2)
            .set("color3", color3)
            .set("color4", color4)
            .set("color5", color5)
            .render();
  }


}
