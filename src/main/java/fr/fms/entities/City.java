package fr.fms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor(force = true)
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private Collection<Hotel> hotels;

}
