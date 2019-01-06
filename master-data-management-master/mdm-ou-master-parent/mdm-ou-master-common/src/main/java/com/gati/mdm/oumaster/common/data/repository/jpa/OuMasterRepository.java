package com.gati.mdm.oumaster.common.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.data.entity.PrimaryKeyOuCodeCompanyId;
import com.gati.mdm.oumaster.common.data.entity.QOuMaster;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface OuMasterRepository extends JpaRepository<OuMaster, PrimaryKeyOuCodeCompanyId>,
        QuerydslPredicateExecutor<OuMaster>, QuerydslBinderCustomizer<QOuMaster> {

    List<OuMaster> findByOuCode(String ouCode);

    OuMaster findByOuCodeAndCompanyId(String ouCode, Integer companyId);

    @Override
    default void customize(QuerydslBindings bindings, QOuMaster root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.email);
    }

}
