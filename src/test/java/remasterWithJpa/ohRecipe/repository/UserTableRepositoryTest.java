package remasterWithJpa.ohRecipe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserTableRepositoryTest {

    @Autowired UserTableRepository userTableRepository;



}