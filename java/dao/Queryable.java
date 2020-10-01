package dao;

import map.Mapable;

import java.util.List;

public interface Queryable<T> {
    <T> List<T> query(String sql, Mapable<T> rowMapper, Object... parameters);
    // void update (String sql, Object... parameters);
    // Long insert (String sql, Object... parameters);
}
