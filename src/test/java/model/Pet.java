package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private PetStatus status;

    public Pet(long id) {
        this.id = id;
        this.category = new Category(1, "dog");
        this.name="Bobik";
        this.tags = List.of(new Tag(1, "friendly"));
        this.photoUrls = List.of("https://petstore.swagger.io/v2/pet/" + id + "/dog.jpg");
        this.status = PetStatus.available;
    }
}
