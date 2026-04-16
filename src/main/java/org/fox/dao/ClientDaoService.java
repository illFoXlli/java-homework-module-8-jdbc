package org.fox.dao;

import org.fox.dto.Client;
import java.util.List;

public interface ClientDaoService {

    long create(String name);

    String getById(long id);

    void setName(long id, String name);

    void deleteById(long id);

    List<Client> listAll();
}