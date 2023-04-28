package br.com.sinqia.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    <T> void save(T dado);
}
