package com.study.onlineshop.dao;

import com.study.onlineshop.dao.jdbc.ConnectionProvider;
import com.study.onlineshop.entity.Product;

import javax.sql.DataSource;
import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    Product getById(int id);

    int add(Product product);

    void delete(int id);

    void update(Product product);

    void setDataSource(DataSource dataSource);

}
