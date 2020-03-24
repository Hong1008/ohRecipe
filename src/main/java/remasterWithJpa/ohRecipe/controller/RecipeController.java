package remasterWithJpa.ohRecipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import remasterWithJpa.ohRecipe.controller.dto.PrimSearchDto;
import remasterWithJpa.ohRecipe.domain.Primary;
import remasterWithJpa.ohRecipe.domain.RecipeComment;
import remasterWithJpa.ohRecipe.domain.code.BoardType;
import remasterWithJpa.ohRecipe.domain.code.Nation;
import remasterWithJpa.ohRecipe.repository.IrdntTypeRepository;
import remasterWithJpa.ohRecipe.repository.PrimaryQuerySupport;
import remasterWithJpa.ohRecipe.repository.PrimaryRepository;
import remasterWithJpa.ohRecipe.repository.RecipeCommentRepository;
import remasterWithJpa.ohRecipe.repository.dto.CommentViewDto;
import remasterWithJpa.ohRecipe.repository.dto.IrdntTypeViewDto;
import remasterWithJpa.ohRecipe.repository.dto.PrimViewDto;
import remasterWithJpa.ohRecipe.service.RecipeService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe/*")
public class RecipeController {

    private final IrdntTypeRepository irdntTypeRepository;
    private final PrimaryRepository primaryRepository;
    private final RecipeCommentRepository commentRepository;
    private final RecipeService recipeService;

    @GetMapping("home")
    public String home(Model model){
        List<IrdntTypeViewDto> irdntTypes = irdntTypeRepository.findAll().stream().map(IrdntTypeViewDto::new).collect(Collectors.toList());

        model.addAttribute("aList",irdntTypes);
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
        modelAndView.addObject("total",results.getTotalPages());
        modelAndView.addObject("page",results.getNumber());
        modelAndView.setViewName("ajax/viewResult");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("comList")
    public ModelAndView comList(String boardCode, Long recipeId,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "comTime") String sort,
                                ModelAndView modelAndView){
        PageRequest pageable = PageRequest.of(page, 5);
        Page<CommentViewDto> comList = commentRepository.commentList(BoardType.valueOf(boardCode), recipeId, pageable, sort);
        modelAndView.addObject("comList",comList.getContent());
        modelAndView.addObject("total",comList.getTotalElements());
        modelAndView.addObject("page",comList.getNumber());
        modelAndView.setViewName("ajax/comment");
        return modelAndView;
    }

    @GetMapping("list")
    public String recipeList(PrimSearchDto primSearchDto, Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "DESC") String direction,
                             @RequestParam(defaultValue = "primViews") String sort){
        PageRequest pageRequest = PageRequest.of(page, 20, Sort.Direction.valueOf(direction), sort);
        Page<Primary> primList = recipeService.sortView(primSearchDto, pageRequest);
        List<String> nationNms = Nation.getNationNms();
        model.addAttribute("primList",primList);
        model.addAttribute("nationNms",nationNms);
        model.addAttribute("sort",sort);
        model.addAttribute("direction",direction);
        return "primList";
    }
}
