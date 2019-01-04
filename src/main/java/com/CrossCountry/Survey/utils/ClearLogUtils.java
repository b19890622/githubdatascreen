package com.CrossCountry.Survey.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ClearLogUtils {
	
	@Value("${screen_log_path}")
	String log_path;
	
	
	@Scheduled(cron = "0 0 3 * * ?",zone="GMT+08")
	public void clearLog(){
		SimpleDateFormat fomart = null;
		fomart = new SimpleDateFormat("yyyy-MM");
		Date da = new Date();
		String yearToMonth = fomart.format(da); //当天所在月份
		Calendar der = Calendar.getInstance();
		der.setTime(da);
		der.add(Calendar.DATE, -3);
		da = der.getTime();
		fomart = new SimpleDateFormat("MM-dd-yyyy");
		String yearToday = fomart.format(da); //3天之前日期 早于等于 的时间的日志删除
		String path = log_path+File.separator+"datacenter";
		File file = new File(path);
		File[] files = file.listFiles();
		String name = "";
		for(int i=0; i<files.length; i++){
			if(files[i].isFile()){
				continue;
			}else{
				deleteFile(files[i], yearToMonth, yearToday);
			}
		}
		
	}
	
	private void deleteFile(File file, String compareDate, String yearToday){
		String fileDname = file.getName();
		if(fileDname.compareTo(compareDate)<0){
			deleteFile(file);
		}else if(fileDname.compareTo(compareDate) == 0){
			deleteCheckFile(file, yearToday);
		}
	}
	
	private void deleteCheckFile(File file, String checkDate){
		File[] files = file.listFiles();
		String name = "";
		for(File fileTem : files){
			if(!fileTem.isDirectory()){
				name = fileTem.getName();
				String[]args = name.split("-");
				name = args[1]+"-"+args[2]+"-"+args[3];
				if(name.compareTo(checkDate)<=0){
					fileTem.delete();
				}
			}else{
				deleteCheckFile(fileTem, checkDate);
			}
		}
	}
	
	private void deleteFile(File file){
		File[] files = file.listFiles();
		for(File fileTem : files){
			if(!fileTem.isDirectory()){
				fileTem.delete();
			}else{
				deleteFile(fileTem);
			}
		}
		file.delete();
	}

}
