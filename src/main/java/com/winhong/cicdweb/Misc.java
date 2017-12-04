package com.winhong.cicdweb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winhong.plugins.cicd.system.Config;
import com.winhong.plugins.cicd.system.InnerConfig;
import com.winhong.plugins.cicd.system.ProjectType;
import com.winhong.plugins.cicd.system.RancherConfig;
import com.winhong.plugins.cicd.system.RegistryConfig;
import com.winhong.plugins.cicd.system.RegistryMirrorConfig;
import com.winhong.plugins.cicd.system.SMTPConfig;
import com.winhong.plugins.cicd.system.SonarConfig;
import com.winhong.plugins.cicd.tool.Tools;

@Path("/misc")
@Consumes("application/json;charset=UTF-8")
public class Misc {

	
	
	public Misc() {
		// TODO Auto-generated constructor stub
	}

	private static final Logger log = LoggerFactory.getLogger(Misc.class);
	
	
	@GET
	@Path("projecttype")
	@Produces("application/json;charset=utf-8")
	public String getMavenList() {
		try {
			return Tools.getJson( ProjectType.getProjectTypeList());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	} 
	
	
	@GET
	@Path("stagesinfo")
	@Produces("application/json;charset=utf-8")
	public String getAllStageDefines() {
		try {
			return Tools.getJson( ProjectType.getStageDisplaylist());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	} 

	@GET
	@Path("maven")
	@Produces("application/json;charset=utf-8")
	public String getMavenList_() {
		try {
			return Tools.getJson(InnerConfig.defaultConfig().getMaven());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@GET
	@Path("/jdk")
	@Produces("application/json;charset=utf-8")
	public String getJdkList() {
		try {
			return Tools.getJson(InnerConfig.defaultConfig().getJdk());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@GET
	@Path("/sonar")
	@Produces("application/json;charset=utf-8")
	public String getSonar() {
		try {
			return Tools.getJson(Config.getSonarConfig());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@POST
	@Path("/sonar")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String saveSonar(String json) {
		try {

			SonarConfig config = (SonarConfig) Tools.objectFromJsonString(json,
					SonarConfig.class);
			Config.saveConfig(config);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@PUT
	@Path("/sonar")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String PUTsaveSonar(String json) {
		return saveSonar(json);
	}

	
	
	
	@GET
	@Path("/smtp")
	@Produces("application/json;charset=utf-8")
	public String getSmtp() {
		try {
			return Tools.getJson(Config.getSMTPConfig());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	
	
	@POST
	@Path("/smtp")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String saveSmtp(String json) {
		try {

			SMTPConfig config = (SMTPConfig) Tools.objectFromJsonString(json,
					SMTPConfig.class);
			Config.saveConfig(config);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@PUT
	@Path("/smtp")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String PUTsaveSmtp(String json) {
		return saveSmtp(json);
	}

	
	@GET
	@Path("/rancher")
	@Produces("application/json;charset=utf-8")
	public String getRancher() {
		try {
			return Tools.getJson(Config.getRancherConfig());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@POST
	@Path("/rancher")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String saveRancher(String json) {
		try {

			RancherConfig config = (RancherConfig) Tools.objectFromJsonString(
					json, RancherConfig.class);
			Config.saveConfig(config);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@PUT
	@Path("/rancher")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String PUTsaveRancher(String json) {
		return saveRancher(json);
	}

	@GET
	@Path("/mirror")
	@Produces("application/json;charset=utf-8")
	public String getMirror() {
		try {
			return Tools.getJson(Config.getRegistryMirrorConfig());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@POST
	@Path("/mirror")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String saveMirror(String json) {
		try {

			RegistryMirrorConfig config = (RegistryMirrorConfig) Tools
					.objectFromJsonString(json, RegistryMirrorConfig.class);
			Config.saveMirrorConfig(config);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@PUT
	@Path("/mirror")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String PUTMirror(String json) {
		return saveMirror(json);
	}

	@GET
	@Path("/registry")
	@Produces("application/json;charset=utf-8")
	public String getRegistryList() {
		try {
			return Tools.getJson(Config.getRegistryList().getRegistries());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@GET
	@Path("/registry/{serverName}")
	@Produces("application/json;charset=utf-8")
	public String getRegistryList(@PathParam("serverName") String serverName) {
		try {
			log.debug("serverName" + serverName);
			return Tools.getJson(Config.getRegistryConfig(serverName));
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@PUT
	@Path("/registry/{serverName}")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String addRegistry(@PathParam("serverName") String serverName,
			String json) {
		RegistryConfig config;
		try {
			config = (RegistryConfig) Tools.objectFromJsonString(json,
					RegistryConfig.class);
			if (!config.getServer().equals(serverName)) {
				return WebTools.Error("ServerName 不能修改！");

			}
			Config.saveRegistry(config);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@POST
	@Path("/registry")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json")
	public String saveRegistry(String json) {
		try {

			RegistryConfig config = (RegistryConfig) Tools
					.objectFromJsonString(json, RegistryConfig.class);

			ArrayList<RegistryConfig> reg = new ArrayList<RegistryConfig>();
			try {
				reg=Config.getRegistryList().getRegistries();
			} catch (java.io.FileNotFoundException e) {
				reg = new ArrayList<RegistryConfig>();
			}

			for (int i = 0; i < reg.size(); i++) {
				if (reg.get(i).getServer().equals(config.getServer())) {
					return WebTools.Error("仓库配置已经存在！");
				}
			}
			Config.saveRegistry(config);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

	@DELETE
	@Path("/registry/{serverName}")
	@Produces("application/json;charset=utf-8")
	@Consumes("application/json;charset=utf-8")
	public String deleteRegistry(@PathParam("serverName") String serverName,
			String json) {
		try {
			Config.deleteRegistry(serverName);
			return "{}";
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}
	
	
	
	@GET
	@Path("/env")
	@Produces("application/json;charset=utf-8")
	public String getEnv() {
		try {

			Properties env = System.getProperties();
			return Tools.getJson(env);

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			return WebTools.Error(e);
		}
	}

}
