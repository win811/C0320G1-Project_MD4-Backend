package md4.bid_project.services.restful.paypal;


import lombok.Data;

@Data
public class Link {
    private String href;
    private String rel;
    private String method;

    public Link(String href, String rel, String method) {
        this.href = href;
        this.rel = rel;
        this.method = method;
    }
}
