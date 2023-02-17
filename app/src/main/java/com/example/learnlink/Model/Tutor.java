package com.example.learnlink.Model;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Student{
    private List<Subject> TutorSubjects;



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
