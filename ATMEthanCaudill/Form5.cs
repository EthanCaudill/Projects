using ATM;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ATMEthanCaudill
{
    public partial class Form5 : Form
    {
        private Customer customer;

        public Form5(Customer cust)
        {
            InitializeComponent();
            customer = cust;
        }

       
            private void Form5_Load(object sender, EventArgs e)
        {
            double bal = customer.getBalance();
            label2.Text = "Balance: $" + bal;
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Form2 menu = new Form2(customer);
            menu.Show();
            this.Close();
        }
    }
}
