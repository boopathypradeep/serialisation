package org.serialization;

public class DeleteAddress_Input {
	private String address_id;

	public DeleteAddress_Input(String address_id) {
		super();
		this.address_id = address_id;
	}

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
}
