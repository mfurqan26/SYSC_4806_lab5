package org.example;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuddyInfoRepository extends JpaRepository<BuddyInfo, Integer> {

    List<BuddyInfo> findByName(String name);
    List<BuddyInfo> findByAddress(String address);
    List<BuddyInfo> findByPhoneNumber(String phoneNumber);

    BuddyInfo findById(long id);

}