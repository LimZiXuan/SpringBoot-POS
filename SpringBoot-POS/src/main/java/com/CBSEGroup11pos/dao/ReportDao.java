package com.CBSEGroup11pos.dao;

import com.CBSEGroup11pos.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportDao extends JpaRepository<Report, Integer> {

    List<Report> getAllReports();

    List<Report> getReportByUserName(@Param("username") String username);
}
