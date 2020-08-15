package md4.bid_project.controllers;

import md4.bid_project.models.Auction;
import md4.bid_project.models.AuctionRecord;
import md4.bid_project.services.AuctionRecordService;
import md4.bid_project.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AuctionController {

    @Autowired
    AuctionService auctionService;
    @Autowired
    AuctionRecordService auctionRecordService;

    //    Cường
    @GetMapping("/myAuctionRecords/{bidderId}")
    public ResponseEntity<Page<AuctionRecord>> findAuctionRecordByBidderId(@PathVariable(value = "bidderId") Long bidderId,
                                                                           @RequestParam(name = "productName",defaultValue = "") String productName,
                                                                           @RequestParam(name = "recordStatusName",defaultValue = "") String recordStatusName,
                                                                           @PageableDefault(value = 1) Pageable pageable) {
        Page<AuctionRecord> auctionRecordPage = auctionRecordService.findByBidderIdAndProductNameAndRecordStatusName(bidderId,productName,recordStatusName,pageable);
        return ResponseEntity.ok(auctionRecordPage);
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<Auction>> listAllAuctions(){
        List<Auction> auctions = auctionService.getAllAuctions();
        if(auctions.isEmpty()){
            return new ResponseEntity<List<Auction>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Auction>>(auctions, HttpStatus.OK);
    }

    @GetMapping("/auctions/{id}")
    public ResponseEntity<Auction> findAuctionById(@PathVariable Long id){
        Auction auction = auctionService.getAuctionById(id);
        if (auction == null) {
            return new ResponseEntity<Auction>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Auction>(auction, HttpStatus.OK);
    }

    @PostMapping("/auctions")
    public ResponseEntity<Void> createAuction (@RequestBody Auction auction){
        auctionService.createAuction(auction);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping ("/auctions/{id}")
    public ResponseEntity<Auction> editAuction (@PathVariable Long id, @RequestBody Auction newAuction){
        Auction auction = auctionService.getAuctionById(id);
        if(auction == null){
            return new ResponseEntity<Auction>(HttpStatus.NOT_FOUND);
        }
        auction.setProduct(newAuction.getProduct());
        auction.setAuctionStatus(newAuction.getAuctionStatus());
        auction.setCloseTime(newAuction.getCloseTime());
        auctionService.editAuction(auction);
        return new ResponseEntity<Auction>(auction, HttpStatus.OK);
    }

    @DeleteMapping ("/auctions/{id}")
    public ResponseEntity<Auction> deleteAuction(@PathVariable Long id){
        Auction auction = auctionService.getAuctionById(id);
        if(auction == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        auctionService.deleteAuction(id);
        return new ResponseEntity<Auction>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/auctionRecords")
    public ResponseEntity<List<AuctionRecord>> getAllAuctionRecord(){
        List<AuctionRecord> auctionRecords = auctionRecordService.getAllAuctionRecords();
        if (auctionRecords.isEmpty()){
            return new ResponseEntity<List<AuctionRecord>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<AuctionRecord>>(auctionRecords, HttpStatus.OK);
    }

    @GetMapping(value = "/auctionRecords/{id}")
    public ResponseEntity<AuctionRecord> getAuctionRecordById(@PathVariable Long id){
        AuctionRecord auctionRecord = auctionRecordService.getAuctionRecordById(id);
        if (auctionRecord == null){
            return new ResponseEntity<AuctionRecord>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<AuctionRecord>(auctionRecord, HttpStatus.OK);
    }

    @PostMapping (value = "/auctionRecords")
    public ResponseEntity<Void> createAuctionRecord(@RequestBody AuctionRecord auctionRecord, UriComponentsBuilder ucBuilder){
        auctionRecordService.createAuctionRecord(auctionRecord);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/auctionRecords/{id}").buildAndExpand(auctionRecord.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping (value = "/auctionRecords/{id}")
    public ResponseEntity<AuctionRecord> editAuctionRecord (@PathVariable Long id, @RequestBody AuctionRecord newAuctionRecord){
        AuctionRecord auctionRecord = auctionRecordService.getAuctionRecordById(id);
        if(auctionRecord == null){
            return new ResponseEntity<AuctionRecord>(HttpStatus.NOT_FOUND);
        }
        auctionRecord.setAuction(newAuctionRecord.getAuction());
        auctionRecord.setBidder(newAuctionRecord.getBidder());
        auctionRecord.setBidPrice(newAuctionRecord.getBidPrice());
        auctionRecord.setBidTime(newAuctionRecord.getBidTime());
        auctionRecord.setIsWinner(newAuctionRecord.getIsWinner());
        auctionRecordService.editAuctionRecord(auctionRecord);
        return new ResponseEntity<AuctionRecord>(auctionRecord, HttpStatus.OK);
    }

    @DeleteMapping ("/auctionRecords/{id}")
    public ResponseEntity<AuctionRecord> deleteAuctionRecord(@PathVariable Long id){
        AuctionRecord auctionRecord = auctionRecordService.getAuctionRecordById(id);
        if( auctionRecord == null){
            return new ResponseEntity<AuctionRecord>(HttpStatus.NOT_FOUND);
        }
        auctionRecordService.deleteAuctionRecord(id);
        return new ResponseEntity<AuctionRecord>(HttpStatus.NO_CONTENT);
    }

}
