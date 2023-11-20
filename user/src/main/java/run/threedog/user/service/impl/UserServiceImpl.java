package run.threedog.user.service.impl;

import org.springframework.stereotype.Service;
import run.threedog.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String test() {
        return "hello SpringBoot";
    }
}
