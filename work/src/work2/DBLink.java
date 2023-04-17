package work2;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBLink {
    private static String driver;
    private static String url;
    private static String user;
    private static String pwd;
    private static MyDatabasePool connPool;//连接池类

    static {
        // 通过类加载器获得资源，并以流的方式进行操作
        InputStream is = DBLink.class.getClassLoader().getResourceAsStream("dis.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            url = properties.getProperty("mysql.url");
            driver = properties.getProperty("mysql.driver");
            user = properties.getProperty("mysql.user");
            pwd = properties.getProperty("mysql.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName(driver);//注册驱动
            connPool = new MyDatabasePool();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);//获得连接
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return conn;
    }
    public void DataBase(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);//获得连接
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void close(Connection conn) {
        if (conn != null)
            connPool.FreeConnection(conn); //将连接放回集合
    }

    public static void close(Statement stm) {
        try {
            if (stm != null)
                stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //查询
    public ResultSet Search(String sql,String str[]) {
        ResultSet rs=null;
        DataBase();
        try {
            PreparedStatement pst=getConnection().prepareStatement(sql);
            if (str!=null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i+1, str[i]);
                }
            }
            rs=pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    //增删修改
    public int AddU(String sql,String str[]) {
        int a=0;
        DataBase();
        try {
            PreparedStatement pst=getConnection().prepareStatement(sql);
            if (str!=null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i+1, str[i]);
                }
            }
            a=pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}