
<%@page import="java.util.Iterator"%>
<%@page import="org.exoplatform.portal.mop.navigation.Scope"%>
<%@page import="org.exoplatform.portal.mop.SiteKey"%>
<%@page import="org.exoplatform.portal.mop.user.UserNavigation"%>
<%@page import="org.exoplatform.portal.mop.Visibility"%>
<%@page import="org.exoplatform.portal.mop.user.UserNodeFilterConfig"%>
<%@page import="org.exoplatform.portal.mop.user.UserPortal"%>
<%@page import="org.exoplatform.portal.application.PortalRequestContext"%>
<%@page import="org.exoplatform.portal.config.UserPortalConfig"%>
<%@page import="org.exoplatform.container.PortalContainer"%>
<%@page import="org.exoplatform.portal.config.UserPortalConfigService"%>
<%@page import="org.exoplatform.portal.webui.util.Util"%>
<%@page import="java.util.Collection"%>
<%@page import="org.exoplatform.portal.mop.user.UserNode"%><%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%
String nomPortail = Util.getPortalRequestContext().getSiteName();
String nomUtilisateur = Util.getPortalRequestContext().getRemoteUser();


	    // Récupération du service
	    UserPortalConfigService userPortalConfigService = (UserPortalConfigService)PortalContainer.getInstance().getComponentInstanceOfType(UserPortalConfigService.class);
		UserPortalConfig userPortalCfg = userPortalConfigService.getUserPortalConfig(nomPortail, nomUtilisateur, PortalRequestContext.USER_PORTAL_CONTEXT);
	    UserPortal userPortal = userPortalCfg.getUserPortal();
	
	    // filtres
	    UserNodeFilterConfig.Builder filterConfigBuilder = UserNodeFilterConfig.builder();
	    filterConfigBuilder.withReadWriteCheck().withVisibility(Visibility.DISPLAYED, Visibility.TEMPORAL);
	    filterConfigBuilder.withTemporalCheck();
	    UserNodeFilterConfig filterConfig = filterConfigBuilder.build();
	
	    // Récupération des noeuds
	    UserNavigation navigation = userPortal.getNavigation(SiteKey.portal(nomPortail));
	    UserNode root = userPortal.getNode(navigation, Scope.ALL, filterConfig, null);
	

	//UserNode rootNode = null;
	Collection<UserNode> menusExo = null;
	//rootNode = getNavigation("portal", Util.getPortalRequestContext().getRemoteUser());
	menusExo = (Collection<UserNode>) root.getChildren();
	int length = menusExo.size();
	%>
<link rel="stylesheet" type="text/css" href="/mobile-extension-apps/css/navigation/demo.css" />
<script type="text/javascript" src="/mobile-extension-apps/js/navigation/jquery.min-1.3.2.js"></script>
<script type="text/javascript" src="/mobile-extension-apps/js/navigation/query.easing.1.3.js"></script>
<script type="text/javascript" src="/mobile-extension-apps/js/navigation/script.js"></script>

<div id="MobileNav">
	<ul class="container">
	<%
		Iterator<UserNode> iterator = menusExo.iterator();
		while( iterator.hasNext()){
			UserNode node = iterator.next();
			Iterator<UserNode> iterator2 = node.getChildren().iterator();
		%>
			<li class="menu">
				<ul>
					<li class="button">
						<a href="<%=node.getURI()%>" class="green">
							<%=node.getResolvedLabel()%>
							<span id="menuArrow" <%if(iterator2.hasNext()){%>class="down"<%}else{%>class="right"<%}%>></span>
						</a>
					</li>
					<%
					if(iterator2.hasNext()){
					%>
					<li class="dropdown">
						<ul>
							<%
							while(iterator2.hasNext()){
								UserNode childNode = iterator2.next();
							%>
								<li><a href="<%=childNode.getURI()%>" ><%=childNode.getResolvedLabel()%></a></li>
							<%}%>
							
						</ul>
					</li>
					<%}%>
				</ul>
			</li>
			<%
		}
	%>
	</ul>			
</div>
