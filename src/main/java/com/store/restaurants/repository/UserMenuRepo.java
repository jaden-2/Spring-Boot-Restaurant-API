package com.store.restaurants.repository;

import com.store.restaurants.entity.UserMenu;
import com.store.restaurants.entity.composites.UserMenuKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMenuRepo extends JpaRepository<UserMenu, UserMenuKey> {
    public boolean existsByKey_UserId(Integer userId);
    @Query("SELECT m.key.menuId from UserMenu m where m.key.userId = :userId")
    public List<Integer> findMenuByUserId(@Param("userId") Integer userId);

    public void deleteByKey_UserId(Integer userId);
}
