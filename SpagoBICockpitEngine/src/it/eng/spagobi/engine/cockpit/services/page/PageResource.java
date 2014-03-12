/*
 * SpagoBI, the Open Source Business Intelligence suite
 * � 2005-2015 Engineering Group
 *
 * This file is part of SpagoBI. SpagoBI is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 2.1 of the License, or any later version. 
 * SpagoBI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details. You should have received
 * a copy of the GNU Lesser General Public License along with SpagoBI. If not, see: http://www.gnu.org/licenses/.
 * The complete text of SpagoBI license is included in the COPYING.LESSER file. 
 */
package it.eng.spagobi.engine.cockpit.services.page;

import it.eng.spagobi.engine.cockpit.CockpitEngine;
import it.eng.spagobi.engine.cockpit.CockpitEngineInstance;
import it.eng.spagobi.utilities.engines.EngineConstants;
import it.eng.spagobi.utilities.engines.SpagoBIEngineServiceExceptionHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @authors Andrea Gioia (andrea.gioia@eng.it)
 * 
 */

@Path("/1.0/page")
public class PageResource extends AbstractCockpitEngineService {

	static private Map<String, JSONObject> pages;
	static private Map<String, String> urls;
	
	static {
		pages = new HashMap<String, JSONObject>();
		urls = new HashMap<String, String>();
		
		try {
			pages.put("edit", new JSONObject("{name: 'execute', description: 'the cockpit edit page', parameters: []}"));
			urls.put("edit", "/WEB-INF/jsp/cockpit.jsp");
			pages.put("execute", new JSONObject("{name: 'execute', description: 'the cockpit execution page', parameters: ['template']}"));
			urls.put("execute", "/WEB-INF/jsp/cockpit.jsp");
		} catch (JSONException t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		}
	}
	
	static private Logger logger = Logger.getLogger(PageResource.class);

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDataSets() {
		try {
			JSONArray resultsJSON = new JSONArray();
			Iterator<String> it = pages.keySet().iterator();
			while(it.hasNext()) {
				String pageName = it.next();
				resultsJSON.put(pages.get(pageName));
			}
			
			return resultsJSON.toString();	
		} catch(Throwable t) {
			throw SpagoBIEngineServiceExceptionHandler.getInstance().getWrappedException("", getEngineInstance(), t);
		} finally {			
			logger.debug("OUT");
		}	
	}
	
	@GET
	@Path("/{pagename}")
	@Produces("text/html")
	public void openPage(@PathParam("pagename") String pageName) {
		CockpitEngineInstance engineInstance;
		String dispatchUrl = null;
		
		try {
			if("execute".equals(pageName)) {
	        	engineInstance = CockpitEngine.createInstance(
	        			getIOManager().getTemplateAsString(), getIOManager().getEnv()
	        	);
	        	getIOManager().getHttpSession().setAttribute(EngineConstants.ENGINE_INSTANCE, engineInstance);
	        	//getExecutionSession().setAttributeInSession( ENGINE_INSTANCE, whatIfEngineInstance);
	        	dispatchUrl = "/WEB-INF/jsp/cockpit.jsp";
			} else if("edit".equals(pageName)) {
				JSONObject template = null;
	        	template = buildBaseTemplate();
	     
	        	// create a new engine instance
	        	engineInstance = CockpitEngine.createInstance(
	        			template.toString(), // servletIOManager.getTemplateAsString(), 
	        			getIOManager().getEnv()
	        	);
	        	getIOManager().getHttpSession().setAttribute(EngineConstants.ENGINE_INSTANCE, engineInstance);
	        	//getExecutionSession().setAttributeInSession( ENGINE_INSTANCE, whatIfEngineInstance);
	        	dispatchUrl = "/WEB-INF/jsp/cockpit.jsp";
			} else {
				//error
				dispatchUrl = "/WEB-INF/jsp/error.jsp";
			}
			request.getRequestDispatcher(dispatchUrl).forward(request, response);
		} catch(Throwable t) {
			throw SpagoBIEngineServiceExceptionHandler.getInstance().getWrappedException("", getEngineInstance(), t);
		} finally {			
			logger.debug("OUT");
		}	
	}
	
	private JSONObject buildBaseTemplate() {
		JSONObject template;
		
		logger.debug("IN");
		template = new JSONObject();
		logger.debug("OUT");
		
		return template;
	}
}
