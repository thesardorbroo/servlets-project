package entity;


import enums.StudentsLevel;

public class Student {

    private Integer id;

    private String name;

    private String phoneNumber;


    public Student(){}

    public Student(Integer id, String name, String phoneNumber){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("\n{\n\n\tid: %d\n\tname: %s\n\tphone number: %s\n}\n", id, name, phoneNumber);
    }
}
