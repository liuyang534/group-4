package work2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config_mysql {
    private static Config_mysql config_mysql;
    private static Properties properties;
    private Config_mysql(){
        String configFile= "dis_bak2.properties"; // 数据库配置文件
        properties = new Properties();
        InputStream is = Config_mysql.class.getClassLoader().getResourceAsStream(configFile);
        try{
            properties.load(is);
            is.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Config_mysql getInstance(){

        if(config_mysql == null){
            config_mysql=new Config_mysql();
        }
        return config_mysql;
    }

    // 通过配置文件Key的名称获取到Key的值。
    public String getString(String key){
        return properties.getProperty(key);
    }
}
