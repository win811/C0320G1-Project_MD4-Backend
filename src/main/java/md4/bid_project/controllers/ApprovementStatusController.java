package md4.bid_project.controllers;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Category;
import md4.bid_project.services.ApprovementStatusService;
import md4.bid_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/approvementStatus")
public class ApprovementStatusController {
    @Autowired
    private ApprovementStatusService approvementStatusService;

    @GetMapping("")
    public ResponseEntity<List<ApprovementStatus>> listAllApprovementStatuses() {
        List<ApprovementStatus> approvementStatuses = approvementStatusService.findAll();
        if (approvementStatuses.isEmpty()) {
            return new ResponseEntity<List<ApprovementStatus>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ApprovementStatus>>(approvementStatuses, HttpStatus.OK);
    }
}
