package com.jobportal.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "applications", uniqueConstraints = @UniqueConstraint(columnNames = {"job_id", "applicant_id"}))
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Job job;
    @ManyToOne(optional = false)
    private User applicant;
    @Column(length = 2000)
    private String coverNote;
    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedOn = new Date();

    public Application() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    public User getApplicant() { return applicant; }
    public void setApplicant(User applicant) { this.applicant = applicant; }
    public String getCoverNote() { return coverNote; }
    public void setCoverNote(String coverNote) { this.coverNote = coverNote; }
    public Date getAppliedOn() { return appliedOn; }
    public void setAppliedOn(Date appliedOn) { this.appliedOn = appliedOn; }
}
