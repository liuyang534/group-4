package work2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigUtil {
    private static Properties properties;
    private static Config config;

    static {
        String configFile= "config.properties"; // 配置文件
        properties = new Properties();
        InputStream is = Config_mysql.class.getClassLoader().getResourceAsStream(configFile);
        try{
            properties.load(new InputStreamReader(is, "UTF-8"));
            is.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        if (properties != null)
            return properties.getProperty(key);
        else
            return null;
    }

    public static work2.Config getValues(){

        if (properties != null) {
            if (config == null) {

                config = new Config();
                //  properties.getProperty(key);
                config.setColumnNameId(properties.getProperty("column.name.id"));
                config.setColumnNameName(properties.getProperty("column.name.name"));
                config.setColumnNameHp(properties.getProperty("column.name.hp"));
                config.setColumnNameDamage(properties.getProperty("column.name.damage"));
                config.setFieldNameId(properties.getProperty("field.name.id"));
                config.setFieldNameName(properties.getProperty("field.name.name"));
                config.setFieldNameHp(properties.getProperty("field.name.hp"));
                config.setFieldNameDamage(properties.getProperty("field.name.damage"));
                config.setPrimaryKey(properties.getProperty("primary.Key"));
            }
            return  config;

        } else {
            return null;
        }

    }

}