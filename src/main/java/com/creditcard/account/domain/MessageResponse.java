package com.creditcard.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Used to make consistent response for json or xml.
 */
@XmlRootElement(name = "message")
@JsonRootName(value = "message")
public class MessageResponse {

	/**
	 * The message.
	 */
	@JsonProperty
	private String message;

	public MessageResponse() {
	}

	/**
	 * Constructor to create a message to send in a controller response.
	 *
	 * @param message
	 * 		string representation of message to pass
	 */
	public MessageResponse(final String message) {
		setMessage(message);
	}

	//Getters and Setters

	// This annotation ensures that only the string value is returned for the xml mapping rather than tag named message
	@XmlValue
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageResponse{"
				+ "message='" + message + '\''
				+ '}';
	}

}
