package korchagin.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import korchagin.dao.MusicRecordingDao;
import korchagin.dao.MusicRecordingDaoCSV;
import korchagin.dao.PersonDao;
import korchagin.model.Album;
import korchagin.model.MusicRecording;
import korchagin.model.enums.MusicGenre;
import korchagin.model.musician.Person;
import korchagin.reflection.Component;
import korchagin.reflection.DependencyInjection;
import korchagin.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Component
@WebServlet("/music_recording")
public class MusicRecordingController extends HttpServlet {

    @DependencyInjection
    private MusicRecordingDao dao;

    public MusicRecordingDao getDao() {
        return dao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        MusicRecording musicRecording = dao.get(id).get();
        PrintWriter out = response.getWriter();
        String json = new JSONObject(musicRecording).toString();
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

        Set<Person> performers = new HashSet<>();
        for (Object id: (List<Object>) map.get("performers")) {
            Person performer = new Person();
            performer.setIdentity(Long.parseLong((String) id));
            performers.add(performer);
        }

        Set<Person> authors = new HashSet<>();
        for (Object id: (List<Object>) map.get("authors")) {
            Person author = new Person();
            author.setIdentity(Long.parseLong((String) id));
            authors.add(author);
        }

        Album album = new Album();
        album.setIdentity(Long.parseLong((String) map.get("album_id")));

        MusicRecording musicRecording = new MusicRecording(MusicGenre.getByName((String) map.get("genre")),
                (String) map.get("name"),
                 authors, performers, album, Integer.parseInt((String) map.get("duration")));
        musicRecording.setRating(Integer.parseInt((String) map.get("rating")));

        dao.put(musicRecording);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new JSONObject(musicRecording).toString());

    }
}
