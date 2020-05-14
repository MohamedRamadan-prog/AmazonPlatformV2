package amazon.layer.domainn;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Required field")
	@Size(min = 15 , max = 17 , message = "this field has to be 16 Digits")
	private String number;
	@NotEmpty(message = "Required field")
	@Size(min = 2 , max = 4 , message = "this field has to be 3 Digits")
	private String csv;
	@Enumerated(EnumType.STRING)
	PaymentType payType;

	public Payment() {
	}

	public Payment(@NotEmpty String number, @NotEmpty PaymentType payType, String csv) {
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

	public PaymentType getPayType() {
		return payType;
	}

	public void setPayType(PaymentType payType) {
		this.payType = payType;
	}

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}
	
	
}