using ATMEthanCaudill;
using System;
using System.Windows.Forms;

namespace ATM
{
    public partial class Form2 : Form
    {
        private Customer customer;

        public Form2(Customer cust)
        {
            InitializeComponent();
            customer = cust;
        }

        private void button2_Click(object sender, EventArgs e)
        {

            Form3 depositForm = new Form3(customer);
                depositForm.Show();
                this.Hide();
            
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Form4 withdrawlForm = new Form4(customer);
            withdrawlForm.Show();
            this.Hide();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Form5 balanceForm = new Form5(customer);
            balanceForm.Show();
            this.Hide();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            Form6 f = new Form6(customer);
            f.Show();
            this.Hide();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Form1 login = new Form1();
            login.Show();
            this.Close();
        }
    }
}