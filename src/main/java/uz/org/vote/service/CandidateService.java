package uz.org.vote.service;

import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.Candidate;

public interface CandidateService extends BasicService<Candidate> {
//    Integer findCandidateByKey(String key);
    ResponseMessage getAllCandidate();

    ResponseMessage getAllCandidateByElection(int id);
}
