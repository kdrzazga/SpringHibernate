using System;

namespace Banque.Common.Entities
{
    public class Bank
    {
        private long id;

        private String name;
        private String shortname;

        public Bank(String name, String shortname)
        {
            this.name = name;
            this.shortname = shortname;
        }

        override
        public String ToString()
        {
            return name;
        }
    }
}
