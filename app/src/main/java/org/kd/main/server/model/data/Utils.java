package org.kd.main.server.model.data;

import org.kd.main.common.entities.Transfer;
import org.kd.main.server.model.data.dao.AccountDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class Utils {

    private final AccountDaoRepo accountDaoRepo;

    @Autowired
    public Utils(AccountDaoRepo accountDaoRepo) {
        this.accountDaoRepo = accountDaoRepo;
    }

    public boolean isInternal(Transfer transfer) {
        var srcFundBank = Optional.ofNullable(
                accountDaoRepo.read(transfer.getSrcAccountId()).getBankId())
                .orElse((long) -1);
        var destFundBank = Optional.ofNullable(
                accountDaoRepo.read(transfer.getDestAccountId()).getBankId())
                .orElse((long) -2);

        var correctInternalTransfer = srcFundBank.equals(destFundBank) && transfer.getInternal();
        var correctExternalTransfer = !srcFundBank.equals(destFundBank) && !transfer.getInternal();
        var nonNullEntities = !srcFundBank.equals(-1L) && !destFundBank.equals(-2L);

        return nonNullEntities && (correctInternalTransfer || correctExternalTransfer);
    }
}