package korchagin.model;

import korchagin.model.program.Program;
import korchagin.model.user_request.UserRequest;

import java.util.*;

public class Modulation {
    private List<ModulationStep> steps;
    private Map<UserRequest, Boolean> requests;
    private List<Program> programs;

    public Modulation() {
        this.requests = new HashMap<>();
        this.programs = new ArrayList<>();
    }

    public Map<UserRequest, Boolean> getRequests() {
        return requests;
    }

    public List<ModulationStep> getSteps() {
        return steps;
    }

    public List<Program> getPrograms() {
        return programs;
    }

}
