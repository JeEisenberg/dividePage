package com.gavin.dao;


import com.gavin.pojo.Film;
import com.gavin.pojo.PageBean;

import java.util.List;

public interface FilmDao {
 /*   List<Film> findAll() ;
*/
    List<Film> findByPage(String filmName,String filmDesc,int currentPage, int pageSize);
Integer findTotalRecords(String filmName,String filmDesc);


}
