/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.cellicereda.meteocal.businesslogic;

import it.polimi.cellicereda.meteocal.entities.Group;
import it.polimi.cellicereda.meteocal.entities.User;
import java.security.Principal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author stefano
 */
@Stateless
public class UserProfileManager {

    @PersistenceContext
    EntityManager em;

    @Inject
    Principal principal;

    /**
     * Save into the DB the given user
     *
     * @param user The user to be saved
     */
    public void save(User user) {
        user.setGroupName(Group.USERS);
        em.persist(user);
    }

    /**
     * Delete from the DB the logged user
     */
    public void unregister() {
        em.remove(getLoggedUser());
    }

    /**
     * @return the logged user
     */
    public User getLoggedUser() {
        return em.find(User.class, principal.getName());
    }

    /**
     * @return The user that has the given email
     * @param email The email to search for
     */
    public User getByEmail(String email) {
        return em.find(User.class, email);
    }

    public List<User> getByName(String name) {
        return em.createNamedQuery("User.findByName").setParameter("name", name).getResultList();
    }

    public List<User> getByUsername(String username) {
        return em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
    }

    public List<User> getBySurname(String surname) {
        return em.createNamedQuery("User.findBySurname").setParameter("surname", surname).getResultList();
    }

    public void changeUsername(User user, String newUsername) {
        User u = em.find(User.class, user.getEmail());
        u.setUsername(newUsername);
    }

    public void changeName(User user, String newName) {
        User u = em.find(User.class, user.getEmail());
        u.setName(newName);
    }

    public void changeSurname(User user, String newSurname) {
        User u = em.find(User.class, user.getEmail());
        u.setSurname(newSurname);
    }

    public void changePassword(User user, String newPassword) {
        User u = em.find(User.class, user.getEmail());
        u.setPassword(newPassword);
    }

    public void changePublicCalendar(User user, boolean newPublicCalendar) {
        User u = em.find(User.class, user.getEmail());
        u.setPublicCalendar(newPublicCalendar);
    }
}
