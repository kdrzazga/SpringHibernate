using Banque.Common.Entities;
using System.Collections.Generic;

namespace Banque.Client.Presenter
{
    class StubGenerator
    {
        public IList<Bank> GenerateStubBanks()
        {
            return new List<Bank>(3)
            {
                new Bank(0L, "Stub1", "stb1"),
                new Bank(1L, "Stub2", "stb2"),
                new Bank(1002L, "Stub3", "stb3")
            };
        }

        public IList<Account> GenerateStubAccounts()
        {
            return new List<Account>
            {
                new Account(0L, false, "stbAcc1", "Stub Account1", 100, 0L),
                new Account(2001L, true, "stbAcc1", "Stub Account1", 10, 1002L),
                new Account(2002L, true, "stbAcc1", "Stub Account1", 5, 1002L)
            };
        }

        public IList<Currency> GenerateStubCurrencies()
        {
            return new List<Currency>
            {
                new Currency(1L, "Polish Zloty", "PLN"),
                new Currency(2L, "US Dollar", "USD"),
                new Currency(3L, "Euro", "EUR")
            };
        }

        public IList<Transfer> GenerateStubTransfers()
        {
            var accountsIterator = GenerateStubAccounts().GetEnumerator();

            var srcAccount = accountsIterator.Current;
            accountsIterator.MoveNext();
            var destAccount = accountsIterator.Current;

            return new List<Transfer>
            {
                new Transfer(srcAccount, destAccount, 1, true)
            };
        }
    }
}
