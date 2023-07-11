package org.example.dao;

import org.example.model.Human;

import javax.swing.event.ListDataEvent;
import java.util.List;

public interface Dao<E, I> {
    // CRUDE - Create Read Delete Edit

    E findById(I id);

    List<E> findAll();

    E save(E entity);

    void delete(I id);

    E update(E entity);
}
