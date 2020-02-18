using System;
using System.Collections.Generic;
using System.Net.Http;
using Banque.Client.View;
using Banque.Common.Entities;
using Newtonsoft.Json;

namespace Banque.Client.Presenter
{
    class TraderPresenter : IPresenterHandler
    {
        private const String Port = "8080";
        private readonly string ServiceAddress = "http://localhost:" + Port;

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
            IList<Account> accounts;

            var corpAccountsJson = new RestHelper().sendRequest(this.ServiceAddress, "/corporate-accounts", HttpMethod.Get);
            var indivAccountsJson = new RestHelper().sendRequest(this.ServiceAddress, "/individual-accounts", HttpMethod.Get);
            Console.Out.WriteLine("Read accounts:\n" + corpAccountsJson );
            Console.Out.WriteLine(indivAccountsJson );
            try
            {
                accounts = JsonConvert.DeserializeObject<List<Account>>(corpAccountsJson);
                foreach (Account individualAccount in JsonConvert.DeserializeObject<List<Account>>(corpAccountsJson))
                {
                    accounts.Add(individualAccount);
                }
            }
            catch (Exception e)
            {
                accounts = new StubGenerator().GenerateStubAccounts();
            }

            return accounts;
        }

        public IList<Bank> ReadBanks()
        {
            var banksJson = new RestHelper().sendRequest(this.ServiceAddress, "/banks", HttpMethod.Get);
            Console.Out.WriteLine("Read banks:\n" + banksJson);
            IList<Bank> banks;

            try
            {
                banks = JsonConvert.DeserializeObject<List<Bank>>(banksJson);
            }
            catch (Exception e)
            {
                banks = new StubGenerator().GenerateStubBanks(); 
            }

            return banks;
        }

        public IList<Transfer> ReadTransfers()
        {
            var transfersJson = new RestHelper().sendRequest(this.ServiceAddress, "/transfers", HttpMethod.Get);
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
            new RestHelper().sendRequest(this.ServiceAddress, "/stop", HttpMethod.Post);
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

        public Bank ReadBank(long id)
        {
            var bankJson = new RestHelper().sendRequest(this.ServiceAddress, "/bank/" + id, HttpMethod.Get);
            Bank bank;               

            try
            {
                bank = JsonConvert.DeserializeObject<Bank>(bankJson);
            }
            catch (Exception e)
            {
                bank = null;//TODO
            }

            return bank;
        }

        public Account ReadAccount(long id)
        {
            var bankJson = new RestHelper().sendRequest(this.ServiceAddress, "/account/" + id, HttpMethod.Get);
            Account account;

            try
            {
                account = JsonConvert.DeserializeObject<Account>(bankJson);
            }
            catch (Exception e)
            {
                account = null;//TODO
            }

            return account;
        }

        public bool BookTransfer(long fromAccountId, long toAccountId, double amount)
        {
            var transferJson = "";
            /*
            {
            "srcAccount": {
                "id": 2003,
                "corporate": true,
                "shortname": "RAMP",
                "name": "Liveramp Holdings Inc.",
                "balance": 38.35,
                "bankId": 1011
                },
            "destAccount": {
                "id": 2004,
                "corporate": true,
                "shortname": "RBA",
                "name": "Ritchie Bros. Auctioneers Inc",
                "balance": 31.33,
                "bankId": 1011
                },
            "units": 10,
            "internal": true
            }*/

            new RestHelper().sendRequest(this.ServiceAddress, "/transfer", HttpMethod.Post, transferJson);

            return false;//TODO:  
        }
    }
}
