package remasterWithJpa.ohRecipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import remasterWithJpa.ohRecipe.repository.UserTableRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final UserTableRepository userRepository;

    

}
