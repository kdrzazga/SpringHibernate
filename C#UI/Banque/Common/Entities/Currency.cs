using System;

namespace Banque.Common.Entities
{
    public class Currency
    {
        private long Id;
        private String Name;
        private String Shortname;

        public Currency()
        {
        }

        public Currency(String name, String shortname)
        {
            this.Name = name;
            this.Shortname = shortname;
        }

        public Currency(long id, String name, String shortname)
        {
            this.Id = id;
            this.Name = name;
            this.Shortname = shortname;
        }

        override
        public String ToString()
        {
            return Shortname;
        }
    }
}
