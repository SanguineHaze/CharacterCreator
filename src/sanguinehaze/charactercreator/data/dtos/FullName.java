package sanguinehaze.charactercreator.data.dtos;

public class FullName {
    private String firstname;
    private String lastname;

    public static FullName of(String firstname, String lastname) {
        return new FullName(firstname, lastname);
    }

    private FullName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
