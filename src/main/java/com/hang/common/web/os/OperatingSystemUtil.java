package com.hang.common.web.os;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hang.common.utils.StringUtil;

public class OperatingSystemUtil
{
    private static Pattern regexWindows =Pattern.compile("Windows\\s?(NT|XP)(\\s*)((\\d*\\.\\d*)|(\\d*))",Pattern.CASE_INSENSITIVE);
    private static Pattern regexLinux = Pattern.compile("(?<!(Android).{1,100})Linux(?!(.+(Android)))", Pattern.CASE_INSENSITIVE);
    private static Pattern regexMacOS = Pattern.compile("(?<!(iPad|iPod|iPhone).{1,100})(\\bMac\\s?OS(\\s*)([A-Z]*)(\\s*)((\\d*[\\._]\\d*)|(\\d*)))(?!(.+(iPad|iPod|iPhone)))", Pattern.CASE_INSENSITIVE);
    private static Pattern regexIOS = Pattern.compile("(ipad|ipod|iphone)", Pattern.CASE_INSENSITIVE);
    private static Pattern regexAndroid = Pattern.compile("Android(\\s*)((\\d*\\.\\d*)|(\\d*))",Pattern.CASE_INSENSITIVE);
    private static Pattern regexWindowsCE = Pattern.compile("windows ce", Pattern.CASE_INSENSITIVE);
    private static Pattern regexWindowsPhone = Pattern.compile("windows phone",Pattern.CASE_INSENSITIVE);
	
	private static HashMap<OS,Pattern> OperationSystemRegexMap=null;
	static
	{
		OperationSystemRegexMap=new HashMap<OS,Pattern>();
		OperationSystemRegexMap.put(OS.Windows, regexWindows);
		OperationSystemRegexMap.put(OS.Linux, regexLinux); 
		OperationSystemRegexMap.put(OS.MacOS, regexMacOS); 
		OperationSystemRegexMap.put(OS.IOS, regexIOS); // ipad,ipod,iphone不属于操作系统范畴 
		OperationSystemRegexMap.put(OS.Android, regexAndroid);
		OperationSystemRegexMap.put(OS.WindowsCE, regexWindowsCE); 
		OperationSystemRegexMap.put(OS.WindowsPhone, regexWindowsPhone); 
	}
	
	/**
	 * 返回操作系统附带版本
	 * 
	 * @param userAgent
	 * @return
	 * 		windows nt x.x / android x.x / ios / max os x x_x / linux / windows phone
	 */
    public static String getOS(String userAgent)
    {
    	if(!StringUtil.isNullOrEmpty(userAgent))
		{
    		userAgent=userAgent.toLowerCase();
	    	String ret ="";
	    	for(Entry<OS,Pattern> item : OperationSystemRegexMap.entrySet())
			{
	    		ret=getOS(userAgent,item.getValue());
				if (!StringUtil.isNullOrEmpty(ret))
				{
					if(OS.IOS.equals(item.getKey())) // 如果是ipad,ipod,iphone，则直接返回"IOS"
					{
						return OS.IOS.name().toLowerCase();
					}
		            return ret;
				}
			}
		}
    	
    	return "";

    }
    
    private static String getOS(String userAgent,Pattern regex)
    {
        Matcher name = regex.matcher(userAgent);
        if (name.find())
        {
            return name.group();
        }
        return null;
    }

}
