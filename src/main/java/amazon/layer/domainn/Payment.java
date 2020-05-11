package amazon.layer.domainn;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

enum paymentType {
	Visa, MasterCard, Prepaid
};

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty()
	private String number;
	@NotEmpty()
	private String csv;
	@Enumerated(EnumType.STRING)
	paymentType payType;

	public Payment() {
	}

	public Payment(@NotEmpty String number, @NotEmpty paymentType payType, String csv) {
		super();
		this.number = number;
		this.payType = payType;
		this.csv = csv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public paymentType getPayType() {
		return payType;
	}

	public void setPayType(paymentType payType) {
		this.payType = payType;
	}

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}
	
	
}