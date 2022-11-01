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
import uz.org.vote.model.Election;
import uz.org.vote.service.ElectionService;
import uz.org.vote.service.FileService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/election")
@RequiredArgsConstructor
public class ElectionController {
    @Autowired
    private final ElectionService electionService;
    @Autowired
    private final FileService fileService;

    @GetMapping
    public ResponseMessage getAll(){
        return electionService.getAll();
    }

    @PostMapping("/add")
    public ResponseMessage saveElection(@RequestBody @Valid Election election){
        return electionService.save(election);
    }
    @PostMapping("/add/uploadFile/{election_id}")
    public ResponseMessage saveElectionFileToDB(@PathVariable int election_id, @RequestParam MultipartFile file){
        fileService.saveElectionFile(file,election_id);
        return ResponseMessage.added();
    }

    @GetMapping("{path}")
    public ResponseEntity<Resource> getFile(@PathVariable String path) throws IOException {
        Resource resource = fileService.load(path);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(resource.getInputStream()));
    }

    @DeleteMapping
    public ResponseMessage deleteElectionById(@RequestParam int id){
        return electionService.delete(id);
    }

//    @GetMapping("{id}") // TODO metod o'zgartirish
//    public ResponseMessage getElectionById(@PathVariable int id){
//        return electionService.getElectionById(id);
//    }
}
