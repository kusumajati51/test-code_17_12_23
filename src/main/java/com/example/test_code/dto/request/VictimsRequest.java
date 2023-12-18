package com.example.test_code.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.annotations.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VictimsRequest {
    @Expose
    @NotEmpty(message = "Please Provide name ")
    @NotNull(message = "Please Provide name ")
    private String name;

    @JsonAlias("death_age")
    @Expose
    @NotNull(message = "Please Provide death_age ")
    private Integer deathAge;



    @JsonAlias("murdered_year")
    @Expose
    @NotNull(message = "Please Provide murderer year ")
    private Integer murderedYear;
}
