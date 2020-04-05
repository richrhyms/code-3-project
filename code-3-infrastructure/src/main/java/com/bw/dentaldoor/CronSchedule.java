package com.bw.dentaldoor;

import com.bw.dentaldoor.dao.AppRepository;
import com.bw.dentaldoor.dao.PortalUserRepository;
import com.bw.dentaldoor.entity.Invite;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.entity.QInvite;
import com.bw.dentaldoor.enums.InvitationTypeConstant;
import com.bw.enums.GenericStatusConstant;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.List;
import java.util.Optional;


@Component
public class CronSchedule {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TransactionTemplate transactionTemplate;
//    private final MailSenderService mailSenderService;
    private final AppRepository appRepository;
    private final PortalUserRepository portalUserRepository;


    public CronSchedule(TransactionTemplate transactionTemplate,
//						MailSenderService mailSenderService,
						PortalUserRepository portalUserRepository,
						AppRepository appRepository) {
        this.transactionTemplate = transactionTemplate;
        this.appRepository = appRepository;
//        this.mailSenderService = mailSenderService;
        this.portalUserRepository = portalUserRepository;
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void sendInvitation() {
		logger.info("===> Running my cron job for mail sending");
//		transactionTemplate.execute((e) -> {
//			List<Invite> invites = appRepository.startJPAQuery(QInvite.invite)
//					.where(QInvite.invite.status.eq(GenericStatusConstant.ACTIVE))
//					.where(QInvite.invite.notificationSent.isNull())
//					.limit(50)
//					.fetch();
//            for (Invite invite : invites) {
//				Optional<PortalUser> activeByEmail = portalUserRepository.findActiveByEmail(invite.getEmail());
//				if(activeByEmail.isPresent()){
//            		invite.setNotificationSent(false);
//            		invite.setIsExistingUser(true);
//
//				}else {
//					try {
//						if(invite.getType() ==  InvitationTypeConstant.DENTAL_OFFICE_ADMIN){
////							mailSenderService.sendDentalOfficerInvite(invite);
//						}
//						if(invite.getType() ==  InvitationTypeConstant.DENTAL_PRACTITIONER){
////							mailSenderService.sendDentalPractitionerInvite(invite);
//						}
//						if(invite.getType() ==  InvitationTypeConstant.USER){
////							mailSenderService.sendUserInvite(invite);
//						}
//						invite.setDateNotified(new Date());
//						invite.setNotificationSent(true);
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
////				inviteRepository.save(invite);
//            }
//            return null;
//        });
    }
}
