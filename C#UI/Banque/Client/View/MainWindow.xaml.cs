using Banque.Client.Presenter;
using Banque.Client.View;
using Banque.Common.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
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
                /*TODO*/
            }
        }

        public void BookTrade_Click(object sender, EventArgs e)
        {            
            Console.Out.WriteLine("Booking Trade for " + amount.Text);            
        }
        
        public void ShowBank_Click(object sender, EventArgs e)
        {
            var id = bankId.SelectedItem;
            Console.Out.WriteLine("Reading Bank " + id);
        }

        public void SaveBank_Click(object sender, EventArgs e)
        {
            var id = bankId.SelectedItem;
            var name = bankName.Text;
            var shortName = bankShortName.Text; 
            Console.Out.WriteLine("Saving Bank (" + id +", " + name + ", " + shortName + ")");
        }

        public void DeleteBank_Click(object sender, EventArgs e)
        {
            var id = bankId.SelectedItem;
            Console.Out.WriteLine("Deleting Bank " + id);
        }

        public void CreateBank_Click(object sender, EventArgs e)
        {
            var id = bankId.SelectedItem;
            var name = bankName.Text;
            var shortName = bankShortName.Text;
            Console.Out.WriteLine("Creating new bank (" + id + ", " + name + ", " + shortName + ")");
        }

        public void ShowAccount_Click(object sender, EventArgs e)
        {
            var id = accounts.SelectedItem;
            Console.Out.WriteLine("Reading Account " + id);
        }

        public void ShowAccountDetails_Click(object sender, EventArgs e)
        {
            var id = accounts.SelectedItem;
            Console.Out.WriteLine("Showing Account Details for account " + id);
        }

        public void SaveAccount_Click(object sender, EventArgs e)
        {
            var name = accountName.Text;
            var shortName = accountShortName.Text;
            var balance = accountBalance.Text;
            Console.Out.WriteLine("Saving Account ("+name + ", " + shortName + ", balance=" + balance + ")");
        }

        public void DeleteAccount_Click(object sender, EventArgs e)
        {
            var id = accounts.SelectedItem;
            Console.Out.WriteLine("Deleting Account " + id);
        }

        public void CreateAccount_Click(object sender, EventArgs e)
        {
            var id = accounts.SelectedItem;
            var name = accountName.Text;
            var shortName = accountShortName.Text;
            var balance = accountBalance.Text;
            Console.Out.WriteLine("Creating new Account (" + id + ", " + name + ", " 
                + shortName + ", balance=" + balance + ")");
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
