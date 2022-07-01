package com.CursoJavaFullstack.lucasMoy.controllers;

import com.CursoJavaFullstack.lucasMoy.dao.UserDAO;
import com.CursoJavaFullstack.lucasMoy.models.User;
import com.CursoJavaFullstack.lucasMoy.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class UserController {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUserList(@RequestHeader(value="Authorization") String token){
        if (!validateToken(token)){ return null; }
        return userDao.getUserList();
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id, @RequestHeader(value="Authorization") String token){
        if (!validateToken(token)){ return null; }
        return userDao.getUserById(id);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDao.register(user);
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.PUT)
    public void editUser(@PathVariable Long id, @RequestBody User newDataUser,@RequestHeader(value="Authorization") String token){
        if (!validateToken(token)){ return; }
        userDao.editUser(id,newDataUser);
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id, @RequestHeader(value="Authorization") String token){
        if (!validateToken(token)){ return; }
        userDao.deleteUser(id);
    }

    private boolean validateToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }
}
