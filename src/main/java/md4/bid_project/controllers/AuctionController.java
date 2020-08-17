package md4.bid_project.controllers;

import md4.bid_project.models.Auction;
import md4.bid_project.models.AuctionRecord;
import md4.bid_project.models.AuctionStatus;
import md4.bid_project.services.AuctionRecordService;
import md4.bid_project.services.AuctionService;
import md4.bid_project.services.AuctionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRecordService auctionRecordService;

    @Autowired
    private AuctionStatusService auctionStatusService;

    @GetMapping("/auction")
    public ResponseEntity<List<Auction>> showListAuction(){
        List<Auction> auctions = auctionService.getAllAuction();
        return ResponseEntity.ok(auctions);
    }

    @GetMapping("auction/record")
    public ResponseEntity<List<AuctionRecord>> showListAuctionRecord(){
        List<AuctionRecord> auctionRecords = auctionRecordService.getAllAuctionRecords();
        return ResponseEntity.ok(auctionRecords);
    }

    @GetMapping("auction/status")
    public ResponseEntity<List<AuctionStatus>> showListAuctionStatus(){
        List<AuctionStatus> auctionStatuses = auctionStatusService.getAllAuctionStatus();
        return ResponseEntity.ok(auctionStatuses);
    }
}
