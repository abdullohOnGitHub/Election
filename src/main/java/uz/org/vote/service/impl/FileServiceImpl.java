package uz.org.vote.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.org.vote.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final JdbcTemplate jdbcTemplate;
    private final Path root = Paths.get("uploads");
    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(MultipartFile file, Integer candidateId) {
        try {
            Date date = new Date();
            String url = date.getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            Files.copy(file.getInputStream(),root.resolve(date.getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."))));
            jdbcTemplate.update("insert into candidatefile (file_size, file_name, file_url, content_type, candidate_id) values " +
                    "(?,?,?,?,?)", file.getSize(), file.getOriginalFilename(),  url, file.getContentType(), candidateId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveElectionFile(MultipartFile file, Integer electionId) {
        try {
            Date date = new Date();
            String url = date.getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            Files.copy(file.getInputStream(),root.resolve(date.getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."))));
            jdbcTemplate.update("insert into electionfile (file_size, file_name, file_url, content_type, candidate_id) values " +
                    "(?,?,?,?,?)", file.getSize(), file.getOriginalFilename(),  url, file.getContentType(), electionId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Resource load(String fileName) {
        Path path = root.resolve(fileName);
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
