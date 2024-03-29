package edu.school21.cinema.services;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.Role;
import edu.school21.cinema.models.UserStatus;
import edu.school21.cinema.repositories.CinemaUserRepository;
import edu.school21.cinema.security.CinemaUserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CinemaUserServiceImpl implements CinemaUserService {
    private final CinemaUserRepository userRepository;
    private final PasswordEncoder bCryptEncoder;

    public CinemaUserServiceImpl(CinemaUserRepository userRepository, PasswordEncoder bCryptEncoder) {
        this.userRepository = userRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CinemaUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CinemaUserPrincipal(user);
    }


    @Override
    public Optional<CinemaUser> signUp(CinemaUser entity) {
        Optional<CinemaUser> tmp = userRepository.findByEmailIgnoreCase(entity.getEmail());
        if (!tmp.isPresent()) {
            entity.setPassword(bCryptEncoder.encode(entity.getPassword()));
            entity.setRole(Role.USER);
            entity.setStatus(UserStatus.NOT_CONFIRMED);
            entity.setPhoneNum(entity.getPhoneNum());
            userRepository.save(entity);
            tmp = userRepository.findByEmailIgnoreCase(entity.getEmail());
            return tmp;
        }
        return Optional.empty();
    }



    @Override
    public Optional<CinemaUser> signIn(String email, String password) {
        Optional<CinemaUser> tmp = userRepository.findByEmailIgnoreCase(email);
        if (tmp.isPresent()) {
            if (bCryptEncoder.matches(password, tmp.get().getPassword())) {
                return tmp;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<CinemaUser> save(CinemaUser entity) {
        CinemaUser user = userRepository.save(entity);
        return Optional.of(user);
    }

    @Override
    public Optional<CinemaUser> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
}
