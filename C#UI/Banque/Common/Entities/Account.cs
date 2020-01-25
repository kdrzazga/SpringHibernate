using System;

namespace Banque.Common.Entities
{
    public class Account 
    {
        private long id;
        private Boolean corporate;
        private String shortname;
        private String name;
        private Double balance;

        private long bankId;

        public Account()
        {      
        }

        public Account(Boolean corporate, String shortname, String name, Double units, long bankId)
        {
            this.corporate = corporate;
            this.shortname = shortname;
            this.name = name;
            this.balance = units;
            this.bankId = bankId;
        }

        override
        public String ToString()
        {
            return this.shortname;
        }
    }
}
