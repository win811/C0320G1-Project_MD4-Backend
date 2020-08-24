package md4.bid_project.exception;

public class SettlementException extends Exception {
    public SettlementException(StringBuilder message) {
        super(message.toString());
    }
}
