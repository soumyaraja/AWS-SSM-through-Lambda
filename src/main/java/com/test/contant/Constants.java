package com.test.contant;

import com.test.util.Util;

public final class Constants {
		
		public static String DBINSERTJOB = "command=nohup java -jar /home/job/DbInsert.jar";
		public static String XMLSCPJOB = "command=nohup java -jar /home/job/Xmlgeneration.jar";
		public static String S3OPERATIONJOB = "command=nohup java -jar /home/job/s3Operation.jar";
		public static String currentdate  = Util.currentDate("yyyyMMdd");
		
		
		
}
