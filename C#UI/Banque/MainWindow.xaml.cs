using Banque.Client.Presenter;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Banque
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static PresenterHandler handler = new TraderPresenter();

        public MainWindow()
        {
            InitializeComponent();
            handler.initApplication();
                
        }

        public void BookTrade_Click(object sender, EventArgs e)
        {
            Console.Out.WriteLine("Booking Trade");
        }
    }
}
