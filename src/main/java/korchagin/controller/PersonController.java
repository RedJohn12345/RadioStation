package korchagin.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import korchagin.dao.AlbumDao;
import korchagin.dao.PersonDao;
import korchagin.dao.PersonDaoCSV;
import korchagin.model.Album;
import korchagin.model.musician.Person;
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
import java.util.Optional;

@Component
@WebServlet("/person")
public class PersonController extends HttpServlet {
    @DependencyInjection
    private PersonDao dao;

    public PersonDao getDao() {
        return dao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Long id = Long.parseLong(request.getParameter("id"));
        Person person = dao.get(id).get();

        PrintWriter out = response.getWriter();
        String json = new JSONObject(person).toString();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        // Получаем данные из запроса
        BufferedReader reader = request.getReader();
        StringBuffer buffer = new StringBuffer();
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            buffer.append(inputLine);
        }
        JSONObject jsonObject = new JSONObject(buffer.toString());
        Map<String, Object> map = jsonObject.toMap();
        Person person = new Person((String) map.get("name"), (String) map.get("surname"), (String) map.get("nickname"));

        dao.put(person);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new JSONObject(person).toString());

    }
}
