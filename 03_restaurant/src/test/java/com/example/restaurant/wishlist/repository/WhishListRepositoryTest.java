package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WhishListRepositoryTest {
    @Autowired
    private WishListRepository wiWhishListRepository;

    private WishListEntity create() {
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("roadAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    @Test
    public void saveTest() {
        var wishListEntity = create();
        var expected = wiWhishListRepository.save(wishListEntity);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1, expected.getIndex());
    }


    @Test
    public void updateTest() {
        var wishListEntity = create();
        var expected = wiWhishListRepository.save(wishListEntity);
        expected.setTitle("update test");
        var savedEntity = wiWhishListRepository.save(expected);
        Assertions.assertNotNull("update test", savedEntity.getTitle());
        Assertions.assertEquals(1, wiWhishListRepository.findAll().size());
    }


    @Test
    public void findByIdTest() {
        var wishListEntity = create();
        wiWhishListRepository.save(wishListEntity);

        var expected = wiWhishListRepository.findById(1);
        Assertions.assertEquals(true, expected.isPresent());
        Assertions.assertEquals(1, expected.get().getIndex());
    }

    @Test
    public void deleteTest() {
        var wishListEntity = create();
        wiWhishListRepository.save(wishListEntity);

        wiWhishListRepository.deleteById(1);
        int count = wiWhishListRepository.findAll().size();
        Assertions.assertEquals(0, count);
    }

    @Test
    public void listAllTest() {
        var wishListEntity1 = create();
        wiWhishListRepository.save(wishListEntity1);

        var wishListEntity2 = create();
        wiWhishListRepository.save(wishListEntity2);

        int count = wiWhishListRepository.findAll().size();
        Assertions.assertEquals(2, count);
    }

}