package uz.org.vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.Candidate;
import uz.org.vote.service.CandidateService;
import uz.org.vote.service.FileService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {
    @Autowired
    private final CandidateService candidateService;
    @Autowired
    private final FileService fileService;

    @PostMapping("/register")
    public ResponseMessage saveDB(@RequestBody @Valid Candidate candidate){
        return candidateService.save(candidate);
    }

    @PostMapping("/register/uploadFile/{candidate_id}")
    public ResponseMessage saveFileDB(@PathVariable int candidate_id, @RequestParam MultipartFile file){
        fileService.save(file,candidate_id);
        return ResponseMessage.added(null);
    }

    @DeleteMapping
    public ResponseMessage deleteCandidate(@RequestParam int candidate_id){
        return candidateService.delete(candidate_id);
    }

    @GetMapping
    public ResponseMessage getAllCandidate(){
        return candidateService.getAllCandidate();
    }

    @GetMapping("{path}")
    public ResponseEntity<Resource> getFile(@PathVariable String path) throws IOException {
        Resource resource = fileService.load(path);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(resource.getInputStream()));
    }

    @GetMapping("/election_id/{id}")
    public ResponseMessage getAllCandidateByElectionId(@PathVariable int id){
        return candidateService.getAllCandidateByElection(id);
    }
    //TODO election process left. Crud for election. Other important cases.
}
