
package org.exoplatform.addons.mobile.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.exoplatform.portal.application.PortalRequestContext;

public class AddMetadataFilter implements org.exoplatform.web.filter.Filter {

    final static public String PORTAL_METADATA_KEY = PortalRequestContext.REQUEST_METADATA;

    final static public String VERSION_KEY = "viewport";

    final static public String PROPERTY_FILEPATH = "/version.properties";

    String version_value="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;";

    public AddMetadataFilter() {
        
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

		Map<String, String> map = (Map<String, String>) request.getAttribute(PORTAL_METADATA_KEY);
        if (map == null) {
            map = new HashMap<String, String>();
        }

        map.put(VERSION_KEY, version_value);
        request.setAttribute(PORTAL_METADATA_KEY, map);
        chain.doFilter(request, response);
    }

}