package md4.bid_project.services.restful.paypal;

import com.paypal.orders.LinkDescription;

import java.util.*;


public class Transaction {

    private String status;
    private String id;
    private List<Link> links;

    public String getStatus() {
        return status;
    }

    public Transaction setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public Transaction setId(String id) {
        this.id = id;
        return this;
    }

    public List<Link> getLinks() {
        return links;
    }

    public Transaction setLinks(List<LinkDescription> links) {
        List<Link> _links = new ArrayList<>();
        for (LinkDescription link: links) {
            _links.add(new Link(link.href(), link.rel(), link.method()));
        }
        this.links = _links;
        return this;
    }
}
