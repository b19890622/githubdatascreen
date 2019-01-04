package com.CrossCountry.Survey.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.input.BOMInputStream;

@SuppressWarnings({ "unused", "unchecked" })
public class StaticConfig {

	public static Properties intiOption = new Properties();

	// 程序所在目录
	public static String userDir = null;
	// 操作系统名称
	public static String osName = null;
	// 上次登录的用户名
	public static String userName = null;
	// Java虚拟机的版本号
	public static float version = 0;

	public static String driver;
	public static String url;
	public static String user;
	public static String pwd;
	public static int intervalTimes = 5000;
	public static int intervalDeviceCountTimes = 60000;
	public static String disSubValue;

	/*
	 * static {
	 * 
	 * }
	 */

	public static void getDataCenterSetting() {
		String PSGSM_HOME = System.getenv("PSGSM_HOME") + "/conf/PSSSP.ini";

		OutputStream outkaf = null;

		try {

			Map<String, Object> ini = readIniFile(PSGSM_HOME);

			Map<String, Object> mapgrpc = (Map<String, Object>) ini.get("UISERVER");

			intiOption.setProperty("DATASCREEN_GRPC_PORT".toLowerCase(), (String) mapgrpc.get("DATASCREEN_GRPC_PORT"));

			Map<String, Object> mapjdbc = (Map<String, Object>) ini.get("DB");
			intiOption.setProperty("spring.datasource.username", ((String) mapjdbc.get("DB_UID")).replace("\"", ""));
			intiOption.setProperty("spring.datasource.password", ((String) mapjdbc.get("DB_PWD")).replace("\"", ""));
			intiOption.setProperty("spring.datasource.driver-class-name",
					((String) mapjdbc.get("DB_DRIVER")).replace("\"", ""));
			intiOption.setProperty("spring.datasource.url", ((String) mapjdbc.get("DB_URL")).replace("\"", ""));
			Map<String, Object> mapdatascreen = (Map<String, Object>) ini.get("DATASCREEN");
			intiOption.setProperty("spring.datasource.initialSize",
					(String) mapdatascreen.get("DATASCREEN.DBinitialSize".toUpperCase()));
			intiOption.setProperty("spring.datasource.minIdle",
					(String) mapdatascreen.get("DATASCREEN.DBminIdle".toUpperCase()));
			intiOption.setProperty("spring.datasource.maxActive",
					(String) mapdatascreen.get("DATASCREEN.DBmaxActive".toUpperCase()));
			intiOption.setProperty("spring.datasource.maxWait",
					(String) mapdatascreen.get("DATASCREEN.DBmaxWait".toUpperCase()));
			intiOption.setProperty("spring.datasource.timeBetweenEvictionRunsMillis",
					(String) mapdatascreen.get("DATASCREEN.DBtimeBetween".toUpperCase()));
			intiOption.setProperty("spring.datasource.minEvictableIdleTimeMillis",
					(String) mapdatascreen.get("DATASCREEN.DBminIdleTime".toUpperCase()));
			intiOption.setProperty("spring.datasource.testWhileIdle",
					(String) mapdatascreen.get("DATASCREEN.DBtestWhileIdle".toUpperCase()));
			intiOption.setProperty("spring.datasource.testOnBorrow",
					(String) mapdatascreen.get("DATASCREEN.DBtestOnBorrow".toUpperCase()));
			intiOption.setProperty("spring.datasource.testOnReturn",
					(String) mapdatascreen.get("DATASCREEN.DBtestOnReturn".toUpperCase()));
			intiOption.setProperty("spring.datasource.validationQuery",
					(String) mapdatascreen.get("DATASCREEN.DBvalidationQuery".toUpperCase()));

			// 配置mybatis信息
			intiOption.setProperty("mybatis.mapper-locations",
					"classpath:mapper/*.xml,mapper/custom/*.xml,classpath:mapper/custom/*/*.xml");
			intiOption.setProperty("mybatis.config-location", "classpath:properties/sqlMapConfig.xml");

			for (String datascreen : mapdatascreen.keySet()) {
				if (!datascreen.startsWith("DATASCREEN")) {
					intiOption.setProperty(datascreen.toLowerCase(), (String) mapdatascreen.get(datascreen));
				}
			}
			System.getProperties().setProperty("LOG_4J", intiOption.get("screen_log_path").toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String removeIniComments(String source) {
		String result = source;

		if (result.contains(";")) {
			result = result.substring(0, result.indexOf(";"));
		}

		if (result.contains("#")) {
			result = result.substring(0, result.indexOf("#"));
		}

		return result.trim();
	}

	public static Map<String, Object> readIniFile(String fileName) {
		Map<String, List<String>> listResult = new HashMap<>();
		Map<String, Object> result = new HashMap<>();
		Properties systemOption = new Properties();
		String globalSection = "global";

		File file = new File(fileName);

		String log = "";
		if (file.exists() && file.canRead()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				systemOption.load(fis);
				fis.close();
			} catch (Exception ex) {
				log = "无法读取启动信息，系统即将退出" + ex.getMessage();
				System.out.println("无法读取启动信息，系统即将退出" + ex.getMessage());
				// SystemLog.addSecurityLog(log, ex);
				System.exit(0);
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException ioex) {

				}
			}
		} else {
			log = "启动信息不存在，系统即将退出";
			System.out.println("启动信息不存在，系统即将退出");
			// SystemLog.addSecurityLog(log, null);
			System.exit(0);
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new BOMInputStream(new FileInputStream(file))));

			String str = null;
			String currentSection = globalSection;
			List<String> currentProperties = new ArrayList<>();
			boolean lineContinued = false;
			String tempStr = null;

			while ((str = reader.readLine()) != null) {
				str = removeIniComments(str).trim();

				if ("".equals(str) || str == null) {
					continue;
				}

				if (lineContinued == true) {
					str = tempStr + str;
				}

				if (str.endsWith("\\")) {
					lineContinued = true;
					tempStr = str.substring(0, str.length() - 1);
					continue;
				} else {
					lineContinued = false;
				}

				if (str.startsWith("[") && str.endsWith("]")) {
					String newSection = str.substring(1, str.length() - 1).trim();

					if (!currentSection.equals(newSection)) {
						listResult.put(currentSection, currentProperties);
						currentSection = newSection;

						currentProperties = listResult.get(currentSection);
						if (currentProperties == null) {
							currentProperties = new ArrayList<>();
						}
					}
				} else {
					currentProperties.add(str);
				}
			}

			listResult.put(currentSection, currentProperties);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		for (String key : listResult.keySet()) {
			List<String> tempList = listResult.get(key);

			if (tempList == null || tempList.size() == 0) {
				continue;
			}

			if (tempList.get(0).contains("=")) {
				Map<String, String> properties = new HashMap<>();
				for (String s : tempList) {
					int delimiterPos = s.indexOf("=");
					properties.put(s.substring(0, delimiterPos).trim(),
							s.substring(delimiterPos + 1, s.length()).trim());
				}
				result.put(key, properties);
			} else {
				result.put(key, listResult.get(key));
			}
		}

		return result;
	}

