package korchagin.model;

import korchagin.model.program.Program;

import java.util.*;

public class RadioStation {
    private List<Program> programs;
    private Program current;

    public RadioStation() {
        this.programs = new ArrayList<>();
    }
}
