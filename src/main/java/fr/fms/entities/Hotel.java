package fr.fms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import com.sun.istack.NotNull;
import org.apache.catalina.User;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
@Entity
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser users;

}
