package work2;

import java.sql.Connection;
import java.util.LinkedList;

public class MyDatabasePool {
    private LinkedList<Connection> connPool=new LinkedList<Connection>();//存放连接
    public MyDatabasePool(){
        for(int i=0;i<10;i++)//连接池中存放10个连接
        {
            this.connPool.addLast(this.CreateConnection());//每次添加到集合最后面
        }
    }
    public Connection CreateConnection(){//获得连接
        return DBLink.getConnection();
    }
    public Connection GetConnection(){
        return connPool.removeFirst();//取出最上面的一个连接
    }
    public void FreeConnection(Connection conn){//将用完后的连接放回到集合中
        this.connPool.addLast(conn);
    }
}
