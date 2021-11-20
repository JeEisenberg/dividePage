package com.gavin.dao.FilmImp;


import com.gavin.pojo.Film;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Gavin
 * @Description: Micro Message:GavinYu
 */
public abstract class BaseDao {

    public int baseUpdate(String sql,Object ... args){
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
                int rows=0;
        try{
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            for (int i = 0; i <args.length ; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            //执行CURD
            rows =preparedStatement.executeUpdate();// 这里不需要再传入SQL语句
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != preparedStatement){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ConnectionPool.returnConnection(connection);
        }
        return rows;
    }
    public List baseQuery(Class clazz, String sql, Object ... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List list=new ArrayList<>() ;
        try{
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);//这里预编译SQL语句"select * from film limit 0,10;"
            //设置参数
            for (int i = 0; i <args.length ; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            //System.out.println(sql);
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句----为什么resultset 为null返回size=0
            connection.commit();
            // 根据字节码获取所有 的属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);// 设置属性可以 访问
            }

            while(resultSet.next()){
                // 通过反射创建对象
                Object obj = clazz.newInstance();//默认在通过反射调用对象的空参构造方法
                for (Field field : fields) {// 临时用Field设置属性
                    String fieldName = field.getName();// 得到每一个的属性名 .... ...
                    Object data = resultSet.getObject(fieldName);
                    field.set(obj,data);
                }
                list.add(obj);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

            FilmDaoImp.closeLink(resultSet,preparedStatement,connection);
            return list;

    }


    public Integer baseQueryInt(String sql,Object ... args) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
       Integer count=0;
        try{
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            for (int i = 0; i <args.length ; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            // 根据字节码获取所有 的属性
            while(resultSet.next()){
                // 通过反射创建对象
                count = resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            FilmDaoImp.closeLink(resultSet,preparedStatement,connection);
            return count;
        }

    }
}

