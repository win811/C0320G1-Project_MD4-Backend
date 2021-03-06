package md4.bid_project.services.impl;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.repositories.ApprovementStatusRepository;
import md4.bid_project.services.ApprovementStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovementStatusImpl implements ApprovementStatusService {
    @Autowired private ApprovementStatusRepository approvementStatusRepository;

    @Override
    public ApprovementStatus findByName(String name) {
        return approvementStatusRepository.findByName(name);
    }

    @Override
    public ApprovementStatus getApprovementById(Long id) {
        return approvementStatusRepository.findById(id).orElse(null);
    }
}
