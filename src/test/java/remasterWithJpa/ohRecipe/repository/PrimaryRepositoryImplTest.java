package remasterWithJpa.ohRecipe.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import remasterWithJpa.ohRecipe.repository.dto.PrimViewDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PrimaryRepositoryImplTest {

    @Autowired PrimaryRepository primaryRepository;

    @Test
    public void testViewResult(){
        //given
        List<String> irdntNms = new ArrayList<>();
        irdntNms.add("돼지고기");
        irdntNms.add("소금");
        irdntNms.add("마늘");
        irdntNms.add("닭");
        PageRequest pageRequest = PageRequest.of(0, 1);
        //when
        Page<PrimViewDto> result = primaryRepository.viewResult(irdntNms, pageRequest);

        //then
        for (PrimViewDto primViewDto : result) {
            System.out.println("primViewDto = " + primViewDto);
        }
        System.out.println("result.hasNext() = " + result.hasNext());
        System.out.println("result.hasPrevious() = " + result.hasPrevious());
        System.out.println("result.getTotalElements() = " + result.getTotalElements());
        System.out.println("result.getNumber() = " + result.getNumber());
        System.out.println("result.getTotalPages() = " + result.getTotalPages());

        primaryRepository.viewResult(irdntNms, PageRequest.of(2, 1));
        primaryRepository.viewResult(irdntNms, PageRequest.of(3, 1));
    }


}