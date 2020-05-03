package amazon.layer.domainn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="product")
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(nullable=false)
    @NotEmpty()
    private String name;
    @Column(nullable=false)
    String creationDateTime;
    private List<Review> reviews;
    public Product(){}

    public Product(@NotEmpty String name){
        super();
        this.name = name;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yy HH:mm:ss");
        this.creationDateTime = dtf.format(LocalDate.now());
        this.reviews = null;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getCreationDateTime()
    {
        return creationDateTime;
    }
    public void CreationDateTime(String creationDateTime)
    {
        this.creationDateTime = creationDateTime;
    }
    public List<Review> getReviews()
    {
        return reviews;
    }
    public void setReviews(List<Review> Reviews)
    {
        this.reviews = reviews;
    }
}