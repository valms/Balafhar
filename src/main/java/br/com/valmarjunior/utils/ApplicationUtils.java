package main.java.br.com.valmarjunior.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger( ApplicationUtils.class );
	
	private ApplicationUtils() {
	}
	
	public static Properties getApplicationProperties() {
		Properties properties = new Properties();
		InputStream inputStream;
		
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
			String propertiesfilename = "app.properties";
			inputStream = classLoader.getResourceAsStream( propertiesfilename );
			
			if (inputStream == null) {
				throw new IOException( "Arquivo não encontrado: " + propertiesfilename );
			}
			properties.load( inputStream );
			
			
		} catch (IOException e) {
			LOGGER.error( e.getMessage() );
		}
		
		return properties;
	}
	
	
	
}
