package com.example.demo.servlet;

import java.io.*;
import java.util.List;

import com.example.demo.entity.Jeux;
import com.example.demo.service.ServiceJeux;
import com.example.demo.util.Definition;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@WebServlet(name = "home", value = "")
public class HomeServlet extends HttpServlet {

    private ServiceJeux serviceJeux;
    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;
    public void init() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        serviceJeux = new ServiceJeux(sessionFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Jeux> jeux = serviceJeux.findAll();
        request.setAttribute("jeux",jeux);
        request.getRequestDispatcher(Definition.PATH_VIEW+"homePage.jsp").forward(request,response);
    }

    public void destroy() {
    }
}