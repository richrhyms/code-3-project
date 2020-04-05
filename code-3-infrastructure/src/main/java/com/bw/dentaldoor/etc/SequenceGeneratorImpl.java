package com.bw.dentaldoor.etc;

import com.bw.SequenceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.math.BigInteger;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public abstract class SequenceGeneratorImpl implements SequenceGenerator {

//    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager entityManager;
    private final String sequenceName;
    private final TransactionTemplate transactionTemplate;
    @Value("${SEQUENCE_DEFINITION_SQL_QUERY}")
    private String sequenceDefinition;

    public SequenceGeneratorImpl(EntityManager entityManager, TransactionTemplate transactionTemplate, String sequenceTableName) {
        this.entityManager = entityManager;
        this.transactionTemplate = transactionTemplate;
        this.sequenceName = sequenceTableName.toLowerCase() + "_sequence";
    }

    @PostConstruct
    public void init() {
        transactionTemplate.execute(tx -> {
//            logger.info(sequenceName);
            this.entityManager.createNativeQuery(String.format(sequenceDefinition, sequenceName))
                    .executeUpdate();
            return null;
        });
    }

    @Transactional
    @Override
    public Long getNextLong() {
        return ((BigInteger) this.entityManager.createNativeQuery(String.format("select nextval ('%s')", sequenceName)).getSingleResult()).longValue();
    }

    @Transactional
    @Override
    public String getNext() {
        return String.valueOf(getNextLong());
    }
}
