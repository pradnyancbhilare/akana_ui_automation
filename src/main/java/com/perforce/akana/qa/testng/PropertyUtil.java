package com.perforce.akana.qa.testng;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;

import com.perforce.akana.qa.exception.TestCodeException;

public class PropertyUtil {
	
    private static final Logger logger = LogManager.getLogger(PropertyUtil.class);
    
    public static Object addSystemProperty(ITestContext context, String key) {
        Object value = System.getProperty(key);
        return addPropertyToContext(context, key, value, false);
    }
       
    public static Object addOptionalSystemProperty(ITestContext context, String key) {
        Object value = System.getProperty(key);
        return addPropertyToContext(context, key, value, true);
    }
    
    /**
     * Loads the system property with the given key into the provided test context if it exists, otherwise it loads the default
     * 
     * @param context
     * @param key
     * @param defaultValue
     */
    public static Object addOptionalSystemPropertyWithDefault(ITestContext context, String key, Object defaultValue) {
      Object value = System.getProperty(key);
      if (value == null) {
        value = defaultValue;
      }
      
      return addPropertyToContext(context, key, value, true);
    }

    public static Object addProperty(ITestContext context, String key, Object value) {
        return addPropertyToContext(context, key, value, false);
    }
    
    public static Object addPropertyWithDefault(ITestContext context, String key, Object value) {
      return addPropertyToContext(context, key, value, false);
    }
    
    private static Object addPropertyToContext(ITestContext context, String key, Object value, boolean optional) {
        if (value != null) {
            logger.debug(String.format("Setting value '%s' for property '%s'", value, key));
            context.setAttribute(key, value);
            return value;
        } else {
            if (optional) {
                logger.warn(String.format("Property is not set: '%s'", key));
                return null;
            } else {
                throw new TestCodeException(String.format("Required property is not set: '%s'", key));
            }
        }
    }

    public static Object addProperty(ITestContext context, String key) {
        return addSystemProperty(context, key);
    }
    
    public static Object addOptionalProperty(ITestContext context, String key) {
        return addOptionalSystemProperty(context, key);
    }
    
    public static void outputEnviromentVariables() {
        Map<String, String> env = System.getenv();
        StringBuilder builder = new StringBuilder().append("Environment Variables:\n");
        for (Entry<String, String> entry : env.entrySet()) {
            builder.append(String.format("%s :: %s\n", entry.getKey(), entry.getValue()));
        }
        
        logger.trace(builder.toString());
    }
    
    public static void outputProperties() {
        Properties props = System.getProperties();
        StringBuilder builder = new StringBuilder().append("System Properties:\n");
        for (Entry<Object, Object> entry : props.entrySet()) {
            builder.append(String.format("%s :: %s\n", entry.getKey(), entry.getValue()));
        }
        
        logger.trace(builder.toString());
    }

}
