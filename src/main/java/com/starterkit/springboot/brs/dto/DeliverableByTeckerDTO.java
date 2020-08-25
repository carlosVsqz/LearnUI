package com.starterkit.springboot.brs.dto;

import com.starterkit.springboot.brs.enumerable.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverableByTeckerDTO {

    private UUID id;

    private String title;

    private String dueDate;

    private StatusType status;
}
