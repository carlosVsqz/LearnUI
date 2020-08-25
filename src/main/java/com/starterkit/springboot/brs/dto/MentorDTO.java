package com.starterkit.springboot.brs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorDTO {

    private UUID mentorId;

    private String name;

    private String pictureUrl;

}
