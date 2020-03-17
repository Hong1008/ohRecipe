package remasterWithJpa.ohRecipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import remasterWithJpa.ohRecipe.repository.IrdntTypeRepository;

import java.util.List;

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

    @ResponseBody
    @PostMapping("viewResult")
    public String viewResult(@RequestParam(value = "irdntNms[]") List<String> irdntNms){
        System.out.println("irdntNms.size() = " + irdntNms.size());
        return null;
    }
}
