package uz.org.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.User;
import uz.org.vote.service.UserService;
import uz.org.vote.utils.PswEncoderVsDecoder;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseMessage getAll() {
        List<Map<String, Object>> users = jdbcTemplate.queryForList("select * from users where is_active = true and is_admin = false");
        return ResponseMessage.find(users);
    }

    @Override
    public ResponseMessage save(User user) {
        String encodePsw = PswEncoderVsDecoder.EncodePsw(user.getPassword());
        int save = jdbcTemplate.update("insert into users(first_name, last_name, age, username, email, password) values (?,?,?,?,?,?)",
                user.getFirstName(), user.getLastName(), user.getAge(), user.getUsername(), user.getEmail(), encodePsw);
        return save > 0 ? ResponseMessage.added(Base64.getEncoder().encodeToString((user.getUsername() + "&" + user.getPassword()).getBytes())) : ResponseMessage.notSaved();
    }

    @Override
    public ResponseMessage delete(int id) {
        int delete = jdbcTemplate.update("update users set is_active = false where id = ? and is_admin = false", id);
        return ResponseMessage.deleted();
    }

    @Override
    public ResponseMessage update(User user) {
        return null;
    }

    @Override
    public ResponseMessage getUsersByElectionId(int id) {
        List<Map<String, Object>> usersByElectionId = jdbcTemplate.queryForList("select u.first_name,u.last_name,e.name from userbyelectionid ue join election e on e.id = ue.election_id join users u on u.id = ue.users_id and u.is_admin = false where ue.election_id = ?",id);
        return ResponseMessage.find(usersByElectionId);
    }

    @Override
    public ResponseMessage login(String username, String password) {
//            User user5 = jdbcTemplate.queryForObject("select is_admin from users where username=? and password=?", User.class, username, psw);
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select is_admin from users where username=? and password=?", username, PswEncoderVsDecoder.EncodePsw(password));
        return list.size() > 0 ? ResponseMessage.find(Base64.getEncoder().encodeToString((username + "&" + password).getBytes())) : ResponseMessage.notFound();
//        }catch (EmptyResultDataAccessException e){
    }

    @Override
    public Integer findByUsernameAndPassword(String key) {
        String[] keyDecoder = PswEncoderVsDecoder.KeyDecoder(key);
        try {
            User user = jdbcTemplate.queryForObject("select is_admin from users where username = ? and password = ?", User.class, keyDecoder[0], PswEncoderVsDecoder.EncodePsw(keyDecoder[1]));
            if (user.getIsAdmin()){
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }
}
