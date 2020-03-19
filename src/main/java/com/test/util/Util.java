package com.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util 
{
public Util()
{
	
}



public static String currentDate(String dateFormat)
{
Date currentdate = new Date();
SimpleDateFormat format = new SimpleDateFormat(dateFormat);
String DateToStr = format.format(currentdate);

return DateToStr;
}
}
