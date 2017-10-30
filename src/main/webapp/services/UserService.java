/*
 * Software property of Acquisio. Copyright 2003-2017.
 */
package webapp.services;

import webapp.pojos.User;

/**
 * @author Macar Calancea (mcalancea@acquisio.com)
 */
public class UserService {

    public UserService() {
    }

    public User findOne(Long id){
        return new User(id, "my id is: " + id, "Mr");
    }
}
