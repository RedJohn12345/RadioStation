package korchagin.model.program;

import korchagin.model.user_request.UserRequest;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class UserRequestsProgram extends Program    {

    private List<UserRequest> requests;

    public UserRequestsProgram(Date date) {
        super(date);
        requests = new LinkedList<>();
    }

    public List<UserRequest> getRequests() {
        return requests;
    }

}
