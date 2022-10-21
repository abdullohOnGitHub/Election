package uz.org.vote.service;

import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.User;

public interface UserService extends BasicService<User>{
    ResponseMessage getUsersByElectionId(int id);
    ResponseMessage login(String username, String password);
    public Integer findByUsernameAndPassword(String key);=
}
