package md4.bid_project.repositories;
// creator: Hoai Ngan team C
import md4.bid_project.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {
}
