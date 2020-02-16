using System;

namespace Banque.Common.Entities
{
    public class Account 
    {
        public long Id { get; set; }
        public Boolean Corporate { get; set; }
        public String Shortname { get; set; }
        public String Name { get; set; }
        public Double Balance { get; set; }

        private long BankId { get; set; }

        public Account()
        {      
        }

        public Account(long id, Boolean corporate, String shortname, String name, Double units, long bankId)
        {
            this.Id = id;
            this.Corporate = corporate;
            this.Shortname = shortname;
            this.Name = name;
            this.Balance = units;
            this.BankId = bankId;
        }

        public Account(Boolean corporate, String shortname, String name, Double units, long bankId)
        {
            this.Corporate = corporate;
            this.Shortname = shortname;
            this.Name = name;
            this.Balance = units;
            this.BankId = bankId;
        }

        override
        public String ToString()
        {
            return this.Shortname;
        }
    }
}
