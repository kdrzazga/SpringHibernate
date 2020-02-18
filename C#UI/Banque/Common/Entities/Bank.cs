using System;

namespace Banque.Common.Entities
{
    public class Bank
    {
        public long Id { get; set; }

        public String Name { get; set; }
        public String Shortname { get; set; }

        public Bank()
        {
        }

        public Bank(long id, String name, String shortname)
        {
            this.Id = id;
            this.Name = name;
            this.Shortname = shortname;
        }

        public Bank(String name, String shortname)
        {
            this.Name = name;
            this.Shortname = shortname;
        }

        override
        public String ToString()
        {
            return Name;
        }
    }
}
