package map;

import java.sql.ResultSet;

public interface Mapable<T> {
    // phai tra ve mot list doi tuong cho vao tu ResultSet
    T mapRow(ResultSet rs);
}
