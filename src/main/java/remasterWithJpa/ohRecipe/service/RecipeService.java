package remasterWithJpa.ohRecipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import remasterWithJpa.ohRecipe.repository.IrdntTypeRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

    private final IrdntTypeRepository irdntTypeRepository;


}
