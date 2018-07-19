package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.user.User;
import tk.roydgar.model.entity.temporary.LoginData;
import tk.roydgar.model.repository.UserRepository;
import tk.roydgar.util.HashUtil;
import tk.roydgar.util.SmtpMailSender;


import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;
    private SmtpMailSender smtpMailSender;
    private Logger logger;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User login(LoginData loginData) {
        String email = loginData.getEmail();
        String password = loginData.getPassword();

        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            return null;
        }

        User loggedUser = user.get();

        if (loggedUser.getStatus() != User.Status.CONFIRMED) {
            return null;
        }

        if (HashUtil.check(email.concat(password), loggedUser.getPassword())) {
            return loggedUser;
        }

        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public User register(User user) {
        if (user == null) {
            return null;
        }

//        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//            return null;
//        }

        user.setPassword(HashUtil.hash(user.getEmail().concat(user.getPassword())));
        User savedUser = userRepository.save(user);

        smtpMailSender.send(user.getEmail(),
                "Workshop Master: Email Confirmation"
                    ,   "Please, confirm your email following next link: " +
                        "https://workshop-master-server.herokuapp.com/user/confirmation/"
                        + savedUser.getId() + "/" +
                        savedUser.getName());
        return savedUser;
    }

    @Transactional(rollbackFor = Exception.class)
    public User confirmEmail(Long userId, String hash) {
        Optional<User> tempUser = userRepository.findById(userId);

        if (!tempUser.isPresent()) {
            return null;
        }

        User user = tempUser.get();

        if (hash.equals(user.getName())) {
            user.setStatus(User.Status.CONFIRMED);
            userRepository.save(user);
            return user;
        }

        return null;
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

}
