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
    public partial class Form6 : Form
    {
        private Customer customer;

        public Form6(Customer cust)  
        {
            InitializeComponent();
            customer = cust;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            {
                int target;
                double amount;

                if (!int.TryParse(textBox1.Text, out target))
                {
                    MessageBox.Show("Enter valid target account");
                    return;
                }

                if (!double.TryParse(textBox2.Text, out amount) || amount <= 0)
                {
                    MessageBox.Show("Enter valid amount");
                    return;
                }

                bool success = customer.transfer(target, amount);

                if (success)
                {
                    MessageBox.Show("Transfer successful!");
                    textBox1.Text = "";
                    textBox2.Text = "";
                }
                else
                {
                    MessageBox.Show("Transfer failed.");
                }
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
