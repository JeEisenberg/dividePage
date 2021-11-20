package com.gavin.dao.FilmImp;

import org.apache.log4j.Logger;
import util.UtilTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;


public class ConnectionPool {
    //准备连接用的
    private static String DRIVER ;
   private static String URL ;
    private static String USER;
    private static String PASSWORD ;
    //准备一个池子用于盛放连接
    private static LinkedList<Connection> pool;
    //初始化大小
    private static int initSize ;
    //限制池子的最大大小
    private static int maxSize ;
    //加载该连接池类信息时就初始化了pool,pool中存放了5个连接实例
    private static Logger logger=null;

    static {
       logger= Logger.getLogger(ConnectionPool.class);
     UtilTool util= new UtilTool("/jdbc.properties");
       DRIVER = util.getProperties("DRIVER");
       URL = util.getProperties("URL");
        USER = util.getProperties("USER");
       PASSWORD= util.getProperties("PASSWORD");
       initSize=Integer.parseInt(util.getProperties("iniSize"));
        maxSize=Integer.parseInt(util.getProperties("maxSize"));
        //加载驱动
        try {

            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
           logger.fatal("驱动加载失败",e);

        }
        //开辟空间用于初始化连接池
       pool = new LinkedList<Connection>();
       //往池子中添加5个连接实例
       for (int i = 0; i < initSize; i++) {
       Connection connection = initConnection();
        pool.addLast(connection);
            }
        }
        //初始化一个connection;
public static Connection initConnection(){
Connection connection = null;
    try {
        connection=DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
        logger.error("初始化连接失败",e);
    }
return connection;

}
    //用于获得连接的方法
    public static Connection getConnection() {
        //怎么获得呢?
        //当连接池中有连接的时候,直接从里面取用
        Connection connection;
        if (pool.size() != 0) {
            connection = pool.removeFirst();
            logger.info("连接池中--"+connection.hashCode()+"被取走了");


        } else {//当连接池中没有的时候,创建一个
             connection = initConnection();
            logger.info("连接池已空--"+connection.hashCode()+"被创建了");


        }
        return connection;
    }

    //用户还回去的方法
    public static void returnConnection(Connection connection){

//什么时候往回还,要求池子内少于五个的时候我才接收
        //检测
        if(null!=connection) {//不为空

                try {//检测
                    if (!connection.isClosed()) {//连接未关闭的话
                        //检测
                        //如果池子中少于10个则可以添加进去--始终保证最大化缓冲池
                        if (pool.size() <= maxSize) {
                            //向池中增加该连接
                            connection.setAutoCommit(true);
                            pool.addLast(connection);//
                            logger.info("归还的连接"+connection.hashCode()+"符合要求,归还成功");

                        }else{//如果归还后池子中连接数大于10,则将该链接关闭
                            logger.info("池子中已满"+connection.hashCode()+"--将被关闭");

                            connection.close();
                            logger.info("池子中已满"+connection.hashCode()+"--被关闭");
                        }
                    }else{//连接关闭
                        logger.warn("归还的连接不能使用,归还失败");

                    }
                } catch (SQLException e) {
                    logger.warn("连接关闭失败");
                }

        }else{
            logger.warn("连接不能使用,归还失败");

        }

    }

    /*public static void main(String[] args) {
        Connection connection = getConnection();
        Connection connection1 = getConnection();
        try {
            connection1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getConnection();
        getConnection();
        getConnection();
        getConnection();
        returnConnection(connection);
        returnConnection(null);
        returnConnection(connection1);
    }*/
}
