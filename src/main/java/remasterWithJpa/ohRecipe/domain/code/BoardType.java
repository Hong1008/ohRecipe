package remasterWithJpa.ohRecipe.domain.code;

public enum BoardType {
    primary("P"),review("R"),selfrecipe("C");

    String boardCode;

    BoardType(String boardCode) {
        this.boardCode = boardCode;
    }
}
