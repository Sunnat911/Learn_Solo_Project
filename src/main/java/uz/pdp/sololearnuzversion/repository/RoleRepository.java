package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sololearnuzversion.entity.role.RoleEntity;
import uz.pdp.sololearnuzversion.entity.role.RoleEnum;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    RoleEntity findByRoleEnum(RoleEnum roleEnum);
}
