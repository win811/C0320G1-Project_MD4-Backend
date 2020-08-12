package md4.bid_project.repositories;

import md4.bid_project.models.AuctionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRecordRepository extends JpaRepository<AuctionRecord, Long> {
}
