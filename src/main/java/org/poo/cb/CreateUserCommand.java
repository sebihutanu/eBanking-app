package org.poo.cb;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateUserCommand implements Command{
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final HashMap<String, User> users;
    CreateUserCommand(String email, String firstName, String lastName, String address, HashMap<String, User> users) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.users = users;
    }
    public void execute() {
        if(users.containsKey(email)) {
            System.out.println("User with " + email + " already exists");
            return;
        }
        User user = new User(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.friends = new ArrayList<>();
        user.portfolio = new Portfolio();
        user.portfolio.accounts = new HashMap<>();
        user.portfolio.actions = new HashMap<>();
        users.put(email, user);
    }
}
