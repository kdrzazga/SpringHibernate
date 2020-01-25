using System;
using System.Collections.Generic;
using System.Net.Http;
using Banque.Common.Entities;

namespace Banque.Client.Presenter
{
    class TraderPresenter : PresenterHandler
    {
        private const String port = "8081";
        private String serviceAddress = "http://localhost:" + port;
        private HttpMethod requestType;
        private String requestAsString;
        private String requestUrl;

        public bool createAccount(string name, string shortname, double units, long bankId)
        {
            throw new NotImplementedException();
        }

        public bool CreateBank(string name, string shortname)
        {
            throw new NotImplementedException();
        }

        public bool deleteAccount(long id)
        {
            throw new NotImplementedException();
        }

        public bool deleteBank(long id)
        {
            throw new NotImplementedException();
        }

        public long getAccountId()
        {
            throw new NotImplementedException();
        }

        public void initApplication()
        {
        }

        public List<Account> readAccounts()
        {
            throw new NotImplementedException();
        }

        public List<Bank> readBanks()
        {
            throw new NotImplementedException();
        }

        public bool saveDb()
        {
            throw new NotImplementedException();
        }

        public void setAccountId(long accountId)
        {
            throw new NotImplementedException();
        }

        public void stopServer()
        {
            throw new NotImplementedException();
        }

        public bool updateAccount(Account account)
        {
            throw new NotImplementedException();
        }

        public bool updateBank(Bank bank)
        {
            throw new NotImplementedException();
        }
    }
}
