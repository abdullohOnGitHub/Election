package uz.org.vote.service;

import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.Election;

import java.util.Optional;

public interface ElectionService extends BasicService<Election> {
    ResponseMessage getElectionById(int id);
}
