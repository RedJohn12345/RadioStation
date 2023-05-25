package korchagin.controller;

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
import java.util.Map;

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
        List<Album> albums = dao.getAll().get();

        PrintWriter out = response.getWriter();
        String json = new JSONArray(albums).toString();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuffer buffer = new StringBuffer();
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            buffer.append(inputLine);
        }
        JSONObject jsonObject = new JSONObject(buffer.toString());
        Map<String, Object> map = jsonObject.toMap();
        Album album = new Album((String) map.get("name"), Integer.parseInt((String) map.get("year")));
        dao.put(album);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new JSONObject(album).toString());

    }
}
