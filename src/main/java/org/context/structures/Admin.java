package org.context.structures;

/**
 * An extension to the 'User' class
 * An admin has extra priveleges
 *
 * Typically an Admin user would be a teacher or a parent
 */
public final class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }
}
