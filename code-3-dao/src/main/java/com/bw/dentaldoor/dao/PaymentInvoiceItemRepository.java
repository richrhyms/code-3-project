package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.PaymentInvoice;
import com.bw.dentaldoor.entity.PaymentInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface PaymentInvoiceItemRepository extends JpaRepository<PaymentInvoiceItem, Long> {

    Long countAllByPaymentInvoice(PaymentInvoice paymentInvoice);
}