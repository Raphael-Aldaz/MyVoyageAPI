package fr.fms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.sun.istack.NotNull;

import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor(force = true)
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String picture;
    @NotNull
    private Integer rate;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;
}
