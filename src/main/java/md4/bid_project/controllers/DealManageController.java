package md4.bid_project.controllers;

import md4.bid_project.models.dto.DealManageApi;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageDTO;
import md4.bid_project.services.DealManageDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//all created by Thao
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class DealManageController {

    @Autowired
    private DealManageDTOService dealManageDTOService;

    //DISPLAY LIST FUNCTION
    @GetMapping(value = "/admin/deal-management")
    public ResponseEntity<DealManageApi> showAllNotDeletedDealList(@RequestParam("page") int currentPage,
                                                                   @RequestParam("limit") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id"));
        List<DealManageDTO> dealList = dealManageDTOService.findAllNotDeletedDeal(pageable);
        int totalOfItems = dealManageDTOService.countTotalItems();
        return dealManageDTOService.setInfoToDealManageApi(dealList, currentPage, pageSize, totalOfItems);
    }

    //SET DELETION STATUS FUNCTION
    @PutMapping(value = "/admin/deal-management/delete")
    public ResponseEntity<Map<String, Long>> setDealsIsDeleted(@RequestBody Long[] ids) {
        Map<String, Long> response = new HashMap<>();
        for(Long id : ids) {
            CartDetail deal = dealManageDTOService.findById(id);
            if (deal == null) {
                return new ResponseEntity<Map<String, Long>>(HttpStatus.NOT_FOUND);
            } else {
                if(deal.getStatus().equals("Thành công") || deal.getStatus().equals("Thất bại") ){
                    deal.setIsDelete(true);
                    dealManageDTOService.save(deal);
                    response.put("deleted", id);
                } else {
                    response.put("not allow", id);
                }
            }
        }
        return new ResponseEntity<Map<String, Long>>(response, HttpStatus.OK);
    }

    //SEARCH FUNCTION
    @PostMapping(value = "/admin/deal-management/search")
    public ResponseEntity<DealManageApi> search(@RequestBody Map<String, Object> infoSearch,
                                                @RequestParam("page") int currentPage,
                                                @RequestParam("limit") int pageSize) {
        String nameBuyer = infoSearch.get("nameBuyer").toString();
        String nameSeller = infoSearch.get("nameSeller").toString();
        String nameProduct = infoSearch.get("nameProduct").toString();
        String statusOfDeal = infoSearch.get("statusOfDeal").toString();
        Double totalPayment = Double.parseDouble(infoSearch.get("totalPayment").toString());
        Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id"));

        List<DealManageDTO> results = dealManageDTOService.searchByFiveFields(nameBuyer, nameSeller, nameProduct, totalPayment, statusOfDeal, pageable);
        int totalOfResult = dealManageDTOService.countSearchResult(nameBuyer, nameSeller, nameProduct, totalPayment, statusOfDeal);
        return dealManageDTOService.setInfoToDealManageApi(results, currentPage, pageSize, totalOfResult);
    }

}