using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace ATM
{
    public partial class Form1 : Form
    {
        Customer thisCustomer = new Customer();
        int cardNum;
        int pinNum;
        public Form1()
        {
            InitializeComponent();
            panel1.Hide();

        }

     

        private void label3_Click(object sender, EventArgs e)
        {

        }

        

        private void button1_Click_1(object sender, EventArgs e)
            {
                panel2.Hide();
                panel2.SendToBack();
                panel1.Show();
                panel1.BringToFront();
                cardNum = int.Parse(textBox1.Text);
            }

        private void button2_Click_1(object sender, EventArgs e)
        {
            pinNum = int.Parse(textBox2.Text);
            thisCustomer.setNumbers(cardNum, pinNum);

            if (thisCustomer.verifyNumbers())
            {
                Form2 menu = new Form2(thisCustomer);
                menu.Show();
                this.Hide();
            }
            else
            {
                MessageBox.Show("Incorrect card number or pin number.", "Save Error", MessageBoxButtons.OK, MessageBoxIcon.Error);

                panel1.Hide();
                panel1.SendToBack();
                panel2.Show();
                panel2.BringToFront();

                textBox1.Text = "";
                textBox2.Text = "";
            }

        }
    }
}