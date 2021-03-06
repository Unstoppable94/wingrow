package com.winhong.plugins.cicd.action;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.winhong.plugins.cicd.data.base.ProjectBaseInfo;
import com.winhong.plugins.cicd.docker.TranditionalDockerProject;
import com.winhong.plugins.cicd.maven.MavenProject;
import com.winhong.plugins.cicd.system.InnerConfig;
import com.winhong.plugins.cicd.system.JdkConfig;
import com.winhong.plugins.cicd.system.ProjectType;
import com.winhong.plugins.cicd.tool.Tools;
import com.winhong.plugins.cicd.view.displayData.DisplayBuild;

public class ProjectActionTest {
 	 	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		}

	public static  ProjectBaseInfo setbase() {
		ProjectBaseInfo b=new ProjectBaseInfo();
		b.setCreateTime(System.currentTimeMillis());
		b.setDescription("project111");
		b.setExraProperties("");
		b.setGroupId("default");
		b.setId("");
		b.setName("中午");
		b.setProjectType(ProjectType.MavenProject);
		b.setLastModifyTime(b.getCreateTime());
		b.setMailOnfail("mailoffail@w.com");
		b.setMailOnReovery(null);
		b.setMailOnSuccess(null);
		b.setMaxExcutiontime(60);
 		b.setSCMBranch("master");
		b.setSCMPassword("acd12345");
		b.setSCMTYPE("git");
		b.setSCMUrl("http://10.0.2.50:180/winhong/ciexample.git");
		b.setSCMUser("xiehq");
		b.setTrigger("");
		b.setTriggerProperty("5 * * * *");
	//	p.setBaseInfo(b);
		b.setProjectType(ProjectType.MavenProject);
		return b;
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddOrModifyProject() {
		
		try {
			MavenProject p=new MavenProject();
			ProjectBaseInfo b = setbase();
			p.setBaseInfo(b);
			ProjectAction.AddProject(p);

			p.getBaseInfo().setGroupId("default");
			
		//	ProjectAction.ModifyMavenProject(p);
		} catch (Exception e) {
 			e.printStackTrace();
			fail();
		}
		
 	}

	
	@Test
	public void testAddOrModifyDockerProject() {
		
		try {
			TranditionalDockerProject   p=new TranditionalDockerProject();
			ProjectBaseInfo b = setbase();
			b.setName("docker test");
			b.setProjectType(ProjectType.TraditionalDocker);
			p.setBaseInfo(b);
			ProjectAction.AddProject(p);
			
			
		//	ProjectAction.ModifyMavenProject(p);
		} catch (Exception e) {
 			e.printStackTrace();
			fail();
		}
		
 	}
	
	
	@Test
	public void testDeleteMavenProject() {
		try {
			MavenProject p=new MavenProject();
			ProjectBaseInfo b = setbase();
			p.setBaseInfo(b);
			p.getBaseInfo().setName("delete中文Poject2");

			ProjectAction.AddProject(p);

			ProjectAction.DeleteProject(p.getBaseInfo().getId());
		} catch (Exception e) {
 			e.printStackTrace();
			fail();
		}
	}

	
	@Test
	public void charSet(){
		String n="默认";
		try {
			System.out.println(URLEncoder.encode(n));

			System.out.println(URLEncoder.encode(n, "UTF-8"));
			System.out.println(URLEncoder.encode("TEST", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	@Test
	public void testTriiger() {
		try {
			
			MavenProject p=new MavenProject();
			p.setBaseInfo(setbase());
			p.getBaseInfo().setName("delete中文Poject2");
			ProjectAction.AddProject(p);
		     ProjectAction.triggerBuild(p.getBaseInfo().getId()); 
 			//ProjectAction.DeleteProject(p.getBaseInfo().getId());
		} catch (Exception e) {
 			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testreadLatestProject() {
		try {
			
		String projectId="pro1504146316430";
		ArrayList<JdkConfig> jdk=InnerConfig.defaultConfig().getJdk();
		JdkConfig n=new JdkConfig("test","test","test");
		jdk.add(n);
		InnerConfig.defaultConfig().setJdk(  jdk);
		ArrayList<JdkConfig> jdk2=InnerConfig.defaultConfig().getJdk();
		System.out.println("jdk size"+jdk2.size());
		System.out.println(ProjectAction.readLatestProject(  projectId));
		}catch (Exception e) {
 			e.printStackTrace();
			fail();
		}
	}
	//public static DisplayBuild    triggerBuild(String projectName) 
	
}
