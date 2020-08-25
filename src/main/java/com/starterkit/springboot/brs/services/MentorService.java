package com.starterkit.springboot.brs.services;


import com.starterkit.springboot.brs.dto.AssignTeckersDTO;
import com.starterkit.springboot.brs.dto.TeckerDTO;

import java.util.List;
import java.util.UUID;

public interface MentorService {

    void assignTeckersToMentor(UUID mentorId, AssignTeckersDTO teckersToAssign);

    void assignToMentor(UUID mentorId, List<UUID> teckersToAssign);

    void unassignFromMentor(UUID mentorId, List<UUID> teckerToUnassing);

    List<TeckerDTO> getTeckersByMentor(UUID mentorId);
}
