package com.example.idemo.model;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "faculty_id",referencedColumnName = "id")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "created_user",referencedColumnName = "id")
    private User createdUser;

    public Student() {
    }

    public Student(Long id, String name, String studentId, String address, String contact, String gender, String email, Faculty faculty, User createdUser) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.email = email;
        this.faculty = faculty;
        this.createdUser = createdUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", faculty=" + faculty +
                ", createdUser=" + createdUser +
                '}';
    }
}
