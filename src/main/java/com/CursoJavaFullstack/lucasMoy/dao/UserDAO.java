package com.CursoJavaFullstack.lucasMoy.dao;
import com.CursoJavaFullstack.lucasMoy.models.User;
import java.util.List;
public interface UserDAO {
    List<User> getUserList();

    User getUserById(Long id);

    void deleteUser(Long id);

    void register(User user);

    User getUserByLogin(User user);

    void editUser(Long id, User newDataUser);
}
