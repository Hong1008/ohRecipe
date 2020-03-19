package remasterWithJpa.ohRecipe.repository.dto;

import remasterWithJpa.ohRecipe.domain.RecipeComment;

import java.time.LocalDateTime;

public class CommentViewDto {
    private String userId;
    private String userNickname;
    private LocalDateTime comTime;
    private int rating;
    private String comContent;
    private String userIcon;

    public CommentViewDto(RecipeComment recipeComment) {
        this.userId = recipeComment.getUserTable().getId();
        this.userNickname = recipeComment.getUserTable().getUserNickname();
        this.comTime = recipeComment.getComTime();
        this.rating = recipeComment.getRating();
        this.comContent = recipeComment.getComContent();
        this.userIcon = recipeComment.getUserTable().getUserIcon();
    }
}
