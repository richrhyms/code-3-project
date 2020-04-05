package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.OneTimePassword;
import com.bw.dentaldoor.entity.TwoFactorRequest;
import com.bw.dentaldoor.enums.OtpDeliveryModeConstant;
import com.bw.dentaldoor.enums.OtpRequestTypeConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {

    @Query("SELECT otp FROM OneTimePassword otp JOIN FETCH otp.twoFactorRequest" +
            " WHERE LOWER(otp.twoFactorRequest.phoneNumber)=LOWER(?1) AND otp.pin=?2" +
            " AND otp.type =?3")
    Optional<OneTimePassword> findByPhoneAndPinAndType(
            String phoneNumber, String pin,
            OtpRequestTypeConstant requestTypeConstant);

    @Query("SELECT otp FROM OneTimePassword otp JOIN FETCH otp.twoFactorRequest" +
            " WHERE LOWER(otp.twoFactorRequest.phoneNumber)=LOWER(?1) " +
            " AND otp.type =?2 and otp.twoFactorRequest.deliveryMode = ?3")
    List<OneTimePassword> findByPhoneAndTypeAndDeliveryMode(String phoneNumber,
                                                            OtpRequestTypeConstant requestTypeConstant,
                                                            OtpDeliveryModeConstant deliveryMode);

    @Query("SELECT otp FROM OneTimePassword otp JOIN FETCH otp.twoFactorRequest" +
            " WHERE otp.twoFactorRequest=?1" +
            " AND otp.type =?2")
    Optional<OneTimePassword> findByTwoFactorRequestAndType(TwoFactorRequest request, OtpRequestTypeConstant type);

}