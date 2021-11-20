package service.imp;

import com.gavin.dao.FilmImp.FilmDaoImp;
import com.gavin.dao.FilmDao;
import com.gavin.pojo.Film;
import com.gavin.pojo.PageBean;
import service.FilmService;

import java.util.List;

public class FilmServiceImp implements FilmService {

    //private FilmService filmDao = new FilmServiceImp();


    //    查找数据并将分页数据封装
    @Override
    public PageBean<Film> findByPage(String filmName,String filmDesc,int currentPage, int pageSize) {
//        怎么查找?
//        通过filmDao接口中的方法
         FilmDao filmDao = new FilmDaoImp();
        ////查询 该页所有数据,比如第一页,10条数据
       List<Film> data = filmDao.findByPage( filmName, filmDesc,currentPage, pageSize);
        Integer TotalRecords = filmDao.findTotalRecords(filmName, filmDesc);
        //计算总页数
        Integer totalPage = TotalRecords % pageSize == 0 ? TotalRecords / pageSize : TotalRecords / pageSize + 1;
        //当前页
        //页大小
        PageBean<Film> pageBean = new PageBean<>(data, TotalRecords, pageSize, totalPage, currentPage);


        return pageBean;
    }
}
