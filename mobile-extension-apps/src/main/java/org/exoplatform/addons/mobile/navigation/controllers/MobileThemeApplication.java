package org.exoplatform.addons.mobile.navigation.controllers;

import juzu.Action;
import juzu.Path;
import juzu.View;
import juzu.bridge.portlet.JuzuPortlet;
import juzu.request.RenderContext;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;

public class MobileThemeApplication {

  @Inject
  PortletPreferences portletPreferences;

  @Inject
  @Path("index.gtmpl")
  Template indexTemplate;

  @Inject
  @Path("edit.gtmpl")
  Template editTemplate;

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


}
