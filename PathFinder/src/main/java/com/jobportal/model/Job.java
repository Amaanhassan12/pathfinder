package com.jobportal.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    private String location;
    private String salary;
    @Column(length = 4000)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedOn = new Date();
    @ManyToOne(optional = false)
    private User employer;

    public Job() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getPostedOn() { return postedOn; }
    public void setPostedOn(Date postedOn) { this.postedOn = postedOn; }
    public User getEmployer() { return employer; }
    public void setEmployer(User employer) { this.employer = employer; }
}
