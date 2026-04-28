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
    public partial class Form4 : Form
    {
        private Customer customer;

        public Form4(Customer cust)
        {
            InitializeComponent();
            customer = cust;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            double amount;

            if (!double.TryParse(textBox1.Text, out amount) || amount <= 0)
            {
                MessageBox.Show("Enter a valid amount");
                return;
            }

            bool success = customer.withdraw(amount);

            if (success)
            {
                MessageBox.Show("Withdraw successful!");
                textBox1.Text = "";
            }
            else
            {
                MessageBox.Show("Insufficient funds.");
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Form2 menu = new Form2(customer);
            menu.Show();
            this.Close();
        }
    }
}
