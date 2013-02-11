package org.exoplatform.addons.mobile.switcher.controllers;

import juzu.Path;
import juzu.View;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;
import java.io.IOException;

public class MobileSwitcherApplication {

  @Inject
  PortletPreferences portletPreferences;

  @Inject
  @Path("index.gtmpl")
  Template indexTemplate;

  @View
  public void index() throws IOException
  {
    String url = portletPreferences.getValue("url", "/portal/mobile");
    indexTemplate.with().set("url", url).render();
  }


}
