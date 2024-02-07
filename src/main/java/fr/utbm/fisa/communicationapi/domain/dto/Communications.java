package fr.utbm.fisa.communicationapi.domain.dto;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Communication;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Builder
@Getter
public class Communications {
    private ArrayList<Communication> communications;
}
