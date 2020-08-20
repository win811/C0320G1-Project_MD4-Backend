package md4.bid_project.exception;

import org.springframework.validation.BindingResult;

// Duy
public class ViolatedException extends Exception {

    private BindingResult result;
    private String message;

    public ViolatedException(BindingResult result) {
        this.result = result;
    }
    public ViolatedException(String message) {
        super(message);
    }

    public BindingResult getResult() {
        return result;
    }

    public void setResult(BindingResult result) {
        this.result = result;
    }
}
