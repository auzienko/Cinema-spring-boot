package edu.school21.cinema.services;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder bCryptEncoder;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository, PasswordEncoder bCryptEncoder) {
        this.administratorRepository = administratorRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public Optional<Administrator> signUp(Administrator entity) {
        Optional<Administrator> tmp = administratorRepository.findByEmailIgnoreCase(entity.getEmail());
        if (!tmp.isPresent()) {
            entity.setPassword(bCryptEncoder.encode(entity.getPassword()));
            administratorRepository.save(entity);
            tmp = administratorRepository.findByEmailIgnoreCase(entity.getEmail());
            return tmp;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Administrator> signIn(String email, String password) {
        Optional<Administrator> tmp = administratorRepository.findByEmailIgnoreCase(email);
        if (tmp.isPresent()) {
            if (bCryptEncoder.matches(password, tmp.get().getPassword())) {
                return tmp;
            }
        }
        return Optional.empty();
    }
}
