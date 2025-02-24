package model;

import java.io.Serializable;
import java.util.List;

public class GetCourse {

    private String url;
    private String services;
    private String expertise;
    private Courses courses;
    private String instructor;
    private String linkedIn;
    public GetCourse(){

    }

    public GetCourse(String url, String services, String expertise, Courses courses, String instructor, String linkedIn) {
        this.url = url;
        this.services = services;
        this.expertise = expertise;
        this.courses = courses;
        this.instructor = instructor;
        this.linkedIn = linkedIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("url :"+url).append("\nservices :"+services).append("\nexpertise "+expertise).append("\ncourses :"+courses).append("\ninstructor :"+instructor).append("\nlinkedIn :"+linkedIn);
        return sb.toString();
    }
}
