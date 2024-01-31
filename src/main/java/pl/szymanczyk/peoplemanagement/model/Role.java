package pl.szymanczyk.peoplemanagement.model;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("Admin"),
    IMPORTER("Importer"),
    EMPLOYEE("Employee");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
