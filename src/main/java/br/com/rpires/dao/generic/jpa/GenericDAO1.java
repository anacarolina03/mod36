package br.com.rpires.dao.generic.jpa;

import java.io.Serializable;

public class GenericDAO1 <T extends Persistente, E extends Serializable> extends GenericJpaDAO<T, E> {
    public GenericDAO1(Class<T> persistenteClass) {
        super(persistenteClass,"Banco1");
    }
}

