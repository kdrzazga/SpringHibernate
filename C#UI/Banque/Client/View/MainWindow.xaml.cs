using Banque.Client.Presenter;
using Banque.Common.Entities;
using System;
using System.Windows;

namespace Banque
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static IPresenterHandler handler = new TraderPresenter();

        public MainWindow()
        {
            InitializeComponent();

            handler.InitApplication();

            foreach(Bank bank in handler.ReadBanks())
            {
                bankId.Items.Add(bank.Id);
            }

            foreach (Account account in handler.ReadAccounts())
            {
                fromAccounts.Items.Add(account.Id);
                toAccounts.Items.Add(account.Id);
                accounts.Items.Add(account.Id);
            }

            foreach(Transfer transfer in handler.ReadTransfers())
            {
                               
            }
        }

        public void BookTrade_Click(object sender, EventArgs e)
        {            
            Console.Out.WriteLine("Booking Trade for " + amount.Text);            
            
        }
        
        public void ShowBank_Click(object sender, EventArgs e)
        {
            var id = long.Parse(bankId.SelectedItem.ToString());
            Console.Out.WriteLine("Reading Bank " + id);
            var bank = handler.ReadBank(id);
            bankName.Text = bank.Name;
            bankShortName.Text = bank.Shortname;
            //TODO bankAccounts.Text = bank.
        }

        public void SaveBank_Click(object sender, EventArgs e)
        {
            var id = long.Parse(bankId.SelectedItem.ToString());
            var name = bankName.Text;
            var shortName = bankShortName.Text; 
            Console.Out.WriteLine("Saving Bank (" + id +", " + name + ", " + shortName + ")");
            handler.UpdateBank(new Bank(id, name, shortName));
        }

        public void DeleteBank_Click(object sender, EventArgs e)
        {
            var id = long.Parse(bankId.SelectedItem.ToString());
            Console.Out.WriteLine("Deleting Bank " + id);
            handler.DeleteBank(id);
        }

        public void CreateBank_Click(object sender, EventArgs e)
        {
            var name = bankName.Text;
            var shortName = bankShortName.Text;
            Console.Out.WriteLine("Creating new bank (" + name + ", " + shortName + ")");
            handler.CreateBank(name, shortName);
        }

        public void ShowAccount_Click(object sender, EventArgs e)
        {
            var id = long.Parse(bankId.SelectedItem.ToString());
            Console.Out.WriteLine("Reading Account " + id);
            var account = handler.ReadAccount(id);
            accountName.Text = account.Name;
            accountShortName.Text = account.Shortname;
            accountBalance.Text = account.Balance.ToString();
        }

        public void ShowAccountDetails_Click(object sender, EventArgs e)
        {
            var id = accounts.SelectedItem;
            Console.Out.WriteLine("Showing Account Details for account " + id);
        }

        public void SaveAccount_Click(object sender, EventArgs e)
        {
            var id = long.Parse(accounts.SelectedItem.ToString());
            var name = accountName.Text;
            var shortName = accountShortName.Text;
            var balance = double.Parse(accountBalance.Text);
            Console.Out.WriteLine("Saving Account ("+name + ", " + shortName + ", balance=" + balance + ")");
            
            long bank = long.Parse(bankId.Text);
            handler.UpdateAccount(new Account(id, false, shortName, name, balance, bank));
        }

        public void DeleteAccount_Click(object sender, EventArgs e)
        {
            var id = long.Parse(accounts.SelectedItem.ToString());
            Console.Out.WriteLine("Deleting Account " + id);
            handler.DeleteAccount(id);
        }

        public void CreateAccount_Click(object sender, EventArgs e)
        {
            var name = accountName.Text;
            var shortName = accountShortName.Text;
            var balance = accountBalance.Text;
            var bank = long.Parse(bankId.SelectedIndex.ToString());
            Console.Out.WriteLine("Creating new Account (" + name + ", " 
                + shortName + ", balance=" + balance + ")");
            handler.CreateAccount(name, shortName, double.Parse(balance), bank);
        }
        
        public void DbLink_Clicked(object sender, EventArgs e)
        {
            Console.Out.WriteLine("Opening DB in default Browser");
            System.Diagnostics.Process.Start(handler.GetServiceAddress() + "/h2-console");
        }        

        public void Stop_Clicked(object sender, EventArgs e)
        {
            Console.Out.WriteLine("Stopping the application");
            handler.StopServer();            
        }
    }
}
