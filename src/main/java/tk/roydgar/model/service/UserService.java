package tk.roydgar.model.service;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.user.User;
import tk.roydgar.model.entity.temporary.LoginData;
import tk.roydgar.model.repository.UserRepository;
import tk.roydgar.util.HashUtil;
import tk.roydgar.util.SmtpMailSender;


import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static tk.roydgar.util.ResponseEntityUtil.responseEntityFromOptional;
import static tk.roydgar.util.constants.HeaderMessages.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;
    private SmtpMailSender smtpMailSender;
    private Logger logger;

    @Transactional(readOnly = true)
    public ResponseEntity findById(Long id) {
        return responseEntityFromOptional(userRepository.findById(id));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> login(LoginData loginData) {
        String email = loginData.getEmail().toLowerCase();
        String password = loginData.getPassword();

        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            logger.info("login() call; FAILURE ; user with given email doesn't exist; " +
                    "loginData = " + loginData);
            return new ResponseEntity<>(
                    ImmutableMap.of(HEADER_KEY, USER_EMAIL_DOESNT_EXIST), UNAUTHORIZED);
        }

        User loggedUser = user.get();


        if (loggedUser.getStatus() != User.Status.CONFIRMED) {
            logger.info("login() call; FAILURE ; user didn't confirm his email; " +
                    "loginData = " + loginData +"l; user = " + loggedUser);
            return new ResponseEntity<>(
                    ImmutableMap.of(HEADER_KEY, USER_EMAIL_WASNT_CONFIRMED), UNAUTHORIZED);
        }

        if (HashUtil.check(email.concat(password), loggedUser.getPassword())) {
            logger.info("login() call; SUCCESS ; loginData = " + loginData +"; loggedUser = " + loggedUser);
            return new ResponseEntity<>(loggedUser, OK);
        }

        logger.info("login() call; FAILURE ; password is incorrect;" +
                "loginData = " + loginData);
        return new ResponseEntity<>(ImmutableMap.of(HEADER_KEY, USER_WRONG_PASSWORD), UNAUTHORIZED);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>(ImmutableMap.of(HEADER_KEY, USER_EXIST), CONFLICT);
        }

        user.setPassword(HashUtil.hash(user.getEmail().concat(user.getPassword())));
        user.setEmail(user.getEmail().toLowerCase());
        User savedUser = userRepository.save(user);

        smtpMailSender.send(user.getEmail(),
                "Workshop Master: Email Confirmation"
                    ,   "Please, confirm your email following next link: " +
                        "https://workshop-master-server.herokuapp.com/user/confirmation/"
                        + savedUser.getId() + "/" +
                        savedUser.getName());

        logger.info("regiser() call; SUCCESS; savedUser = " + savedUser);
        return new ResponseEntity<>(savedUser, OK);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> confirmEmail(Long userId, String hash) {
        Optional<User> tempUser = userRepository.findById(userId);

        if (!tempUser.isPresent()) {
            logger.info("confirmEmail() call; FAILURE; incorrect user id; userId = "
                + userId + "; hash = " + hash);
            return new ResponseEntity<>(ImmutableMap.of(HEADER_KEY, USER_NOT_FOUND), BAD_REQUEST);
        }

        User user = tempUser.get();

        if (hash.equals(user.getName()) && user.getStatus() == User.Status.GUEST) {
            user.setStatus(User.Status.CONFIRMED);
            User savedUser = userRepository.save(user);
            logger.info("confrimEmail() call; SUCCESS; confirmedUser = " + user);
            return new ResponseEntity<>(savedUser, OK);
        }

        logger.info("confirmEmail() call; FAILURE; incorrect hash or already confirmed; userId = "
                + userId + "; hash = " + hash + "; user = " + user);
        return new ResponseEntity<>(ImmutableMap.of(HEADER_KEY, EMAIL_CONFIRMATION_ERROR), BAD_REQUEST);
    }

}
