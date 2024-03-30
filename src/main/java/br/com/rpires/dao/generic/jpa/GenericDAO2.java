package br.com.rpires.dao.generic.jpa;

import java.io.Serializable;

public class GenericDAO2 <T extends Persistente, E extends Serializable> extends GenericJpaDAO<T, E> {
    public GenericDAO2(Class<T> persistenteClass) {
        super(persistenteClass,"Banco2");
    }
}
