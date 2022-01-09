package com.psjw.jpa.bookmanager.repository;

import com.psjw.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void curd() { //create, read, update, delete
        userRepository.save(new User());
        System.out.println(">>>>>" + userRepository.findAll());
        userRepository.findAll().forEach(System.out::println);
    }


    @Test
    void curd1() { //create, read, update, delete
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));//DESC 정렬 , name
        users.forEach(System.out::println);
    }

    @Test
    void curd2() { //create, read, update, delete
//        List<User> findUsers = userRepository.findAllById(List.of(1L, 2L, 3L));
        //Lists.newArrayList -> AssertJ에서 제공
        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L)); //AssertJ에서 제공\
        users.forEach(System.out::println);
    }


    @Test
    void curd3() { //create, read, update, delete
        User user1 = new User("jack", "jack@fastcampus.com");
        User user2 = new User("steve", "steve@fastcampus.com");
        userRepository.saveAll(Lists.newArrayList(user1, user2));

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
//    @Transactional
    void curd4() { //create, read, update, delete
        //could not initialize proxy [com.psjw.jpa.bookmanager.domain.User#1] - no Session
        //Session flush에서 쓰는 Context 개념 -> getOne 후에  System.out.println(user); 유지 시켜주기위해 @Transactional 붙여줌
        //getOne : Lazy방식
        User user = userRepository.getById(1L);
        System.out.println(user);
    }

    @Test
    void curd5() { //create, read, update, delete
        //findById :  Eager 방식
        Optional<User> findUser = userRepository.findById(1L);
        System.out.println(findUser);
    }

    @Test
    void crud6() {
        userRepository.save(new User("new martin", "newmartin@fastcampus.com"));
        userRepository.flush(); //DB반영시점을 조절
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud7() {
        userRepository.saveAndFlush(new User("new martin", "newmartin@fastcampus.com"));
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud8() {
        long count = userRepository.count();
        System.out.println(count);
    }

    @Test
    void crud9() {
        boolean exists = userRepository.existsById(1L); //count 쿼리가 수행
        System.out.println(exists);
    }


    @Test
    void crud10() {
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
    }

    @Test
    void crud11() {
        userRepository.deleteById(1L);
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud12() {
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L))); //in절로 가저온후 건바이건 select한번 더하고 건바이건 delete
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud13() {
        userRepository.deleteAll(); //성능이슈가 발생 -> 쿼리상으로 findAll을 가져와서 건바이건 delete
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud14() {
        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // in절로 가져온 이후 or연산을 사용하여 삭제
        userRepository.findAll().forEach(System.out::println);
    }


    @Test
    void crud15() {
        userRepository.deleteAllInBatch(); // delete 쿼리 단한번수행
        userRepository.findAll().forEach(System.out::println);
    }


    @Test
    void crud16() {
        //블로그, 게시판 등의 페이징 - zero-based page index -> 0페이지부터 시작
        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));
        System.out.println("users = " + users);
        System.out.println("totalElements = " + users.getTotalElements());
        System.out.println("totalPages = " + users.getTotalPages());
        System.out.println("numberOfElements = " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize()); //페이지 마다 갯수
        users.getContent().forEach(System.out::println);
    }

    @Test
    void crud17() {//QueryMatcher , Query By Example
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") //비교 제외
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.endsWith());//비교대상

        //ma는 무시 email로 비교
        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void crud18() { //QueryMatcher , Query By Example
        ExampleMatcher matcher = ExampleMatcher.matching() //.withIgnorePaths("name") 제외
                .withMatcher("email", endsWith()); //비교 대상
        //email like , name = 로비교
        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);
        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void crud19() { //QueryMatcher , Query By Example

        Example<User> example = Example.of(new User("martin", "martin@fastcampus.com"));
        //name, email 모두 = 로 비교
        userRepository.findAll(example).forEach(System.out::println);
    }


    @Test
    void crud20() { //QueryMatcher , Query By Example
        User user = new User();
        user.setEmail("slow");
        //문자열만 가능 -> 복잡한 쿼리는 일반적으로 QueryDsl 사용
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user);

        userRepository.findAll(example).forEach(System.out::println);
    }


    @Test
    void crud21() { //Update 쿼리
        userRepository.save(new User("david", "david@fastcampus.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com");

        userRepository.save(user);
        user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("user = " + user);
    }

    @Test
    void select() {
        //User findByName(String name); ->단일 객체로 조회가 안되서 오류발생 - query did not return a unique result: 2
//        System.out.println(userRepository.findByName("martin"));
        System.out.println(userRepository.findByName("dennis"));

        //이중에서 골라 쓰면됨 동일함
        System.out.println("findByEmail : " + userRepository.findByEmail("martin@fastcampus.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("martin@fastcampus.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("martin@fastcampus.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("martin@fastcampus.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("martin@fastcampus.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("martin@fastcampus.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin@fastcampus.com"));
        //
        //Something과 같이 잘못된 문자열은 Spring Data JPA에서 제외
        System.out.println("findSomethingByEmail : " + userRepository.findSomethingByEmail("martin@fastcampus.com"));

        //! No property byEmail found for type User! -> Runtime Error
//        System.out.println("findByByEmail : " + userRepository.findByByEmail("martin@fastcampus.com"));


        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("martin"));

        System.out.println("findTop2ByName : " + userRepository.findTop2ByName("martin"));
        System.out.println("findFirst2ByName : " + userRepository.findFirst2ByName("martin"));

//        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin")); //인식하지 않는 키워드는 인식안함 Last1인식 X -> limit가 없음
    }

    /*
    User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2022-01-09T19:42:18.574926, updatedAt=2022-01-09T19:42:18.574926)
    User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2022-01-09T19:42:18.569365, updatedAt=2022-01-09T19:42:18.569365)
    User(id=5, name=martin, email=martin@another.com, createdAt=2022-01-09T19:42:18.574926, updatedAt=2022-01-09T19:42:18.574926)
    User(id=4, name=james, email=james@slowcampus.com, createdAt=2022-01-09T19:42:18.574926, updatedAt=2022-01-09T19:42:18.574926)
    User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2022-01-09T19:42:18.574426, updatedAt=2022-01-09T19:42:18.574426)
     */
    @Test
    void where() {
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "martin"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com", "martin"));
        //Before ,After는 보통 시간 관련 -> After는 user0_.created_at>? , Before user0_.created_at<?
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        //Long 타입도 가능함  user0_.id>?
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
        //GreaterThan user0_.created_at>? -> After와 동일
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        //GreaterThanEqual   user0_.created_at>=?
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        // Between -> user0_.id between ? and ?
        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        // Between -> user0_.id between ? and ?
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));

        // GreaterThanEqualAndIdLessThanEqual ->  user0_.id>=?  and user0_.id<=?
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
    }

    /*
    User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2022-01-09T19:42:18.574926, updatedAt=2022-01-09T19:42:18.574926)
    User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2022-01-09T19:42:18.569365, updatedAt=2022-01-09T19:42:18.569365)
    User(id=5, name=martin, email=martin@another.com, createdAt=2022-01-09T19:42:18.574926, updatedAt=2022-01-09T19:42:18.574926)
    User(id=4, name=james, email=james@slowcampus.com, createdAt=2022-01-09T19:42:18.574926, updatedAt=2022-01-09T19:42:18.574926)
    User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2022-01-09T19:42:18.574426, updatedAt=2022-01-09T19:42:18.574426)
    */
    @Test
    void where1() {
        //IsNotNull -> user0_.id is not null
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
        //IsNotEmpty can only be used on collection properties! -> 여기서 IsNotEmpty는 컬렉션의 Empty를 찾는것 -> 문자열의 Empty 가 아니다
//        System.out.println("findByIdIsNotEmpty : " + userRepository.findByIdIsNotEmpty());
        //User안에 List<Address> 의 비었는지 여부를 체크한다.
//        exists (
//                select
//                address2_.id
//                from
//                user_address address1_,
//                address address2_
//                where
//                user0_.id=address1_.user_id
//                and address1_.address_id=address2_.id
//        )
        //많이 사용하지 않음
        //다른 테스트를 위해 주석 (User.java)에 Address또한 주석
//        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());
        //In ->  user0_.name in ( ? , ? )
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin","dennis")));


        //user0_.email like ? escape ? 시작지점 like 'mar%'
        System.out.println("findByEmailStartingWith : " + userRepository.findByEmailStartingWith("mar"));
        //user0_.name like ? escape ? 끝지점 like '%tin'
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        //user0_.name like ? escape ? 중간지점 like '%art%'
        System.out.println("findByNameContaining : " + userRepository.findByNameContaining("art"));
        //user0_.name like ? escape ? 중간지점 like '%art%'
        System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));
        //user0_.name like ? escape ? -> mar%, %tin, %art% 로 like 문 제어
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%"+"art"+"%"));

    }
}
