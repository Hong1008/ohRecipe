package remasterWithJpa.ohRecipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import remasterWithJpa.ohRecipe.repository.IrdntTypeRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe/*")
public class RecipeController {

    private final IrdntTypeRepository irdntTypeRepository;

    @GetMapping("home")
    public String home(Model model){
        model.addAttribute("aList",irdntTypeRepository.findAll());
        model.addAttribute("tList",irdntTypeRepository.findDistinctTypeNmList());

        return "home";
    }
}
