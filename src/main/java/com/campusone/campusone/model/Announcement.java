package com.campusone.campusone.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;
    private String postedByRole; // "Admin", "Faculty", "HOD"
    private String postedByName;
    private String timeAgo;      // "2 hours ago", "1 day ago", etc.
    private String colorTag;     // "RED", "YELLOW", "GREEN", "PURPLE"
    private LocalDateTime createdAt = LocalDateTime.now();

    public Announcement() {
    }

    public Announcement(String title, String postedByRole, String timeAgo, String colorTag) {
        this.title = title;
        this.postedByRole = postedByRole;
        this.timeAgo = timeAgo;
        this.colorTag = colorTag;
        this.postedByName = postedByRole;
    }

    public Announcement(String title, String postedByRole, String postedByName, String timeAgo, String colorTag) {
        this.title = title;
        this.postedByRole = postedByRole;
        this.postedByName = postedByName;
        this.timeAgo = timeAgo;
        this.colorTag = colorTag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedByRole() {
        return postedByRole;
    }

    public void setPostedByRole(String postedByRole) {
        this.postedByRole = postedByRole;
    }

    public String getPostedByName() {
        return postedByName;
    }

    public void setPostedByName(String postedByName) {
        this.postedByName = postedByName;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getColorTag() {
        return colorTag;
    }

    public void setColorTag(String colorTag) {
        this.colorTag = colorTag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
