package uz.org.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.org.vote.service.FileService;

@SpringBootApplication
public class VoteApplication implements CommandLineRunner {
    @Autowired
    FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(VoteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (Boolean.parseBoolean(args[0])){
            fileService.init();
        }
    }
}
