package org.poo.cb;
import java.util.ArrayList;

public class User {
    public String email;
    public String firstName;
    public String lastName;
    public String address;
    public ArrayList<User> friends;
    public Portfolio portfolio;
    public boolean premium;
    User(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void adaugaPrieten(User prieten) {
        this.friends.add(prieten);
    }
    public String getFriendsEmails() {
        StringBuilder emails = new StringBuilder();
        for (User prieten : this.friends) {
            emails.append("\"").append(prieten.email).append("\"");
            if (this.friends.indexOf(prieten) != this.friends.size() - 1) {
                emails.append(",");
            }
        }
        return emails.toString();
    }
    public String toString() {
        return "{\"email\":" + "\"" + this.email + "\"" + ",\"firstname\":" + "\"" + this.firstName + "\"" + ",\"lastname\":" + "\"" + this.lastName + "\"" + ",\"address\":" + "\"" + this.address + "\"" + ",\"friends\":[" + this.getFriendsEmails() + "]}";
    }
}