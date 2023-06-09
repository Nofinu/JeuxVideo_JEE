package com.example.demo.servlet;

import com.example.demo.entity.Jeux;
import com.example.demo.service.ServiceJeux;
import com.example.demo.util.Definition;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "jeux",value = "/jeux")
public class JeuxServlet extends HttpServlet {
    private ServiceJeux serviceJeux;
    private SessionFactory sessionFactory;
    private StandardServiceRegistry registry;
    public void init (){
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        serviceJeux = new ServiceJeux(sessionFactory);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(request.getParameter("type")!= null){
                String type = request.getParameter("type");
                switch (type){
                    case "add":
                        request.setAttribute("t",type);
                        request.getRequestDispatcher(Definition.PATH_VIEW+"formJeux.jsp").forward(request,response);
                        break;
                    case "edit":
                        if(request.getParameter("id")!=null){
                            int id = Integer.parseInt(request.getParameter("id"));
                            Jeux jeu = serviceJeux.findById(id);
                            if(jeu != null){
                                HttpSession session = request.getSession();
                                session.setAttribute("jeu",jeu);
                                request.setAttribute("t",type);
                                request.setAttribute("jeu", jeu);
                                request.getRequestDispatcher(Definition.PATH_VIEW+"formJeux.jsp").forward(request,response);
                            }
                        }

                        break;
                }
            }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("type")!=null){
            String type = request.getParameter("type");
            String name;
            float score;
            String review;
            LocalDate date;
            String types;
            switch (type){
                case "add":
                    name = request.getParameter("name");
                    score = Float.parseFloat(request.getParameter("score"));
                    date = LocalDate.parse(request.getParameter("date"));
                    review = request.getParameter("review");
                    types = request.getParameter("types");

                    if(serviceJeux.createJeux(name,score,date,review,types)){
                        response.sendRedirect("");
                    }else{
                        request.getRequestDispatcher(Definition.PATH_VIEW+"error.jsp");
                    }
                    break;
                case "edit":
                    HttpSession session =request.getSession();
                    Jeux jeu = (Jeux) session.getAttribute("jeu");
                    name = request.getParameter("name");
                    score = Float.parseFloat(request.getParameter("score"));
                    date = LocalDate.parse(request.getParameter("date"));
                    review = request.getParameter("review");
                    types = request.getParameter("types");
                    if(serviceJeux.updateJeux(jeu.getId(), name,score,date,review,types)){
                        response.sendRedirect("");
                    }else{
                        request.getRequestDispatcher(Definition.PATH_VIEW+"error.jsp");
                    }
                    break;
            }
        }
    }

    public void destroy (){
        sessionFactory.close();
    }
}
