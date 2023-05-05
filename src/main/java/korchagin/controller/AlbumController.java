package korchagin.controller;

import korchagin.dao.AlbumDao;
import korchagin.model.Album;
import korchagin.reflection.Component;
import korchagin.reflection.DependencyInjection;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

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
        BufferedReader reader = request.getReader();
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        Album album = new Album(name, year);

        dao.put(album);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new JSONObject(album).toString());

    }


}
