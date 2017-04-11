package com.upsmart.server.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamUtils {

	static Logger log = LoggerFactory.getLogger(StreamUtils.class);
	
    private StreamUtils() {}

    /**
     * Copy data from {@code InputStream} to {@code OutputStream}
     *
     * @param from src stream, can be null
     * @param to destinate stream, can be null
     */
    public static void copy(InputStream from, OutputStream to, byte[] buf)
            throws IOException {
        if(buf == null || from == null || to == null)
            return;
        int count = 0;
        int bufSize = buf.length;
        while((count = from.read(buf, 0, bufSize)) != -1)
            to.write(buf, 0, count);
    }

    /**
     * Copy data from {@code InputStream} to {@code OutputStream}
     *
     * @param from src stream, can be null
     * @param to destinate stream, can be null
     */
    public static void copy(InputStream from, OutputStream to, int bufSize)
            throws IOException {
        if(from == null || to == null)
            return;
        byte[] buf = new byte[bufSize];
        copy(from, to , buf);
    }
    
    
    public static String formatStackTrace(Throwable throwable) 
    {  
        if(throwable==null)
        {
        	return "";
        }  
        
        String stackTrace = null;
        try 
        {  
            Writer writer = new StringWriter();  
            PrintWriter printWriter = new PrintWriter(writer);  
            throwable.printStackTrace(printWriter);       
            printWriter.flush();  
            writer.flush();  
            stackTrace = writer.toString();  
            printWriter.close();              
            writer.close();  
        }
        catch (Exception e) 
        {  
        	 log.error(e.getMessage(), e);
        }
        return stackTrace;  
    }
}
