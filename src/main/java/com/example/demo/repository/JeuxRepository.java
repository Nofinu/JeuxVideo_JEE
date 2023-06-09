package com.example.demo.repository;

import com.example.demo.entity.Jeux;
import org.hibernate.Session;

import java.util.List;

public class JeuxRepository extends Repository<Jeux> {

    public JeuxRepository(Session _session) {
        super(_session);
    }

    @Override
    public Jeux findById(int id) {
        return _session.get(Jeux.class,id);
    }

    @Override
    public List<Jeux> findAll() {
        return _session.createQuery("from jeux", Jeux.class).list();
    }


}
