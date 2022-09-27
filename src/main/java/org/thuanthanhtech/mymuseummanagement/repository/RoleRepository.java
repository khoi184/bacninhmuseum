package org.thuanthanhtech.mymuseummanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thuanthanhtech.mymuseummanagement.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameRole(String nameRole);
}
