package remasterWithJpa.ohRecipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import remasterWithJpa.ohRecipe.repository.IrdntTypeRepository;
import remasterWithJpa.ohRecipe.repository.PrimaryRepository;
import remasterWithJpa.ohRecipe.repository.dto.PrimViewDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe/*")
public class RecipeController {

    private final IrdntTypeRepository irdntTypeRepository;
    private final PrimaryRepository primaryRepository;

    @GetMapping("home")
    public String home(Model model){
        model.addAttribute("aList",irdntTypeRepository.findAll());
        model.addAttribute("tList",irdntTypeRepository.findDistinctTypeNmList());

        return "home";
    }

    @ResponseBody
    @GetMapping("viewResult")
    public ModelAndView viewResult(@RequestParam(value = "irdntNms[]") List<String> irdntNms,
                                   @RequestParam(required = false, defaultValue = "0") int page,
                                   ModelAndView modelAndView){
        Page<PrimViewDto> results = primaryRepository.viewResult(irdntNms, PageRequest.of(page, 1));

        modelAndView.addObject("viewResult",results.getContent().get(0));
        modelAndView.addObject("page",results);
        modelAndView.setViewName("ajax/viewResult");
        return modelAndView;
    }
}
