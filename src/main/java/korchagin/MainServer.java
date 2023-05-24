package korchagin;
import jakarta.servlet.Servlet;
import korchagin.controller.AlbumController;
import korchagin.controller.MusicRecordingController;
import korchagin.controller.PersonController;
import korchagin.reflection.ApplicationContext;
import korchagin.reflection.DependencyInjection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainServer {


    @DependencyInjection
    private static AlbumController albumController;

    @DependencyInjection
    private static MusicRecordingController musicRecordingController;

    @DependencyInjection
    private static PersonController personController;

    private static final String URL = "jdbc:postgresql://localhost:9100/radiostation";
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "root";

    static {
        try {
            ApplicationContext.injectDependencies();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        personController.getDao().setConnection(connection);
        albumController.getDao().setConnection(connection);
        musicRecordingController.getDao().setConnection(connection);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setContextPath("/api");

        servletContextHandler.addServlet(new ServletHolder(albumController), "/album/*");

        server.setHandler(servletContextHandler);

        server.start();
        server.join();
    }
}
