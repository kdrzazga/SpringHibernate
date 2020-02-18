namespace Banque.Common.Entities
{
    public class Transfer
    {
        public long Id { get; set; }
        public Account srcAccount { get; set; }
        public Account destAccount { get; set; }
        public double units { get; set; }
        public bool internalTransfer  { get; set; }

        public Transfer(Account srcAccount, Account destAccount, double units, bool internalTransfer) {
        this.srcAccount = srcAccount;
        this.destAccount = destAccount;
        this.units = units;
        this.internalTransfer = internalTransfer;
    }
}
}
