package com.winhong.plugins.cicd.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winhong.plugins.cicd.system.InnerConfig;
import com.winhong.plugins.cicd.tool.Tools;
import com.winhong.plugins.cicd.user.User;

public class UserAction {

	private static String userDir = "/user/";

	private static String deletedUserDir = "/deleteduser/";

	private static final Logger log = LoggerFactory.getLogger(UserAction.class);

 	public static final  String PasswordMask="******";

 	public static final  String defaultAdmin="admin";
 	public static final  String defaultPassword="admin";

 	static {
 	 
		try {
			if (!userExist(defaultAdmin)){
				User defaultUser = new User();
				defaultUser.setUsername(defaultAdmin);
				defaultUser.setRole(User.adminRole);
				defaultUser.setPassword(defaultPassword);
				addUser(defaultUser);
				
			}
		} catch (IOException e) {
 			e.printStackTrace();
 			log.error("create default user failed!");
		}
	 
 	 
 		 

}
 	 
 	
	public static ArrayList<User> getAllUser(String username) throws IOException {

		File folder = new File(InnerConfig.defaultConfig().getDataDir()+userDir);
		
		File[] listOfFiles = folder.listFiles();
		ArrayList<User> users = new ArrayList<User>();
		log.debug("usename:"+username);	

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				log.debug("filename:" + fileName);
				if (fileName.endsWith(".json")) {
					if (username != null && username != "")
						if (URLDecoder.decode(fileName, "UTF-8").indexOf(
								username) < 0) {
							continue;
						}
					User u = (User) Tools.objectFromJsonFile(
							listOfFiles[i].getAbsolutePath(), User.class);
					log.debug("adduser:"+u.getUsername());

					users.add(u);
				}

			}
		}

		return users;
	}

	public static User getUserinfo(String name) throws FileNotFoundException,
			UnsupportedEncodingException {
		return (User) Tools.objectFromJsonFile(getUserfilename(name),
				User.class);

	}

	private static String getUserfilename(String name)
			throws FileNotFoundException, UnsupportedEncodingException {

		File dir = new File(InnerConfig.defaultConfig().getDataDir() + userDir);

		if (dir.exists() == false)
			;
		dir.mkdirs();

		String temp = InnerConfig.defaultConfig().getDataDir() + userDir
				+ URLEncoder.encode(name, "UTF-8") + ".json";
		return temp;
	}

	public static User addUser(User user) throws IOException {

		if (userExist(user.getUsername()))
			throw new IOException("用户已经存在");
		long time = System.currentTimeMillis();
		
		user.setId(String.valueOf(time));
		user.setCreateTime(time);
		user.setLatestModifyTime(time);

		Tools.saveStringToFile(Tools.getJson(user),
				getUserfilename(user.getUsername()));
		return user;

	}

	public static User modifyUser(User user) throws IOException {
		if (!userExist(user.getUsername()))
			throw new IOException("用户不存在");
		//密码处理，
		if (user.getPassword().equals(PasswordMask)){
			User oldUser = getUserinfo(user.getUsername());
			user.setPassword(oldUser.getPassword());
		}
		long time = System.currentTimeMillis();
		user.setLatestModifyTime(time);
		File file = new File(getUserfilename(user.getUsername()));
		
		file.renameTo(new File(file.getAbsoluteFile() + "@" + time));

		Tools.saveStringToFile(Tools.getJson(user),
				getUserfilename(user.getUsername()));
		return user;

	}

	public static boolean deleteUser(String username) throws IOException {

		File dir = new File(InnerConfig.defaultConfig().getDataDir()
				+ deletedUserDir);

		if (dir.exists() == false)
			;
		dir.mkdirs();

		long time = System.currentTimeMillis();
		 
		File file = new File(getUserfilename(username));

		file.renameTo(new File(file.getAbsoluteFile() + "@" + time+"_deleted"));

		//tools.saveStringToFile(tools.getJson(user),
	//			getUserfilename(user.getUsername() + "@" + time + ));
		return true;

	}

	public static boolean userExist(String username)
			throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(getUserfilename(username));

		return file.exists();
	}

}
