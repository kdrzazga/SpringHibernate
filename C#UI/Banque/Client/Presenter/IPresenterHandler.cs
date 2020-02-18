using System;
using System.Collections.Generic;
using Banque.Common.Entities;

namespace Banque.Client.Presenter
{
    public interface IPresenterHandler
    {
        Boolean CreateBank(String name, String shortname);

        IList<Bank> ReadBanks();

        Bank ReadBank(long id);

        Boolean UpdateBank(Bank bank);

        Boolean DeleteBank(long id);

        Boolean CreateAccount(String name, String shortname, Double units, long bankId);

        IList<Account> ReadAccounts();

        Account ReadAccount(long id);

        IList<Transfer> ReadTransfers();

        Boolean UpdateAccount(Account account);

        Boolean DeleteAccount(long id);

        Boolean BookTransfer(long fromAccountId, long toAccountId, double amount);

        void InitApplication();

        Boolean SaveDb();

        void StopServer();

        void SetAccountId(long accountId);

        long GetAccountId();

        String GetServiceAddress();
    }
}
