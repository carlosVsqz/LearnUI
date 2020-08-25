package com.starterkit.springboot.brs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverableDTO {

    private UUID id;

    private String dueDate;

    private String title;

    private String description;

    private UUID batchId;
}
