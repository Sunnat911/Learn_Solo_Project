package uz.pdp.sololearnuzversion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.user.UserEntity;
import uz.pdp.sololearnuzversion.service.user.ApplicationUserDetailService;

@Service
public class CurrentUser {
    private final ApplicationUserDetailService applicationUserDetailService;

    @Autowired
    public CurrentUser(ApplicationUserDetailService applicationUserDetailService) {
        this.applicationUserDetailService = applicationUserDetailService;
    }

    public UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication != null) {
            String username = (String) authentication.getPrincipal();
            UserDetails userDetails = applicationUserDetailService.loadUserByUsername(username);
            return (UserEntity) userDetails;
        }
        return null;

    }
}
