package amazon.layer.domainn;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

enum paymentType{
    Visa,
    MasterCard,
    Prepaid
};

@Entity
public class Payment {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(nullable=false)
    @NotEmpty()
    private Integer number;
    @Column(nullable=false)
    paymentType payType;
    public Payment(){}

    public Payment(@NotEmpty Integer number, @NotEmpty paymentType payType){
        super();
        this.number = number;
        this.payType = payType;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getNumber()
    {
        return number;
    }
    public void setNumber(Integer number)
    {
        this.number = number;
    }
    public paymentType getPayType()
    {
        return payType;
    }
    public void setPayType(paymentType payType)
    {
        this.payType = payType;
    }
}