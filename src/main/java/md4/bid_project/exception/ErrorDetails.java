package md4.bid_project.exception;

import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetails {


	private Date timestamp;
	private String message;
	private String details;
	private Map<String, String> errors;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ErrorDetails(Date timestamp, String details) {
		this.timestamp = timestamp;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errorList) {
		LinkedHashMap<String, String> errorMap = new LinkedHashMap<>();
		for (FieldError err: errorList) {
			errorMap.put(err.getField(), err.getDefaultMessage());
		}
		this.errors = errorMap;
	}
}
