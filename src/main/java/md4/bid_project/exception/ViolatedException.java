package md4.bid_project.exception;

import org.springframework.validation.BindingResult;

public class ViolatedException extends Exception {

    private BindingResult result;

    public ViolatedException(BindingResult result) {
        this.result = result;
    }

    public BindingResult getResult() {
        return result;
    }

    public void setResult(BindingResult result) {
        this.result = result;
    }
}
