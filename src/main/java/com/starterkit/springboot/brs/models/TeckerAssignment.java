package com.starterkit.springboot.brs.models;

import com.starterkit.springboot.brs.enumerable.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tecker_by_deliverable")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeckerAssignment implements Serializable {

    private static final long serialVersionUID = 1231247459935466311L;

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "deliverable_id", nullable = false)
    private UUID deliverableId;

    @Column(name = "tecker_id", nullable = false)
    private UUID teckerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 100, nullable = false)
    private StatusType status;
}
