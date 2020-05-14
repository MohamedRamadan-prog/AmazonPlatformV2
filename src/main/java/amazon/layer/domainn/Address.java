package amazon.layer.domainn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "Required field")
	private String state;
	@NotEmpty(message = "Required field")
	private String city;
	@NotEmpty(message = "Required field")
	private String zipCode;
	@NotEmpty(message = "Required field")
	private String addressLine;

	public Address(String state, String city, String zipCode, String addressLine) {
		super();
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.addressLine = addressLine;
	}
	
	public Address() {}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
