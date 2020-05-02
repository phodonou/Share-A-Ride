package com.mycompany.app.repositories;

import java.util.*;
import com.mycompany.app.models.*;

import org.junit.Test;

public class UserRepositoryTest {

    @Test
    public void createAccountSuccessful() {
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();
        int id = userRepository.createAccount(mockUser);
        assert (id > 0);
    }

    @Test
    public void confirmAccountSuccessful() {
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();
        userRepository.createAccount(mockUser);

        boolean confirmed = userRepository.confirmAccount(mockUser.getAid());
        assert (confirmed);
    }

    @Test
    public void confirmAccountSuccessful1() {
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();

        boolean confirmed = userRepository.confirmAccount(mockUser.getAid());
        assert (!confirmed);
    }

    @Test
    public void deleteAccountSuccessful() {
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();
        userRepository.createAccount(mockUser);

        boolean deleted = userRepository.deleteAccount(mockUser.getAid());

        assert (deleted);
    }

    @Test
    public void deleteAccountSuccessful1() {
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();

        boolean deleted = userRepository.deleteAccount(mockUser.getAid());

        assert (!deleted);
    }

    @Test
    public void updateAccountSuccessful() {
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();
        userRepository.createAccount(mockUser);

        boolean created = userRepository.updateAccount(mockUser, mockUser.getAid());
        assert (created);
    }

    @Test
    public void updateAccountSuccessful1() {
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();

        boolean created = userRepository.updateAccount(mockUser, mockUser.getAid());
        assert (!created);
    }

    @Test
    public void accounts(){
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();
        userRepository.createAccount(mockUser);

        List<Map<String, Object>> accounts = userRepository.accounts(null);

        assert(!accounts.isEmpty());
    }

    @Test
    public void accounts1(){
        UserRepository userRepository = new UserRepository();
        User mockUser = new User("f", "", "", "", true);
        userRepository.createAccount(mockUser);

        List<Map<String, Object>> accounts = userRepository.accounts("f");

        assert(!accounts.isEmpty());
    }

    @Test
    public void account(){
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();
        userRepository.createAccount(mockUser);

        Map<String, Object> account = userRepository.account(mockUser.getAid());

        assert(account != null);
    }

    // @Test
    // public void createRating(){
    //     UserRepository userRepository = new UserRepository();
    //     User mockUser = new User();
    //     userRepository.createAccount(mockUser);

    //     Rating mockRating = new Rating();

    //     int rid =  userRepository.createRating(mockRating, mockUser.getAid());

    //     assert(rid > 0);
    // }

    // @Test
    // public void getRating(){
    //     UserRepository userRepository = new UserRepository();
    //     User mockUser = new User("", "", "", "", true);
    //     userRepository.createAccount(mockUser);

    //     Rating mockRating = new Rating(1, mockUser.getAid(), 1, "");
    //     userRepository.createRating(mockRating, mockUser.getAid());

    //     Map<String, Object> rating=  userRepository.getRating(mockUser.getAid());

    //     assert(rating != null);
    // }

    @Test
    public void sendMessageNotification(){
        UserRepository userRepository = new UserRepository();
        User mockUser = new User();
        
        boolean created = userRepository.sendMessageNotification(mockUser);

        assert(created);
    }

    // @Test
    // public void avg(){
    //     UserRepository userRepository = new UserRepository();

    //     User mockUser = new User();
    //     userRepository.createAccount(mockUser);

    //     Rating mockRating = new Rating();

    //     userRepository.createRating(mockRating, mockUser.getAid());

    //     userRepository.calculateAvgRating(mockUser.getRatings());
    // }

}