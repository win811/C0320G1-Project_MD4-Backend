package md4.bid_project.controllers;

import md4.bid_project.models.Auction;
import md4.bid_project.models.AuctionRecord;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.repositories.AuctionStatusRepository;
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

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// creator: Hoai Ngan team C
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
                                                                           @RequestParam(name = "productName", defaultValue = "") String productName,
                                                                           @RequestParam(name = "recordStatusName", defaultValue = "") String recordStatusName,
                                                                           @RequestParam(name = "page",defaultValue = "0") int page) {
        Page<AuctionRecord> auctionRecordPage = auctionRecordService.findByBidderIdAndProductNameAndRecordStatusName(bidderId, productName, recordStatusName, page);
        return ResponseEntity.ok(auctionRecordPage);
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<Auction>> listAllAuctions() {
        List<Auction> auctions = auctionService.getAllAuctions();
        if (auctions.isEmpty()) {
            return new ResponseEntity<List<Auction>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Auction>>(auctions, HttpStatus.OK);
    }

    @GetMapping("/auctions/{id}")
    public ResponseEntity<Auction> findAuctionById(@PathVariable Long id) {
        Auction auction = auctionService.getAuctionById(id);
        if (auction == null) {
            return new ResponseEntity<Auction>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Auction>(auction, HttpStatus.OK);
    }
    //Bach
    @GetMapping("/auctions/product/{productId}")
    public ResponseEntity<Auction> findAuctionByProduct(@PathVariable Long productId){
        Auction auction = auctionService.getAutionByProductId(productId);
        if (auction == null) {
            return new ResponseEntity<Auction>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Auction>(auction, HttpStatus.OK);
    }

    @PostMapping("/auctions")
    public ResponseEntity<Void> createAuction(@RequestBody Auction auction) {
        auctionService.createAuction(auction);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/auctions")
    public ResponseEntity<Auction> editAuction(@RequestBody Auction newAuction) {
        auctionService.editAuction(newAuction);
        return new ResponseEntity<Auction>(newAuction, HttpStatus.OK);
    }

    // Fix đấu giá
    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AuctionStatusRepository auctionStatusRepository;

    @PutMapping("/auctions/{auctionId}")
    public ResponseEntity<Auction> closeAuction(@PathVariable Long auctionId, @RequestBody Map<String, Object> reqBody)
            throws Exception {
        String closeTimeString = (String) reqBody.get("closeTime");
        LocalDateTime closeTime = LocalDateTime.parse(closeTimeString);
        Long status = Long.parseLong(reqBody.get("status").toString());
        Optional<Auction> optionalAuction = auctionRepository.findById(auctionId);
        if (optionalAuction.isPresent()) {
            Auction auction = optionalAuction.get();
            auction.setCloseTime(closeTime);
            auction.setAuctionStatus(auctionStatusRepository.findById(status).orElse(null));
            auctionRepository.save(auction);
            return ResponseEntity.ok(auction);
        } else {
            throw new Exception();
        }
    }

    @DeleteMapping("/auctions/{id}")
    public ResponseEntity<Auction> deleteAuction(@PathVariable Long id) {
        Auction auction = auctionService.getAuctionById(id);
        if (auction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        auctionService.deleteAuction(id);
        return new ResponseEntity<Auction>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/auctionRecords")
    public ResponseEntity<List<AuctionRecord>> getAllAuctionRecord() {
        List<AuctionRecord> auctionRecords = auctionRecordService.getAllAuctionRecords();
        if (auctionRecords.isEmpty()) {
            return new ResponseEntity<List<AuctionRecord>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<AuctionRecord>>(auctionRecords, HttpStatus.OK);
    }

    @GetMapping(value = "/auctionRecords/{id}")
    public ResponseEntity<AuctionRecord> getAuctionRecordById(@PathVariable Long id) {
        AuctionRecord auctionRecord = auctionRecordService.getAuctionRecordById(id);
        if (auctionRecord == null) {
            return new ResponseEntity<AuctionRecord>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<AuctionRecord>(auctionRecord, HttpStatus.OK);
    }

    @PostMapping(value = "/auctionRecords")
    public ResponseEntity<Void> createAuctionRecord(@RequestBody AuctionRecord auctionRecord, UriComponentsBuilder ucBuilder) {
        auctionRecordService.createAuctionRecord(auctionRecord);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/auctionRecords/{id}").buildAndExpand(auctionRecord.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

//    @PutMapping (value = "/auctionRecords/{id}")
//    public ResponseEntity<AuctionRecord> editAuctionRecord (@PathVariable Long id, @RequestBody AuctionRecord newAuctionRecord){
//        AuctionRecord auctionRecord = auctionRecordService.getAuctionRecordById(id);
//        if(auctionRecord == null){
//            return new ResponseEntity<AuctionRecord>(HttpStatus.NOT_FOUND);
//        }
//        auctionRecord.setAuction(newAuctionRecord.getAuction());
//        auctionRecord.setBidder(newAuctionRecord.getBidder());
//        auctionRecord.setBidPrice(newAuctionRecord.getBidPrice());
//        auctionRecord.setBidTime(newAuctionRecord.getBidTime());
//        auctionRecord.setIsWinner(newAuctionRecord.getIsWinner());
//        auctionRecordService.editAuctionRecord(auctionRecord);
//        return new ResponseEntity<AuctionRecord>(auctionRecord, HttpStatus.OK);
//    }

    @DeleteMapping("/auctionRecords/{id}")
    public ResponseEntity<AuctionRecord> deleteAuctionRecord(@PathVariable Long id) {
        AuctionRecord auctionRecord = auctionRecordService.getAuctionRecordById(id);
        if (auctionRecord == null) {
            return new ResponseEntity<AuctionRecord>(HttpStatus.NOT_FOUND);
        }
        auctionRecordService.deleteAuctionRecord(id);
        return new ResponseEntity<AuctionRecord>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/topAuctionRecords/{id}")
    public ResponseEntity<List<AuctionRecord>> getTopAuctionRecords(@PathVariable Long id) {

        List<AuctionRecord> topAuctionRecord = auctionRecordService.getTopAuctionRecords(id);

        if (topAuctionRecord.isEmpty()) {
            return new ResponseEntity<List<AuctionRecord>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<AuctionRecord>>(topAuctionRecord, HttpStatus.OK);
    }

    @GetMapping("/highestPrice/{id}")
    public ResponseEntity<AuctionRecord> getHighestPrice(@PathVariable Long id) {
        AuctionRecord record = auctionRecordService.getRecordHavingBestPrice(id);

        if (record == null) {
            return new ResponseEntity<AuctionRecord>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<AuctionRecord>(record, HttpStatus.OK);
    }

    @PutMapping("/highestPrice")
    public ResponseEntity<AuctionRecord> editAuctionRecord(@RequestBody AuctionRecord newAuctionRecord) {

        auctionRecordService.editAuctionRecord(newAuctionRecord);
        return new ResponseEntity<AuctionRecord>(newAuctionRecord, HttpStatus.OK);
    }

    @GetMapping("/auctionRecordByUser/{auctionId}/{userId}")
    public ResponseEntity<AuctionRecord> findAuctionRecordByAuctionAndUser(@PathVariable Long auctionId, @PathVariable Long userId) {
        return ResponseEntity.ok(this.auctionRecordService.findByAuctionIdAndBidderId(auctionId, userId));
    }


    //Creator: BHung -find auctions by status
    @GetMapping("/home/auctionStatus/{id}")
    public ResponseEntity<List<Auction>> getAuctionsByStatusId(@PathVariable Long id) {
        List<Auction> auctions = auctionService.findAuctionsByStatusId(id);
        if (auctions == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Auction>>(auctions, HttpStatus.OK);
    }

    //Creator: BHung -find auctions by status
    @GetMapping("/home/topAuction")
    public ResponseEntity<List<Auction>> getTopAuction() {
        List<Auction> auctions = auctionService.findTopAuctions();
        if (auctions == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Auction>>(auctions, HttpStatus.OK);
    }

    //Creator: BHung -find auctions by status and categories
    @GetMapping("/home/{statusId}/{categoryName}")
    public ResponseEntity<List<Auction>> getAuctionsByStatusAndCategory(@PathVariable Long statusId, @PathVariable Optional<String> categoryName) {
        String newName = "";
        if (categoryName.isPresent()) {
            newName = categoryName.get();
        }
        List<Auction> auctions = auctionService.findAllAuctionByStatusAndCategoryName(statusId, newName);
        if (auctions == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Auction>>(auctions, HttpStatus.OK);
    }

    //    Creator: BHung -search HomePage đang code
    @GetMapping("/home/search")
    public ResponseEntity<List<Auction>> searchAuctionHomePage(@RequestParam(name = "productName", defaultValue = "") String productName,
                                                               @RequestParam(name = "categoryName", defaultValue = "") String categoryName,
                                                               @RequestParam(name = "price", defaultValue = "") String price) {
        List<Auction> auctions = new ArrayList<>();
        if (price.equals("")) {
            auctions = auctionService.findAllAuctionsByProductNameAndCategoryName(productName, categoryName);
        } else if (price.equals("5000000")) {
            auctions = auctionService.findALlAuctionsByProductNameAndCategoryNameAndPriceMoreThan(productName, categoryName, price);
        } else {
            auctions = auctionService.findAllAuctionByProductNameAndCategoryNameAndPriceRange(productName, categoryName, price);
        }
        return new ResponseEntity<List<Auction>>(auctions, HttpStatus.OK);
    }
}
