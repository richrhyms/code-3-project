//package com.bw.dentaldoor.search.handler;
//
//import com.bw.dentaldoor.dao.AppRepository;
//import com.bw.dentaldoor.dao.account.PortalAccountMemberRoleRepository;
//import com.bw.dentaldoor.dao.account.PortalAccountMembershipRepository;
//import com.bw.dentaldoor.dao.account.PortalAccountRepository;
//import com.bw.dentaldoor.entity.*;
//import com.bw.dentaldoor.response.pojo.PortalUserPojo;
//import com.bw.dentaldoor.response.pojo.QueryResultsPojo;
//import com.bw.dentaldoor.search.filter.PortalUserSearchFilter;
//import com.bw.enums.GenericStatusConstant;
//import com.bw.exception.NotFoundException;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.jpa.impl.JPAQuery;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.BooleanUtils;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.inject.Named;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Named
//@RequiredArgsConstructor
//public class UserManagementSearchHandler {
//
//    private final AppRepository appRepository;
//    private final PortalAccountRepository accountRepository;
//    private final PortalAccountMembershipRepository membershipRepository;
//    private final PortalAccountMemberRoleRepository roleRepository;
//
//    @Transactional
//    public QueryResultsPojo<PortalUserPojo> search(PortalUserSearchFilter filter, Predicate predicate) {
//        PortalAccount account = accountRepository.findByCode(filter.getAccountCode()).orElseThrow(NotFoundException::new);
//
//        QPortalUser qPortalUser = QPortalUser.portalUser;
//        JPAQuery<PortalUser> portalUserJPAQuery = appRepository.startJPAQuery(qPortalUser);
//        if (predicate != null) {
//            portalUserJPAQuery
//                    .where(predicate);
//        }
//
//        QPortalAccountMembership qMembership = QPortalAccountMembership.portalAccountMembership;
//        portalUserJPAQuery
//                .leftJoin(qMembership)
//                .on(qMembership.portalUser.eq(QPortalUser.portalUser));
//
//        // filter for all users that don't have membership yet for the account
//        if (BooleanUtils.isTrue(filter.getExcludeAccount())) {
//            portalUserJPAQuery.leftJoin(QPortalAccount.portalAccount)
//                    .on(QPortalAccount.portalAccount.eq(qMembership.portalAccount));
//            portalUserJPAQuery.where(qMembership.isNull().or(
//                    QPortalAccount.portalAccount.code.notEqualsIgnoreCase(filter.getAccountCode())
//            ));
//        }
//
//
//        if (filter.getRoles().isPresent()) {
//            QPortalAccountMemberRole membershipRole = QPortalAccountMemberRole.portalAccountMemberRole;
//            portalUserJPAQuery.leftJoin(membershipRole)
//                    .on(membershipRole.membership.eq(qMembership)
//                    .and(qMembership.portalAccount.eq(account))
//                    .and(membershipRole.status.eq(GenericStatusConstant.ACTIVE)
//                    .and(qMembership.status.eq(GenericStatusConstant.ACTIVE))));
//
//            portalUserJPAQuery.where(membershipRole.role.name.in(
//                    filter.getRoles().get()
//            ));
//        }
//
//        filter.getCreatedOnOrAfter().ifPresent(x -> portalUserJPAQuery.where(qPortalUser.dateCreated.goe(x)));
//        filter.getCreatedBefore().ifPresent(x -> portalUserJPAQuery.where(qPortalUser.dateCreated.lt(x)));
//        filter.getLimit().ifPresent(limit -> portalUserJPAQuery
//                .limit(limit)
//                .offset(filter.getOffset().orElse(0)));
//
//        List<PortalUser> portalUserList = portalUserJPAQuery.distinct().fetchResults().getResults();
//
//        List<PortalUserPojo> userPojos = portalUserList.stream().map(portalUser1 -> {
//            PortalUserPojo portalUserDto = new PortalUserPojo();
//            getPortalUserDto(portalUser1, portalUserDto);
//            Optional<PortalAccountMembership> optionalMembership = membershipRepository.findMembership(account, portalUser1, GenericStatusConstant.ACTIVE);
//
//            optionalMembership.ifPresent(it -> {
//                portalUserDto.setAccountName(it.getPortalAccount().getName());
//                portalUserDto.setMembershipStatus(it.getStatus());
//                portalUserDto.setAccountType(it.getPortalAccount().getType());
//
//                List<PortalAccountMemberRole> membershipRoleList = roleRepository.findAllByMembershipAndStatus(it, GenericStatusConstant.ACTIVE);
//
//                Set<PortalAccountTypeRole> userRoles = membershipRoleList.stream()
//                        .map(PortalAccountMemberRole::getRole).collect(Collectors.toSet());
//                portalUserDto.setRole(userRoles);
//            });
//
//            return portalUserDto;
//        }).collect(Collectors.toList());
//
//        return new QueryResultsPojo<>(userPojos, portalUserJPAQuery.distinct().fetchResults());
//
//    }
//
//    private void getPortalUserDto(PortalUser portalUser, PortalUserPojo userPojo) {
//        userPojo.setId(portalUser.getId());
//        userPojo.setFirstName(portalUser.getFirstName());
//        userPojo.setLastName(portalUser.getLastName());
//        userPojo.setEmail(portalUser.getEmail());
//        userPojo.setPhoneNumber(portalUser.getPhoneNumber());
//        userPojo.setDateCreated(portalUser.getDateCreated());
//        userPojo.setStatus(portalUser.getStatus());
//        userPojo.setUsername(portalUser.getUsername());
//        userPojo.setUserId(portalUser.getUserId());
//    }
//}
