package org.fox.service;

import org.fox.dao.ClientDaoService;
import org.fox.dto.Client;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientDaoService dao;

    public ClientServiceImpl(ClientDaoService dao) {
        this.dao = dao;
    }

    @Override
    public long create(String name) {

        if (name == null || name.trim().length() < 2) {
            throw new IllegalArgumentException("Invalid name");
        }

        return dao.create(name);
    }

    @Override
    public String getById(long id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id");
        }

        return dao.getById(id);
    }

    @Override
    public void setName(long id, String name) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id");
        }

        if (name == null || name.trim().length() < 2) {
            throw new IllegalArgumentException("Invalid name");
        }

        dao.setName(id, name);
    }

    @Override
    public void deleteById(long id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id");
        }

        dao.deleteById(id);
    }

    @Override
    public List<Client> listAll() {
        return dao.listAll();
    }
}