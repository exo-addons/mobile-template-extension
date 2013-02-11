package org.exoplatform.addons.mobile.switcher.controllers;

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

public class MobileSwitcherApplication {

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
    PortletMode portletMode = renderContext.getProperty(JuzuPortlet.PORTLET_MODE);
    if (portletMode.equals(PortletMode.VIEW))
    {
    String url = portletPreferences.getValue("url", "/portal/mobile");
    indexTemplate.with().set("url", url).render();
    }
    else
    {
      String url = portletPreferences.getValue("url", "/portal/mobile");
      editTemplate.with().set("url", url).render();
    }
  }

  @Action
  public void save(String url)
  {
    try {
      portletPreferences.setValue("url", url);
      portletPreferences.store();
    } catch (ReadOnlyException e) {
    } catch (ValidatorException e) {
    } catch (IOException e) {
    }
  }

}
