package entity;

public class Users {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String username;

    private String password;

    private String priority;

    public Users(){}

    public Users(Integer id, String firstName, String lastName, String phoneNumber, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                        "\n\t{\n\tId: %d\n" +
                        "\tFirstname: %s\n" +
                        "\tLastname: %s\n" +
                        "\tPhone number: %s\n" +
                        "\tUsername: %s\n\t}\n",
                id, firstName, lastName, phoneNumber, username
                );
    }
}
