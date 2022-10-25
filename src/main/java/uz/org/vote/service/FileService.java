package uz.org.vote.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import uz.org.vote.message.ResponseMessage;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {
    void init();
    void save(MultipartFile file,Integer candidateId);
    void saveElectionFile(MultipartFile file, Integer electionId);
    Resource load(String fileName);
    void deleteAll();
    Stream<Path> loadAll();
}
