package com.psjw.jpa.bookmanager.repository;


import com.psjw.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //모두 동일 : findByName, findUserByNameIs, findUserByName, findUserByNameEquals
    Optional<User> findByName(String name);

    Optional<User> findUserByNameIs(String name);

    Optional<User> findUserByName(String name);

    Optional<User> findUserByNameEquals(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String Email);

    User findSomethingByEmail(String Email);

    //실행시 오류 발생
//    User findByByEmail(String email);

    List<User> findFirst1ByName(String name);

    List<User> findTop1ByName(String name);

    List<User> findFirst2ByName(String name);

    List<User> findTop2ByName(String name);

    List<User> findLast1ByName(String name);

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    //Before ,After는 시간 관련
    List<User> findByCreatedAtAfter(LocalDateTime yesterday);

    List<User> findByIdAfter(Long id);

    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<User> findByIdBetween(Long id1, Long id2);

    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    List<User> findByIdIsNotNull();

//    List<User> findByIdIsNotEmpty();

    //name is not null and name !=''로 이해하면 안됨
//    List<User> findByAddressIsNotEmpty();

    List<User> findByNameIn(List<String> names);

    List<User> findByEmailStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContaining(String name);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);
}
