package org.example.aletheiacares;



import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "spiritual_gifts",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class SpiritualGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "scripture_reference")
    private String scriptureReference;

    /**
     * Users who have this gift.
     * Mapped by the `gifts` field in User.
     */
    @ManyToMany(mappedBy = "gifts")
    private Set<User> users = new HashSet<>();

    /**
     * Categories to which this gift belongs.
     * Owning side of the category_gift join table.
     */
    @ManyToMany
    @JoinTable(
            name = "category_gift",
            joinColumns = @JoinColumn(name = "gift_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public SpiritualGift() {}

    public SpiritualGift(String name, String scriptureReference) {
        this.name = name;
        this.scriptureReference = scriptureReference;
    }

    // —— Getters & Setters —— //

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

    public String getScriptureReference() {
        return scriptureReference;
    }

    public void setScriptureReference(String scriptureReference) {
        this.scriptureReference = scriptureReference;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
