package randomlovers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import randomlovers.persistence.model.Topic;
import randomlovers.persistence.model.User;
import randomlovers.services.UserService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> listTopics(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       Boolean logged = userService.validateUser(user.getUsername(), user.getPassword());
        if(!logged){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(logged, HttpStatus.OK);
    }
}
