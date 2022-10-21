package uz.org.vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.org.vote.message.ResponseMessage;
import uz.org.vote.model.User;
import uz.org.vote.service.CandidateService;
import uz.org.vote.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;


    @GetMapping
    public ResponseMessage getAll(@RequestHeader String key){
        Integer code = userService.findByUsernameAndPassword(key);
        if (code == 0){
            return ResponseMessage.accessDenied();
        }
        return userService.getAll();
    }

    @PostMapping("/signUp")
    public ResponseMessage saveData(@RequestBody @Valid User user){
        return userService.save(user);
    }


    @DeleteMapping
    public ResponseMessage deleteById(@RequestHeader String key, @RequestParam int id){
        Integer code = userService.findByUsernameAndPassword(key);
        if (code == 0){
            return ResponseMessage.accessDenied();
        }
        return userService.delete(id);
    }

    @GetMapping("/election{id}")
    public ResponseMessage getUsersByElectionId(@RequestHeader String key,@PathVariable int id){
        Integer code = userService.findByUsernameAndPassword(key);
        if (code == 0){
            return ResponseMessage.accessDenied();
        }
        return userService.getUsersByElectionId(id);
    }

    @PostMapping("/login")
    public ResponseMessage findByUsernameAndPassword(@RequestParam @NotNull String username, @RequestParam @NotNull String password){
        return userService.login(username,password);
    }


}
