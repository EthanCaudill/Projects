using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ATM
{
    public class Customer
    {
        private int cardNumber;
        private int pin;

        public void setNumbers(int cNum, int pNum)
        {
            cardNumber = cNum;
            pin = pNum;
        }
        private double dailyTotal = 0;
        private DateTime lastTransactionDate = DateTime.Today;
        private void resetDailyIfNeeded()
        {
            if (lastTransactionDate.Date != DateTime.Today)
            {
                dailyTotal = 0;
                lastTransactionDate = DateTime.Today;
            }
        }
        public bool transfer(int targetAccount, double amount)
        {
            resetDailyIfNeeded();

            if (dailyTotal + amount > 3000)
            {
                MessageBox.Show("Daily limit exceeded ($3000).");
                return false;
            }
            double balance = getBalance();

            if (balance < amount)
                return false;

            string connStr = "server=csitmariadb.eku.edu;user=student;database=csc340_db;port=3306;password=Maroon@21?;";
            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                // subtract from current
                string sql1 = "UPDATE chang1account SET balance = balance - @amt WHERE accountNum=@thisCardNum";
                MySqlCommand cmd1 = new MySqlCommand(sql1, conn);
                cmd1.Parameters.AddWithValue("@amt", amount);
                cmd1.Parameters.AddWithValue("@thisCardNum", cardNumber);
                cmd1.ExecuteNonQuery();

                // add to target
                string sql2 = "UPDATE chang1account SET balance = balance + @amt WHERE accountNum=@target";
                MySqlCommand cmd2 = new MySqlCommand(sql2, conn);
                cmd2.Parameters.AddWithValue("@amt", amount);
                cmd2.Parameters.AddWithValue("@target", targetAccount);

                int rows = cmd2.ExecuteNonQuery();

                if (rows == 0)
                {
                    MessageBox.Show("Target account not found.");
                    return false;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
                return false;
            }

            conn.Close();
            return true;
        }
        public double getBalance()
        {
           
                double balance = 0;

                string connStr = "server=csitmariadb.eku.edu;user=student;database=csc340_db;port=3306;password=Maroon@21?;";
                MySqlConnection conn = new MySqlConnection(connStr);

                try
                {
                    conn.Open();

                    string sql = "SELECT balance FROM chang1account WHERE accountNum=@thisCardNum";
                    MySqlCommand cmd = new MySqlCommand(sql, conn);

                    cmd.Parameters.AddWithValue("@thisCardNum", cardNumber);

                    MySqlDataReader reader = cmd.ExecuteReader();

                    if (reader.Read())
                    {
                        balance = Convert.ToDouble(reader["balance"]);
                    }

                    reader.Close();
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.ToString());
                }

                conn.Close();
                return balance;
            }
        public bool withdraw(double amount)
        {
            resetDailyIfNeeded();

            if (dailyTotal + amount > 3000)
            {
                MessageBox.Show("Daily limit exceeded ($3000).");
                return false;
            }
            double balance = getBalance();

            if (balance < amount)
            {
                return false;
            }

            string connStr = "server=csitmariadb.eku.edu;user=student;database=csc340_db;port=3306;password=Maroon@21?;";
            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                string sql = "UPDATE chang1account SET balance = balance - @amt WHERE accountNum=@thisCardNum";
                MySqlCommand cmd = new MySqlCommand(sql, conn);

                cmd.Parameters.AddWithValue("@amt", amount);
                cmd.Parameters.AddWithValue("@thisCardNum", cardNumber);

                cmd.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
                return false;
            }

            conn.Close();
            return true;
        }

        public bool deposit(double amount)
        {
            resetDailyIfNeeded();

            if (dailyTotal + amount > 3000)
            {
                MessageBox.Show("Daily limit exceeded ($3000).");
                return false;
            }

            string connStr = "server=csitmariadb.eku.edu;user=student;database=csc340_db;port=3306;password=Maroon@21?;";
            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                string sql = "UPDATE chang1account SET balance = balance + @amt WHERE accountNum=@thisCardNum";
                MySqlCommand cmd = new MySqlCommand(sql, conn);

                cmd.Parameters.AddWithValue("@amt", amount);
                cmd.Parameters.AddWithValue("@thisCardNum", cardNumber);

                int rows = cmd.ExecuteNonQuery();

                if (rows == 0)
                {
                    MessageBox.Show("Account not found.");
                    return false;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
                return false;
            }

            conn.Close();
            return true;
        }
        public bool verifyNumbers()
        {
            bool result = false;
            //string s = "Tom";
            string connStr = "server=csitmariadb.eku.edu;user=student;database=csc340_db;port=3306;password=Maroon@21?;";

            MySql.Data.MySqlClient.MySqlConnection conn = new MySql.Data.MySqlClient.MySqlConnection(connStr);

            try
            {

                Console.WriteLine("Connecting to MySQL...");
                conn.Open();
                string sql = "SELECT * FROM changATMCustomer WHERE cardNumber=@thisCardNum and pin=@thisPinNum";
                MySql.Data.MySqlClient.MySqlCommand cmd = new MySql.Data.MySqlClient.MySqlCommand(sql, conn);

                cmd.Parameters.AddWithValue("@thisCardNum", cardNumber);
                cmd.Parameters.AddWithValue("@thisPinNum", pin);
                MySqlDataReader myReader = cmd.ExecuteReader();
                if (myReader.Read())
                {
                    result = true; ;
                    //textBox1.Text = myReader["FirstName"].ToString();
                    //textBox2.Text = myReader["LastName"].ToString();
                    //textBox3.Text = myReader["ID"].ToString();
                }
                myReader.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
            conn.Close();
            Console.WriteLine("Done.");

            return result;
        }
    }
}
