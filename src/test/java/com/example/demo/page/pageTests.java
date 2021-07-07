package com.example.demo.page;

import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class pageTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("--------------------------------------");

        System.out.println("Total Page : " + result.getTotalPages());

        System.out.println("Total Count : " + result.getTotalElements());

        System.out.println("Page Number : " + result.getNumber());

        System.out.println("Page Size : " + result.getSize());

        System.out.println("has next page? : " + result.hasNext());

        System.out.println("first Page? : " + result.isFirst());

        System.out.println("----------------------------------------");

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }

    }

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);


        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void testQueryMethods() {

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoAsc(70L, 80L);

        for (Memo memo : list) {
            System.out.println(memo);
        }

    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    public void testQuerySelect2() {
        List<Memo> result = memoRepository.getListDesc();

        for (Memo memo : result) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryUpdate2() {
        memoRepository.updateMemoText(10L, "Really?");
    }

    @Test
    public void testQuerySelectNative() {
        memoRepository.getNativeResult();
    }

}
