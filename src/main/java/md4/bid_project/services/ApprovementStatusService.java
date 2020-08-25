package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Category;

import java.util.List;

public interface ApprovementStatusService {
    ApprovementStatus findByName (String name);
    List<ApprovementStatus> findAll();
}
