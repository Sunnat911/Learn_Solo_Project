package uz.pdp.sololearnuzversion.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.user.UserEntity;
import uz.pdp.sololearnuzversion.repository.UserRepository;

import java.util.Optional;



@Service
public class ApplicationUserDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty())
            throw new UsernameNotFoundException(username + " shu userimiz topilmadi, va code shu joyda sindi ");

        return optionalUserEntity.get();
    }
}
