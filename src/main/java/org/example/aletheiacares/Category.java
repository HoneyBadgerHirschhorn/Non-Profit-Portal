package org.example.aletheiacares;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<HelpRequest> helpRequests;

    @ManyToMany(mappedBy = "categories")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "category_gift",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "gift_id")
    )
    private List<SpiritualGift> spiritualGifts;



    // Constructors
    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HelpRequest> getHelpRequests() {
        return helpRequests;
    }

    public void setHelpRequests(List<HelpRequest> helpRequests) {
        this.helpRequests = helpRequests;
    }
}
