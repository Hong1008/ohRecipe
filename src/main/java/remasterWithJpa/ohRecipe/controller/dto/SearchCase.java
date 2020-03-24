package remasterWithJpa.ohRecipe.controller.dto;

public enum SearchCase {
    both, recipeNmKo, irdntNm;

    public static String getSearchWord(SearchCase isCase, String searchCase, String searchWord){
        return SearchCase.isCase(isCase,searchCase) ? searchWord : null;
    }

    public static boolean isCase(SearchCase isCase, String searchCase){
        if(SearchCase.valueOf(searchCase).equals(SearchCase.both)){
            return true;
        }
        return SearchCase.valueOf(searchCase).equals(isCase);
    }
}
