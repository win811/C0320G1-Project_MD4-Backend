package md4.bid_project.controllers;

import md4.bid_project.models.dto.DealManageApi;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.dto.DealManageDTO;
import md4.bid_project.repositories.CartDetailRepository;
import md4.bid_project.services.CartDetailService;
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
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class DealManageController {

    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @GetMapping(value = "/deal-management")
    public ResponseEntity<DealManageApi> showAllNotDeletedDealList(@RequestParam("page") int currentPage,
                                                                   @RequestParam("limit") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id"));
        List<DealManageDTO> dealList = cartDetailService.findAllNotDeletedDeal(pageable);
        if (dealList.isEmpty()) {
            return new ResponseEntity<DealManageApi>(HttpStatus.NO_CONTENT);
        } else {
            DealManageApi dealManageApi = new DealManageApi();
            dealManageApi.setCurrentPage(currentPage);
            dealManageApi.setItems(dealList);
            dealManageApi.setTotalPage((int) Math.ceil((double) cartDetailService.countTotalItems() / pageSize));
            return new ResponseEntity<DealManageApi>(dealManageApi, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/deal-management/{id}")
    public ResponseEntity<CartDetail> findDealsById(@PathVariable("id") Long id) {
        CartDetail cartDetail = cartDetailService.findById(id);
        if (cartDetail == null) {
            return new ResponseEntity<CartDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CartDetail>(cartDetail, HttpStatus.OK);
    }

    @PutMapping(value = "/deal-management/delete")
    public Map<String, Boolean> deleteDeals(@RequestBody Long[] ids){
        Map<String, Boolean> response = new HashMap<>();
        for(Long id : ids) {
            CartDetail deal = cartDetailService.findById(id);
            if(deal.getStatus().equals("Thành công") || deal.getStatus().equals("Thất bại") ){
                deal.setIsDelete(true);
                cartDetailService.save(deal);
                response.put("deleted " + id, Boolean.TRUE);
            } else {
                response.put("deleted " + id, Boolean.FALSE);
            }
        }
        return response;
    }

    @PutMapping(value = "/deal-management/delete/{id}")
    public ResponseEntity<CartDetail> deleteOne(@PathVariable(value = "id") Long id) {
        CartDetail deal = cartDetailService.findById(id);
        if(deal.getStatus().equals("Thành công") || deal.getStatus().equals("Thất bại") ){
            deal.setIsDelete(true);
            final CartDetail deletedDeal = cartDetailService.save(deal);
            return ResponseEntity.ok(deletedDeal);
        } else {
            return new ResponseEntity<CartDetail>(deal, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/deal-management/search")
    public ResponseEntity<DealManageApi> searchDeals(@RequestBody Map<String, Object> infoSearch,
                                                     @RequestParam("page") int currentPage,
                                                     @RequestParam("limit") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id"));
        String nameBuyer = (infoSearch.get("nameBuyer").toString());
        String nameSeller = (infoSearch.get("nameSeller").toString());
        String nameProduct = (infoSearch.get("nameProduct").toString());
        String statusOfDeal = (infoSearch.get("statusOfDeal").toString());
        Double totalPayment = Double.valueOf(infoSearch.get("totalPayment").toString());

        List<CartDetail> dealList = cartDetailRepository.queryBy(nameBuyer, nameSeller, nameProduct, totalPayment, statusOfDeal);
        if (dealList.isEmpty()) {
            return new ResponseEntity<DealManageApi>(HttpStatus.NO_CONTENT);
        } else {
            DealManageApi dealManageApi = new DealManageApi();
            dealManageApi.setItems(dealList);
            dealManageApi.setCurrentPage(currentPage);
            dealManageApi.setTotalPage((int) Math.ceil((double) cartDetailService.countTotalItems() / pageSize));
            return new ResponseEntity<DealManageApi>(dealManageApi, HttpStatus.OK);
        }
    }
}
