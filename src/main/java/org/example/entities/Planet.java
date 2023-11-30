package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @Pattern(regexp = "[A-Z0-9]+", message = "Invalid planet ID")
    @Size(max = 10, message = "Planet ID length should be at most 10")
    private String id;

    @Column
    @NotBlank(message = "Planet name cannot be blank")
    @Size(max = 500, message = "Planet name length should be at most 500")
    private String name;

    @OneToMany(mappedBy = "fromPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> fromPlanetTickets = new HashSet<>();

    @OneToMany(mappedBy = "toPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> toPlanetTickets = new HashSet<>();

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fromPlanetTickets=" + fromPlanetTickets +
                ", toPlanetTickets=" + toPlanetTickets +
                '}';
    }
}
