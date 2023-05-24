package korchagin.controller;//package korchagin.controller;
//
//import korchagin.dao.AlbumDao;
//import korchagin.model.Album;
//import korchagin.model.MusicRecording;
//import korchagin.reflection.Component;
//import korchagin.reflection.DependencyInjection;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import javax.servlet.Servlet;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.Optional;
//
//
//@WebServlet("/album")
//@Component
//public class AlbumController extends HttpServlet {
//
//    @DependencyInjection
//    private AlbumDao dao;
//
//    public AlbumDao getDao() {
//        return dao;
//    }
//
////    public Optional<Album> get(Long albumId) {
////        return this.dao.get(albumId);
////    }
////
////    public void put(Album album) {
////        this.dao.put(album);
////    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        List<Album> albums = dao.getAll().get();
//
//        PrintWriter out = response.getWriter();
//        String json = new JSONArray(albums).toString();
//        out.print(json);
//        out.flush();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//
//        // Получаем данные из запроса
//        BufferedReader reader = request.getReader();
//        String name = request.getParameter("name");
//        int year = Integer.parseInt(request.getParameter("year"));
//        Album album = new Album(name, year);
//
//        dao.put(album);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(new JSONObject(album).toString());
//
//    }
//
//
//}

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import korchagin.dao.AlbumDao;
import korchagin.model.Album;
import korchagin.reflection.Component;
import korchagin.reflection.DependencyInjection;
import org.json.JSONArray;
import org.json.JSONObject;


import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
@WebServlet("/album")
public class AlbumController extends HttpServlet {

    @DependencyInjection
    private AlbumDao dao;

    public AlbumDao getDao() {
        return dao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<Album> albums = dao.getAll().get();

        PrintWriter out = response.getWriter();
        String json = new JSONArray(albums).toString();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // Получаем данные из запроса
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        Album album = new Album(name, year);

        dao.put(album);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new JSONObject(album).toString());

    }
}
