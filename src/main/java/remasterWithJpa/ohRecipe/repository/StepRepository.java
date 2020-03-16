package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remasterWithJpa.ohRecipe.domain.Step;

public interface StepRepository extends JpaRepository<Step,Integer> {
}
