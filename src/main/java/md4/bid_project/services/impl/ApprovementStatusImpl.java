package md4.bid_project.services.impl;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.repositories.ApprovementStatusRepository;
import md4.bid_project.services.ApprovementStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovementStatusImpl implements ApprovementStatusService {
    @Autowired private ApprovementStatusRepository approvementStatusRepository;

    @Override
    public void saveNewApprovementStatus(ApprovementStatus approvementStatus) {
        approvementStatusRepository.save(approvementStatus);
    }

    @Override
    public ApprovementStatus findById(Long id) {
        return approvementStatusRepository.findById(id).orElse(null);
    }

    @Override
    public List<ApprovementStatus> findAll() {
        return approvementStatusRepository.findAll();
    }
}
