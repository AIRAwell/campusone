# CampusOne: College Management System

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Database](https://img.shields.io/badge/Database-H2%20(In--Memory)-blue.svg)](https://www.h2database.com/)
[![Security](https://img.shields.io/badge/Security-Spring%20Security-red.svg)](https://spring.io/projects/spring-security)

CampusOne is a web-based College Management System developed using **Java**, **Spring Boot**, **HTML5**, **CSS3**, and **H2 Database**. The main objective of this project is to simplify and manage daily college activities through a single platform. It provides separate dashboards for **Students**, **Faculty members**, **Heads of Departments (HOD)**, and **Administrators**.

---

## 📌 Abstract
The system includes features such as student login, timetable management, attendance tracking, assignment and notes management, announcements, and Leave/On-Duty (OD) request management. Faculty members can manage academic activities, HODs can monitor department activities and approve requests, and administrators can manage users and college information.

The project uses Spring Boot to develop the backend and H2 Database to store information during the demonstration. The user-friendly interface makes the system easy to access and operate. CampusOne reduces manual work, improves communication, and helps organize college information efficiently.

**Keywords:** College Management System, Java, Spring Boot, H2 Database, Attendance Management, Timetable, Web Application.

---

## 🖥️ System Dashboards & Features

### 1. Login Page
- Clean split layout with campus hero branding and feature highlights.
- Role selection dropdown (`Student`, `Faculty`, `HOD`, `Admin`).
- One-click default test credentials auto-fill.
- Password visibility toggle.

### 2. Student Dashboard
- **Key Metrics:** Today's Classes (3), Attendance Percentage (85%), Pending Assignments (2), New Notices (3).
- **Today's Timetable:** Live schedule with time, subject, and classroom room number.
- **Recent Announcements:** Latest notices with time-ago metadata and "View All" navigation.
- **Timetable Page:** Full timetable with faculty assignments and room allocations.
- **Leave / OD Requests:** Table with status badges (`Pending`, `Approved`, `Rejected`), and an interactive modal to submit new Leave or On-Duty requests.

### 3. Faculty Dashboard
- **Key Metrics:** My Classes (4), Pending Assignments (5), New Requests (2).
- **Quick Actions:** Mark Attendance, Upload Notes, Enter Marks, Post Announcements.
- **Recent Announcements:** Department-specific notices.

### 4. HOD Dashboard
- **Key Metrics:** Total Faculty (6), Total Students (120), Pending Approvals.
- **Department Reports:** Direct links to Attendance, Assignment, Student Performance, and Faculty Performance reports.
- **Recent Requests & Approvals:** Review student Leave and OD requests with 1-click **Approve** and **Reject** actions.

### 5. Admin Dashboard
- **Key Metrics:** Total Students (85), Total Faculty (12), Total Departments (5).
- **Quick Actions:** User management modals to add Students, Faculty, HODs, and Admins.
- **Infrastructure:** Manage Courses, Rooms, Events, and College Notices.

### 6. H2 Database Console
- Accessible at `/h2-console`
- JDBC URL: `jdbc:h2:mem:campusone`
- Username: `sa` | Password: *(blank)*

---

## 🔑 Default Login Credentials

| Role | Username | Password | Default Redirect |
|:---|:---|:---|:---|
| **Student** | `student1` | `student123` | `/student/dashboard` |
| **Faculty** | `faculty1` | `faculty123` | `/faculty/dashboard` |
| **HOD** | `hod1` | `hod123` | `/hod/dashboard` |
| **Admin** | `admin1` | `admin123` | `/admin/dashboard` |

---

## 🚀 How to Run Locally

### Prerequisites
- **Java 21** or later (e.g., Microsoft OpenJDK 21)
- Windows PowerShell / Command Prompt

### Step 1: Clone the Repository
```bash
git clone https://github.com/AIRAwell/campusone.git
cd campusone
```

### Step 2: Set Environment Variables (PowerShell)
```powershell
if (-not (Test-Path "C:\Temp")) { New-Item -ItemType Directory -Path "C:\Temp" | Out-Null }
$env:TEMP = "C:\Temp"
$env:TMP = "C:\Temp"
$env:JAVA_HOME = "C:\Program Files\Microsoft\jdk-21.0.13.21-hotspot"
$env:Path = "$env:Path;C:\Program Files\Microsoft\jdk-21.0.13.21-hotspot\bin"
```

### Step 3: Run the Application
```powershell
.\mvnw.cmd spring-boot:run
```

Once started, open your web browser and navigate to:
```
http://localhost:8080
```

---

## 🛠️ Technology Stack
- **Backend:** Java 21, Spring Boot, Spring Data JPA, Spring Security
- **Frontend:** Thymeleaf template engine, HTML5, Vanilla CSS3, SVG Icons
- **Database:** H2 In-Memory Relational Database
- **Build Tool:** Maven (with `mvnw` wrapper included)
