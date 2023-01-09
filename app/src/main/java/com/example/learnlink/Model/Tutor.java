package com.example.learnlink.Model;

import java.util.List;

public class Tutor extends Student{
    private List<Subject> TutorSubjects;

    public Tutor(String firstName, String lastName, String email, String password, String phoneNumber, List<Subject> tutorSubjects) {
        super(firstName, lastName, email, password, phoneNumber);
        TutorSubjects = tutorSubjects;
    }

    public List<Subject> getTutorSubjects() {
        return TutorSubjects;
    }

    public void setTutorSubjects(List<Subject> tutorSubjects) {
        TutorSubjects = tutorSubjects;
    }
    public void addTutorSubject(Subject subject){
        TutorSubjects.add(subject);
    }
}
