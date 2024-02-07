package org.poo.cb;

import java.util.HashMap;

public class ListUserCommand implements Command{
    private final String email;
    private final HashMap<String, User> users;
    ListUserCommand(String email, HashMap<String, User> users) {
        this.email = email;
        this.users = users;
    }
    public void execute() {
        if(!users.containsKey(email)) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }
        User userList = users.get(email);
        String toPrint = userList.toString();
        System.out.println(toPrint);
    }
}
