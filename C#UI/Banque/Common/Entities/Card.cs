using System;

namespace Banque.Common.Entities
{
    public abstract class Card
    {
        protected long id;
        protected Account account;
        protected Bank bank;
        protected Currency currency;
        protected Boolean active;
        protected float balance;
    }
}
