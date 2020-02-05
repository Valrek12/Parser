package com.opencard.DbConnection;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaRepository extends CrudRepository<TableCategories, Integer> {
    List<TableCategories> findById(int id);
}
