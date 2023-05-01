package korchagin.model;

import korchagin.model.user_request.UserRequest;

public class ModulationStep {

    private UserRequest request;

    public ModulationStep(UserRequest request) {
        this.request = request;
    }

    public UserRequest getRequest() {
        return request;
    }

    public void setRequest(UserRequest request) {
        this.request = request;
    }
}
