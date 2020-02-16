using System;
using System.Collections.Generic;
using System.Net.Http;
using Banque.Client.View;
using Banque.Common.Entities;

namespace Banque.Client.Presenter
{
    class TraderPresenter : IPresenterHandler
    {
        private const String Port = "8080";
        private String ServiceAddress = "http://localhost:" + Port;
        private HttpMethod RequestType;
        private String RequestAsString;
        private String RequestUrl;

        public bool CreateAccount(string name, string shortname, double units, long bankId)
        {
            throw new NotImplementedException();
        }

        public bool CreateBank(string name, string shortname)
        {
            throw new NotImplementedException();
        }

        public bool DeleteAccount(long id)
        {
            throw new NotImplementedException();
        }

        public bool DeleteBank(long id)
        {
            throw new NotImplementedException();
        }

        public long GetAccountId()
        {
            throw new NotImplementedException();
        }

        public void InitApplication()
        {
        }

        public IList<Account> ReadAccounts()
        {
            var corpAccountsJson = new RestHelper().sendRequest(this.ServiceAddress, "/corporate-accounts", "GET", new byte[0]);
            var indivAccountsJson = new RestHelper().sendRequest(this.ServiceAddress, "/individual-accounts", "GET", new byte[0]);
            Console.Out.WriteLine("Read accounts:\n" + corpAccountsJson );
            Console.Out.WriteLine(indivAccountsJson );
            /*TODO convert JSON to list and remove stub*/
            return new StubGenerator().GenerateStubAccounts();
        }

        public IList<Bank> ReadBanks()
        {
            var banksJson = new RestHelper().sendRequest(this.ServiceAddress, "/banks", "GET", new byte[0]);
            Console.Out.WriteLine("Read banks:\n" + banksJson);
            /*TODO convert JSON to list of banks and remove stub*/
            return new StubGenerator().GenerateStubBanks();
        }

        public IList<Transfer> ReadTransfers()
        {
            var transfersJson = new RestHelper().sendRequest(this.ServiceAddress, "/transfers", "GET", new byte[0]);
            Console.Out.WriteLine("Read transfers:\n" + transfersJson);
            /*TODO convert JSON to list of banks and remove stub*/
            return new StubGenerator().GenerateStubTransfers();

        }

        public bool SaveDb()
        {
            throw new NotImplementedException();
        }

        public void SetAccountId(long accountId)
        {
            throw new NotImplementedException();
        }

        public void StopServer()
        {
            new RestHelper().sendRequest(this.ServiceAddress, "/stop", "POST", new byte[0]);
            //throw new NotImplementedException();
        }

        public bool UpdateAccount(Account account)
        {
            throw new NotImplementedException();
        }

        public bool UpdateBank(Bank bank)
        {
            throw new NotImplementedException();
        }

        public String GetServiceAddress()
        {
            return this.ServiceAddress;
        }
    }
}
