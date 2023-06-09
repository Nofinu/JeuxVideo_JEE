package com.example.demo.service;

import com.example.demo.entity.Jeux;
import com.example.demo.repository.JeuxRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;

public class ServiceJeux {
    private SessionFactory _sessionFactory;
    private Session session;
    private JeuxRepository jeuxRepository;

    public ServiceJeux(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    public boolean createJeux (String name, float score, LocalDate releaseDate, String review, String type){
        session = _sessionFactory.openSession();
        boolean result = false;
        Jeux jeux = new Jeux(name,score,releaseDate,review,type);
        try{
            session.beginTransaction();
            jeuxRepository = new JeuxRepository(session);
            jeuxRepository.create(jeux);
            session.getTransaction().commit();
            result = true;
        }catch(Exception e){
            try{
                session.getTransaction().rollback();
            }catch (Exception exc){

            }
        }finally {
            session.close();
        }
        return result;
    }

    public boolean deleteJeux(Jeux jeux){
        session = _sessionFactory.openSession();
        boolean result = false;

        try{
            session.beginTransaction();
            jeuxRepository = new JeuxRepository(session);
            jeuxRepository.delete(jeux);
            session.getTransaction().commit();
            result = true;
        }catch (Exception e){
            try{
                session.getTransaction().rollback();
            }catch (Exception exc){

            }
        }finally {
            session.close();
        }
        return  result;
    }

    public boolean updateJeux (int id, String name, float score, LocalDate releaseDate, String review, String type){
        session = _sessionFactory.openSession();
        boolean result = false;
        Jeux jeux = new Jeux(name,score,releaseDate,review,type);
        jeux.setId(id);

        try{
            session.beginTransaction();
            jeuxRepository = new JeuxRepository(session);
            jeuxRepository.update(jeux);
            session.getTransaction().commit();
            result = true;
        }catch (Exception e){
            try{
                session.getTransaction().rollback();
            }catch (Exception exc){

            }
        }finally {
            session.close();
        }
        return  result;
    }

    public Jeux findById (int id){
        Jeux jeu = null;
        session = _sessionFactory.openSession();
        jeuxRepository = new JeuxRepository(session);
        try{
            jeu = jeuxRepository.findById(id);
        }catch (Exception e){

        }finally {
            session.close();
        }
        return jeu;
    }

    public List<Jeux> findAll (){
        List<Jeux> jeux = null;
        session = _sessionFactory.openSession();
        jeuxRepository = new JeuxRepository(session);
        try{
            jeux = jeuxRepository.findAll();
        }catch (Exception e){

        }finally {
            session.close();
        }
        return jeux;
    }
}
