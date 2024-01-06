package com.CBSEGroup11pos.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name = "Report.getAllReports", query = "select r from Report r order by r.id desc")

@NamedQuery(name = "Report.getReportByUserName", query = "select r from Report r where r.createdBy=:username order by r.id desc")

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "reportType")
    private String reportType;
    @Column(name = "createdby")
    private String createdBy;
}
