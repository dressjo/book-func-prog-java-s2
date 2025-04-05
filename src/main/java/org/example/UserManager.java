package org.example;

import org.example.UserManager.User.Address;

public class UserManager {

  public record Company(String name, Address address) {
    public record Address(String street, String city) {}
  }

  record User(Address address)  {
    record Address(String street) {}
    public User {
      if (address == null) {
        throw new IllegalArgumentException("Address cannot be null");
      }
    }

  }

  public static void main(String[] args) {
    final User user = new User(new User.Address("street"));

    System.out.println(user.address.street);
    if(user instanceof User(Address(String street)) && street.equals("street")) {
      System.out.println("UserPrincipal");
    }


  }
}
