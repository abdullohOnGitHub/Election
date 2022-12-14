package uz.org.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.Election;
import uz.org.vote.service.ElectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ElectionServiceImpl implements ElectionService{
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ElectionServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseMessage getAll() {
        List<Map<String, Object>> elections = jdbcTemplate.queryForList("select e.name,'http://localhost:8080/election/' || e2.file_url as file_url from election e join electionfile e2 on e.id = e2.election_id where e.is_active = true");
        return ResponseMessage.find(elections);
    }

    @Override
    public ResponseMessage save(Election election) {
        Integer electionId = jdbcTemplate.queryForObject("insert into election(name,is_active) values (?,?) returning id", Integer.class, election.getName(), true);
        return electionId>0 ? ResponseMessage.added(electionId) : ResponseMessage.notSaved();
    }

    @Override
    public ResponseMessage delete(int id) {
        int update = jdbcTemplate.update("update election set is_active = false where id = ?", id);
        return update > 0 ? ResponseMessage.deleted() : ResponseMessage.accessDenied();
    }

    @Override
    public ResponseMessage update(Election election) {
        return null;
    }

    @Override
    public ResponseMessage getElectionById(int id) {
        try {
            Election election = jdbcTemplate.queryForObject("select name from election where is_active = true and id = ?", Election.class, id);
            return ResponseMessage.find(election.getName());
        }catch (EmptyResultDataAccessException e){
            return ResponseMessage.notFound();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
