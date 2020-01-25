using System;
using System.Collections.Generic;
using Banque.Common.Entities;

namespace Banque.Client.Presenter
{
    public interface PresenterHandler
    {
        Boolean CreateBank(String name, String shortname);

        List<Bank> readBanks();

        Boolean updateBank(Bank bank);

        Boolean deleteBank(long id);

        Boolean createAccount(String name, String shortname, Double units, long bankId);

        List<Account> readAccounts();

        Boolean updateAccount(Account account);

        Boolean deleteAccount(long id);

        void initApplication();

        Boolean saveDb();

        void stopServer();

        void setAccountId(long accountId);

        long getAccountId();
    }
}
