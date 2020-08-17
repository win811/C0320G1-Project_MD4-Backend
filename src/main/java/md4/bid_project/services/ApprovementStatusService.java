package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;

public interface ApprovementStatusService {
    ApprovementStatus findByName (String name);
}
