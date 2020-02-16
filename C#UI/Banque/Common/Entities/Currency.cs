using System;

namespace Banque.Common.Entities
{
    public class Currency
    {
        public long Id { get; set; }
        public String Name { get; set; }
        public String Shortname { get; set; }

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
