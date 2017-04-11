package com.upsmart.server.common.browser;

public enum Browser
{
	 OTHER(0),
     IE(1),
     FIREFOX(2),
     CHROME(3),
     OPERA(4),
     SAFARI(5),
     MAXTHON(6),
     TENCENT_TRAVERLER(7),
     SOGOU_BROWSER(8),
     THEWORLD_BROWSER(9),
     BROWSER_360(10);
	 
	 private int value=0;
	 private  Browser(int value)
	 {
		 this.value=value;
	 }
	 
	 public int getValue()
	 {
		 return this.value;
	 }
}
