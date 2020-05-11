package amazon.layer.domainn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="review")
public class Review {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column()
    @NotEmpty()
    private String comment;
    @Column()
    LocalDate creationDateTime;
    @Column()
    Boolean accepted;
    
    public Review(){}

    public Review(@NotEmpty String comment){
        super();
        this.comment = comment;
        this.creationDateTime =LocalDate.now();
        this.accepted = false;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getComment()
    {
        return comment;
    }
    public void setComment(String comment)
    {
        this.comment = comment;
    }
    public LocalDate getCreationDateTime()
    {
        return creationDateTime;
    }
    public void CreationDateTime(LocalDate creationDateTime)
    {
        this.creationDateTime = creationDateTime;
    }
    public Boolean getAccepted()
    {
        return accepted;
    }
    public void setAccepted(Boolean accepted)
    {
        this.accepted = accepted;
    }
}