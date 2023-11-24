package com.savewise.rewear.platform.iam.infrastructure.authorization.sfs.services;

import com.savewise.rewear.platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.savewise.rewear.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/***
 * This class is used by Spring Security to authenticate and authorize user
 * using username and password.
 */
@Service("defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /***
     * This method gets the user from the database and returns a UserDetails object
     * using username.
     * @param username username of the user
     * @return UserDetails object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}