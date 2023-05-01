package korchagin.model;

import korchagin.model.program.Program;

import java.util.*;

public class RadioStation {
    private List<Program> programs;
    private Program current;
    private Modulation modulation;
    private boolean modulationWork;

    public RadioStation() {
        this.programs = new ArrayList<>();
    }
}
