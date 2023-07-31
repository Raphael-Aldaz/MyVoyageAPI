package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.sun.istack.NotNull;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor(force = true)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    @NotNull
    private String picture;

    @NotNull
    private Integer note;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @ManyToOne
    private City city;
}
