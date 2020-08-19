package md4.bid_project.controllers;

import md4.bid_project.models.dto.DealManageApi;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageDTO;
import md4.bid_project.repositories.DealManageDTORepository;
import md4.bid_project.services.DealManageDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//all created by Thao
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class DealManageController {

    @Autowired
    private DealManageDTOService dealManageDTOService;

    @GetMapping(value = "/deal-management")
    public ResponseEntity<DealManageApi> showAllNotDeletedDealList(@RequestParam("page") int currentPage,
                                                                   @RequestParam("limit") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id"));
        List<DealManageDTO> dealList = dealManageDTOService.findAllNotDeletedDeal(pageable);
        if (dealList.isEmpty()) {
            return new ResponseEntity<DealManageApi>(HttpStatus.NO_CONTENT);
        } else {
            DealManageApi dealManageApi = new DealManageApi();
            dealManageApi.setCurrentPage(currentPage);
            dealManageApi.setItems(dealList);
            dealManageApi.setTotalPage((int) Math.ceil((double) dealManageDTOService.countTotalItems() / pageSize));
            return new ResponseEntity<DealManageApi>(dealManageApi, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/deal-management/{id}")
    public ResponseEntity<CartDetail> findDealsById(@PathVariable("id") Long id) {
        CartDetail cartDetail = dealManageDTOService.findById(id);
        if (cartDetail == null) {
            return new ResponseEntity<CartDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CartDetail>(cartDetail, HttpStatus.OK);
    }

    @PutMapping(value = "/deal-management/delete")
    public Map<String, Boolean> deleteDeals(@RequestBody Map<String, Long[]> requestBody) {
        Long[] ids = requestBody.get("ids").clone();
        Map<String, Boolean> response = new HashMap<>();
        for(Long id : ids) {
            CartDetail deal = dealManageDTOService.findById(id);
            if(deal.getStatus().equals("Thành công") || deal.getStatus().equals("Thất bại") ){
                deal.setIsDelete(true);
                dealManageDTOService.save(deal);
                response.put("deleted " + id, Boolean.TRUE);
            } else {
                response.put("deleted " + id, Boolean.FALSE);
            }
        }
        return response;
    }

    @PostMapping(value = "/deal-management/search")
    public ResponseEntity<List<DealManageDTO>> test(@RequestBody Map<String, Object> infoSearch) {
        String nameBuyer = infoSearch.get("nameBuyer").toString();
        String nameSeller = infoSearch.get("nameSeller").toString();
        String nameProduct = infoSearch.get("nameProduct").toString();
        String statusOfDeal = infoSearch.get("statusOfDeal").toString();
        String totalPayment = infoSearch.get("totalPayment").toString();

        List<DealManageDTO> result = new ArrayList<>();
        if( !nameBuyer.isEmpty() && !nameSeller.isEmpty() && !nameProduct.isEmpty() && !statusOfDeal.isEmpty() && !totalPayment.isEmpty()) {
            result = dealManageDTOService.searchBySellerAndBuyerAndProductAndTotalPayAndStatus(nameBuyer, nameSeller, nameProduct, totalPayment, statusOfDeal);
        } else{
            result = dealManageDTOService.searchByOneField(nameBuyer, nameSeller, nameProduct, Double.parseDouble(totalPayment), statusOfDeal);
        }
        if(result.isEmpty()) {
            return new ResponseEntity<List<DealManageDTO>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<DealManageDTO>>(result, HttpStatus.OK);
        }
    }
}
