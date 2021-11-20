package com.gavin.controller;

import com.gavin.dao.FilmImp.FilmDaoImp;
import com.gavin.pojo.Film;
import com.gavin.pojo.PageBean;
import service.FilmService;
import service.imp.FilmServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ShowFilmController.do")
public class ShowFilmController extends HttpServlet {
    private FilmDaoImp filmImp = new FilmDaoImp();
private FilmService filmService= new FilmServiceImp();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         接收数据
        String currentPage1 = req.getParameter("currentPage");
        String pageSize1 = req.getParameter("pageSize");

//        如果不带页码数,则赋予一初始值;
        int currentPage ;
        //转换成需要的类型
        //页码数
        try {
            /*如果有异常,则默认值为1*/
            currentPage=  Integer.parseInt(currentPage1);
        } catch (NumberFormatException e) {
            currentPage=1;
        }
        //页大小
//        如果没有页大小参数
        int pageSize ;

        try {
            pageSize =  Integer.parseInt(pageSize1);
        } catch (NumberFormatException e) {
            pageSize=10;
        }

        String filmName = req.getParameter("filmName");
        String filmDesc = req.getParameter("filmDesc");

//        业务处理---查找数据
        PageBean<Film> pageBean = filmService.findByPage(filmName,filmDesc,currentPage, pageSize);

//将数据放入请求域
        req.setAttribute("pageBean", pageBean);
req.setAttribute("filmName",filmName);
req.setAttribute("filmDesc",filmDesc);

//        响应数据,页面跳转
        req.getRequestDispatcher("showFilm.jsp").forward(req, resp);


    }

}
