package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import remasterWithJpa.ohRecipe.domain.IrdntType;

import java.util.List;

public interface IrdntTypeRepository extends JpaRepository<IrdntType,String> {

    @Query("select it.irdntNm from IrdntType it")
    List<String> findIrdntNmList();

    @Query("select distinct it.typeNm from IrdntType it")
    List<String> findDistinctTypeNmList();
}
