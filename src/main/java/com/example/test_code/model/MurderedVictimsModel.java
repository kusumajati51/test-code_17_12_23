package com.example.test_code.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "murdered_victims")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class MurderedVictimsModel {

    @Id
    @GeneratedValue
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @NotEmpty(message = "Please provide a name")
    @Expose
    @NonNull
    private String name;

    @JsonAlias("death_age")
    @NotNull(message = "Please provide a age")
    @Column(name = "death_age")
    @Expose
    @NonNull
    private Integer deathAge;

    @JsonAlias("birth_year")
    @NotNull(message = "Please provide a age")
    @Min(value = 0)
    @Expose
    @Column(name = "birth_year")
    @NonNull
    private Integer birthYear;

    @JsonAlias("murdered_year")
    @NotNull(message = "Please provide a age")
    @Expose
    @Column(name = "murdered_year")
    @NonNull
    private Integer murderedYear;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

}
