package com.CBSEGroup11pos.restImpl;

import com.CBSEGroup11pos.entity.Report;
import com.CBSEGroup11pos.rest.ReportRest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ReportRestImpl implements ReportRest {
    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<List<Report>> getReports() {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteReport(Integer id) {
        return null;
    }
}
