package uz.org.vote.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.Candidate;
import uz.org.vote.model.User;
import uz.org.vote.service.CandidateService;
import uz.org.vote.utils.PswEncoderVsDecoder;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public ResponseMessage getAll() {
        return null;
    }

    @Override
    public ResponseMessage save(Candidate candidate) {
        Integer id = jdbcTemplate.queryForObject("insert into candidate (first_name, last_name, age, job, election_id, email, phone_number) values (?,?,?,?,?,?,?) returning id", Integer.class,
                candidate.getFirstName(), candidate.getLastName(), candidate.getAge(), candidate.getJob(), candidate.getElectionId(), candidate.getEmail(),candidate.getPhoneNumber());
        return id>0 ? ResponseMessage.added(id) : ResponseMessage.notSaved();
    }

    @Override
    public ResponseMessage delete(int id) {
        int update = jdbcTemplate.update("delete from candidate where id = ?", id);
        return update > 0 ? ResponseMessage.deleted() : ResponseMessage.notFound();
    }

    @Override
    public ResponseMessage update(Candidate candidate) {
        return null;
    }

    @Override
    public ResponseMessage getAllCandidate() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select c.first_name,c.last_name,c.email,c.age,c.job, cf.file_name,'http://localhost:8080/candidate/' || cf.file_url as file_url " +
                "from candidate c join candidatefile cf on c.id = cf.candidate_id");
        return ResponseMessage.find(list);
    }

    @Override
    public ResponseMessage getAllCandidateByElection(int id) {
        List<Map<String, Object>> candidates = jdbcTemplate.queryForList("select e.name,c.first_name,c.last_name,c.email,c.age,c.job, cf.file_name,'http://localhost:8080/candidate/' || cf.file_url as file_url " +
                "from candidate c join candidatefile cf on c.id = cf.candidate_id join election e on e.id = c.election_id where election_id = ? and c.is_active = true", id);
        return ResponseMessage.find(candidates);
    }

//    @Override
//    public Integer findCandidateByKey(String key) {
//        String[] info = PswEncoderVsDecoder.CandidateKeyDecoder(key);
//        try {
//            Candidate candidate = jdbcTemplate.queryForObject("select * from candidate where first_name = ? and last_name = ?", Candidate.class, info[0], info[1]);
//            if (user.getIsAdmin()){
//                return 1;
//            }else{
//                return 0;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return -1;
//        }
//    }
}