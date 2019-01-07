
package com.CrossCountry.Survey;

import java.util.concurrent.CountDownLatch;

import com.CrossCountry.Survey.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.CrossCountry.Survey.utils.StaticConfig;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

@SpringBootApplication
public class DataScreenApplication implements CommandLineRunner{
	@Autowired
	CommonService commonService;
	
	@Autowired
	CommonNetWorkService commonNetWorkService;
	
	@Autowired
	CommonBorderProtectionService commonBorderProtectionService;
	@Autowired
	CommonVerificationService commonVerificationService;
	@Autowired
	CommonSurveillanceService commonSurveillanceService;
	@Autowired
	CommonHostProtectionService commonHostProtectionService;
	@Autowired
	CommonSafetyTechSuperService commonSafetyTechSuperService;
	@Autowired
	GoogleCacheUtils cacheUtils;
	
	@Value("${datascreen_grpc_port}")
	int port;
	
	@Value("${screen_ip}")
	String grpcIp;

	public static void main(String[] args) {
		if(args != null && args.length>=1){
			StaticConfig.disSubValue = args[0];
			System.out.println("--------------参数"+StaticConfig.disSubValue);
    		if(args[0].indexOf("version") != -1){
    			System.out.println("Version:1.3.0_2018-12-12");
    		}
    		System.exit(0);
    	}
		StaticConfig.getDataCenterSetting();
		StaticConfig.dbConnetction();
		SpringApplication application = new SpringApplication(DataScreenApplication.class);
		application.setDefaultProperties(StaticConfig.intiOption);
		application.run(args);
		CountDownLatch la = new CountDownLatch(1);
		try {
			la.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
		Server server = serverBuilder
	    		  .addService(ServerInterceptors.intercept(commonService, new HeaderServerInterceptor()))
	    		  .addService(ServerInterceptors.intercept(commonNetWorkService, new HeaderServerInterceptor()))
	    		  .addService(ServerInterceptors.intercept(commonBorderProtectionService, new HeaderServerInterceptor()))
	    		  .addService(ServerInterceptors.intercept(commonSurveillanceService, new HeaderServerInterceptor()))
	    		  .addService(ServerInterceptors.intercept(commonVerificationService, new HeaderServerInterceptor()))
	    		  .addService(ServerInterceptors.intercept(commonHostProtectionService, new HeaderServerInterceptor()))
				  .addService(ServerInterceptors.intercept(commonSafetyTechSuperService, new HeaderServerInterceptor()))
	                .build();
	        server.start();
	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            @Override
	            public void run() {
	                System.err.println("*** shutting down gRPC server since JVM is shutting down");
	                if (server != null) {
	                    server.shutdown();
	                }
	                System.err.println("*** server shut down");
	            }
	        });
	}

}

