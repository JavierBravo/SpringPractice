package com.CursoJavaFullstack.lucasMoy.dao;

import com.CursoJavaFullstack.lucasMoy.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImp implements UserDAO{
    @PersistenceContext
    EntityManager entityManager;

    @Override // (GET /api/users) OBTENER LISTADO COMPLETO
    public List<User> getUserList() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) { return user; }
        return null;
    }


    @Override // (DELETE /api/users/{id}) ELIMINAR USUARIO
    public void deleteUser(Long id){
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override // (POST /api/users) ALTA DE USUARIO
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override // (POST /api/login) INICIAR SESIÓN
    public User getUserByLogin(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> list = entityManager.createQuery(query)
                                        .setParameter("email", user.getEmail())
                                        .getResultList();

        if (list.isEmpty()) {return null;}

        String hashedPassword = list.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(hashedPassword, user.getPassword())){
            return list.get(0);
        }
        return null;
    }

    @Override // (PUT /api/users/{id}) MODIFICAR USUARIO
    public void editUser(Long id, User newDataUser) {
        User user = entityManager.find(User.class, id);
        user.setNombre(newDataUser.getNombre());
        user.setApellido(newDataUser.getApellido());
        user.setTelefono(newDataUser.getTelefono());
        entityManager.merge(user);
    }
}
