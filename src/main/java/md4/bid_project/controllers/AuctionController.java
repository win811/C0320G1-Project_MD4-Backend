package md4.bid_project.controllers;

import md4.bid_project.models.Auction;
import md4.bid_project.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @GetMapping("auction/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable(value = "id") Long auctionId) {
        Auction auction = auctionService.getAuctionById(auctionId);
        return ResponseEntity.ok().body(auction);
    }
}
