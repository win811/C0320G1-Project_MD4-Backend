package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;

import md4.bid_project.models.ApprovementStatus;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;

import java.util.List;

public interface ApprovementStatusService {
    ApprovementStatus getApprovementById(Long id);
    ApprovementStatus findByName (String name);
    void saveNewApprovementStatus(ApprovementStatus approvementStatus);
    ApprovementStatus findById (Long id);

    List<ApprovementStatus> findAll();
}
