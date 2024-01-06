package com.CBSEGroup11pos.service;

import com.CBSEGroup11pos.entity.Report;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ReportService {

    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<Report>> getReports();

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    ResponseEntity<String> deleteReport(Integer id);
}
