package remasterWithJpa.ohRecipe.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import remasterWithJpa.ohRecipe.domain.RecipeComment;
import remasterWithJpa.ohRecipe.domain.code.BoardType;
import remasterWithJpa.ohRecipe.repository.dto.CommentViewDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecipeCommentRepositoryTest {

    @Autowired RecipeCommentRepository repository;

    @Test
    public void findCommentListTest(){
        Long id = 1L;
        PageRequest pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("comTime")));

        Page<RecipeComment> result = repository.findAllBy(BoardType.primary, id, pageable);

        for (RecipeComment recipeComment : result) {
            System.out.println("recipeComment = " + recipeComment);
        }
    }

    @Test
    public void comListTest(){
        Long id = 1L;
        PageRequest pageable = PageRequest.of(0, 5 ,Sort.by(Sort.Direction.DESC,"comTime"));

        Page<CommentViewDto> result = repository.commentList(BoardType.primary, id, pageable,"comTime");

        System.out.println("result.getTotalPages() = " + result.getTotalPages());
        System.out.println("result.getTotalElements() = " + result.getTotalElements());

        for (CommentViewDto commentViewDto : result) {
            System.out.println("commentViewDto = " + commentViewDto);
        }
    }
}