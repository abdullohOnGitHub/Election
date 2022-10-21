package uz.org.vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.Election;
import uz.org.vote.service.ElectionService;

@RestController
@RequestMapping("/election")
@RequiredArgsConstructor
public class ElectionController {
    @Autowired
    private final ElectionService electionService;

    @GetMapping
    public ResponseMessage getAll(){
        return electionService.getAll();
    }

    @PostMapping("/add")
    public ResponseMessage saveElection(@RequestBody Election election){
        return electionService.save(election);
    }

    @DeleteMapping
    public ResponseMessage deleteElectionById(@RequestParam int id){
        return electionService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseMessage getElectionById(@PathVariable int id){
        return electionService.getElectionById(id);
    }
}
