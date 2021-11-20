package com.gavin.dao.FilmImp;


import com.gavin.dao.FilmDao;
import com.gavin.pojo.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmDaoImp extends BaseDao implements FilmDao {


    static List<Film> list = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

   /* @Override
    public List<Film> findAll() {
        try {
            String SQL = "select * from film;";
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            if (null != resultSet) {
                while (resultSet.next()) {
                    Integer film_id = resultSet.getInt("film_id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    Date release_year = resultSet.getDate("release_year");
                    Integer rental_duration = resultSet.getInt("rental_duration");
                    Double rental_rate = resultSet.getDouble("rental_rate");
                    Integer length = resultSet.getInt("length");
                    Double replacement_cost = resultSet.getDouble("replacement_cost");
                    String rating = resultSet.getString("rating");
                    String special_feature = resultSet.getString("special_feature");

                    Film film = new Film(film_id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_feature);
                    list.add(film);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeLink(resultSet, preparedStatement, connection);
        }
        return list;
    }
*/

    /**
     * @param currentPage 当前页
     * @param pageSize    页大小
     * @return
     */
    @Override
    public List findByPage(String filmName, String filmDesc, int currentPage, int pageSize) {

        StringBuffer sql = new StringBuffer("select * from film  where 2=2 ");//limit ?,?;
        if (null != filmName&&!"".equals(filmName) ){
            sql.append(" and title like ?");
        }
        if (null != filmDesc&&!"".equals(filmDesc)) {
            sql.append(" and description like ? ");
        }
        sql.append(" limit ?,?");
        System.out.println(sql.toString());
        List list;
//如果都不为空

        if((null != filmName&&!"".equals(filmName))&&(null != filmDesc&&!"".equals(filmDesc))){
           list = baseQuery(Film.class, sql.toString(), "%"+filmName+"%","%"+filmDesc+"%",(currentPage - 1) * pageSize, pageSize);


            //如果filmName不为空而filmDesc为空
        }else if( (null != filmName&&!"".equals(filmName))&&(null == filmDesc||"".equals(filmDesc))){
            filmName="%"+filmName+"%";

           list = baseQuery(Film.class, sql.toString(), "%"+filmName+"%",(currentPage - 1) * pageSize, pageSize);

            //如果filmName为空而filmDesc不为空
        }else if( (null == filmName||"".equals(filmName))&&(null != filmDesc&&!"".equals(filmDesc))){
           list = baseQuery(Film.class, sql.toString(), "%"+filmDesc+"%",(currentPage - 1) * pageSize, pageSize);
            filmDesc="%"+filmDesc+"%";

            //如果都为空
        }else {
            list = baseQuery(Film.class, sql.toString(), (currentPage - 1) * pageSize, pageSize);
        }

        //从哪条记录开始
        //页大小
        return list;
    }

    @Override
    public Integer findTotalRecords(String filmName, String filmDesc) {
     /*   String sql = "select count(?) from film  where 2=2";*/
        StringBuffer sql = new StringBuffer("select count(1) from film  where 2=2 ");//limit ?,?;
        if (null != filmName&&!"".equals(filmName) ){
            sql.append(" and title like ? ");
        }
        if (null != filmDesc&&!"".equals(filmDesc)) {
            sql.append("  and description like ? ");
        }
        System.out.println(sql.toString());

        Integer TotalRecords;
//如果都不为空
        if((null != filmName&&!"".equals(filmName))&&(null != filmDesc&&!"".equals(filmDesc))){
            filmName="%"+filmName+"%";
            filmDesc="%"+filmDesc+"%";
            TotalRecords = baseQueryInt(sql.toString(), filmName, filmDesc);
            //如果filmName不为空而filmDesc为空
        }else if( (null != filmName&&!"".equals(filmName))&&(null == filmDesc||"".equals(filmDesc))){

            TotalRecords = baseQueryInt(sql.toString(), "%"+filmName+"%");
            //如果filmName为空而filmDesc不为空
        }else if( (null == filmName||"".equals(filmName))&&(null != filmDesc&&!"".equals(filmDesc))){
            filmDesc="%"+filmDesc+"%";
            TotalRecords = baseQueryInt(sql.toString(),  "%"+filmDesc+"%");
            //如果都为空
        }else {
            TotalRecords = baseQueryInt(sql.toString());
        }
        return TotalRecords;
    }

    public static void closeLink(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {

        try {
            if (null != resultSet)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (null != preparedStatement)
                preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (null != connection)
            ConnectionPool.returnConnection(connection);


    }


}