	public static void main(String[] arg) {
		System.out.println("-------开始执行-------");
		StaticConfig.getDataCenterSetting();
		System.out.println("-------运行结束-------");
	}

	public static void dbConnetction() {
		String dbdriver = intiOption.getProperty("spring.datasource.driver-class-name").toString();
		String USER = intiOption.getProperty("spring.datasource.username").toString();
		String PASSWORD = intiOption.getProperty("spring.datasource.password").toString();
		String url = intiOption.getProperty("spring.datasource.url").toString();
		// 1.获得数据库链接
		Connection conn = null;
		try {
			Class.forName(dbdriver);
			conn = DriverManager.getConnection(url, USER, PASSWORD);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("连接dm7失败，尝试连接dm6.....");
			try {
				Class.forName("dm6.jdbc.driver.DmDriver");
				url = url.replace(":dm:", ":dm6:");
				conn = DriverManager.getConnection(url, USER, PASSWORD);
				conn.close();
				// 处理PSSSP.ini 文件
				intiOption.setProperty("spring.datasource.driver-class-name", "dm6.jdbc.driver.DmDriver");
				// String dmurl = intiOption.getProperty("db_url");
				// dmurl = dmurl.replace(":dm:", ":dm6:");
				intiOption.setProperty("spring.datasource.url", url);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
		}

	}

}
