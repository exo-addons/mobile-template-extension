@Application(defaultController = org.exoplatform.addons.mobile.navigation.controllers.MobileThemeApplication.class)
@Portlet (name = "MobileThemePortlet")

@Assets(
        location = AssetLocation.SERVER,
        scripts = {
                @Script(src = "js/jquery-1.7.1.min.js", id = "jquery")
        }
)
package org.exoplatform.addons.mobile.navigation;

import juzu.Application;
import juzu.asset.AssetLocation;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.portlet.Portlet;
