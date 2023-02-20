package com.example.learnlink.Model;

import java.util.List;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    public boolean isIstutor() {
        return isTutor;
    }

    public void setIstutor(boolean istutor) {
        this.isTutor = istutor;
    }
    private List<Subject> TutorSubjects;
    boolean isTutor;
    public List<Subject> getTutorSubjects() {
        return TutorSubjects;
    }
    public void removeTutorSubject(Subject item) {
        TutorSubjects.remove(item);
    }

    public Student(String id, String firstName, String lastName, String email, String password, String phoneNumber, List<Subject> tutorSubjects, boolean isTutor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        TutorSubjects = tutorSubjects;
        this.isTutor = isTutor;
    }

    public void setTutorSubjects(List<Subject> tutorSubjects) {
        TutorSubjects = tutorSubjects;
    }
    public void addTutorSubject(Subject subject){
        TutorSubjects.add(subject);
    }
    public Student(String id ,String firstName, String lastName, String email, String phoneNumber,boolean istutor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isTutor = istutor;
    }
    public Student(String id ,String firstName, String lastName,boolean istutor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isTutor = istutor;
    }
    public Student() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
