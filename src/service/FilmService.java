package service;

import com.gavin.pojo.Film;
import com.gavin.pojo.PageBean;

public interface FilmService {
    PageBean<Film> findByPage(String filmName,String filmDesc,int currentPage, int pageSize);


}
