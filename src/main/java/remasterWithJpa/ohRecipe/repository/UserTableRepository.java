package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remasterWithJpa.ohRecipe.domain.UserTable;

public interface UserTableRepository extends JpaRepository<UserTable,String> {

}
