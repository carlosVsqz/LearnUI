package com.starterkit.springboot.brs.services;


import com.starterkit.springboot.brs.dto.TeckerDTO;

import java.util.List;
import java.util.UUID;

public interface ParentService {

    void assignToParent(UUID parentId, List<UUID> teckers);

    void assignTeckersToParent(UUID parentId, List<UUID> teckers);

    List<TeckerDTO> getTeckersByParent(UUID parentId);

    List<TeckerDTO> getTeckersByParentLogged(UUID parentId);
}
