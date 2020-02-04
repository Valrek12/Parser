package com.opencard.DbConnection;

import org.springframework.data.repository.CrudRepository;

public interface JpaRepository extends CrudRepository<TableCategories, Integer> {
    TableCategories findById(int id);
}
