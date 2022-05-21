package InvoiceFrame2;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class InvoiceJframe extends JFrame implements ActionListener, ListSelectionListener {
    FileReader fr = null;
      final ArrayList<String[]> invoiceItem = new ArrayList<String[]>();
    final ArrayList<String[]> invoicetb =  new ArrayList<String[]>();
    final ArrayList<String[]> Invoices= new ArrayList<String[]>();
    private  String[] invoice_tbl = {"No." , "Date", "Customer","Total"};
   private  String [][] inv_data = new String[3][4] ;

    private String [] invoice_items={"No.","Item Name", "Item Price", "Count","Item Total"};
    private String [] [] item_data =  new String[4][5];
    Boolean itemsFull= false;
    private JMenuBar menu;
    private JMenu file_Menu;
    private JMenuItem save_Item;
    private JMenuItem open_Item;
    private JMenuItem exit_Item;

// panel
    Container contentPane;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JPanel buttonPanel;
    private JPanel buttonPanel1;
    private JPanel buttonPanel2;




//table

    private JTextField   customer_name_txt, invoice_total_txt,invoice_date_txt,nameField1,dateField1,idField1 ;
    private JTable invoice_Table , item_Table;

    private JButton new_btn , delete_btn ,save_btn, cancel_btn ;

    private JLabel invoice_num_lb,invoice_total_lb,invoice_Date_lb,customer_Name_lb , number_lb,idLbl,nameLbl,dateLbl;

    String old_name , old_date ;
    private JPanel newPanel;



    public InvoiceJframe()
    {


        super("Sales Invoice ");
        setSize(970,650);
        setLocation(300,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
          //label
        contentPane = getContentPane();
        GroupLayout layout = new GroupLayout(contentPane);
        Panel1 = new JPanel();
        Panel2 = new JPanel();
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.Y_AXIS));
        Panel3 = new JPanel(new GridLayout());
        Panel4 = new JPanel();
        Panel4.setLayout(new BoxLayout(Panel4, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        buttonPanel1 = new JPanel();
        buttonPanel2 = new JPanel();

// Add menu with file list and load , save options

        menu = new JMenuBar();

        file_Menu = new JMenu("File");
        file_Menu.setActionCommand("F");


        open_Item = new JMenuItem("load file",'l');
        open_Item.setActionCommand("L");
        open_Item.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_DOWN_MASK));
        open_Item.addActionListener(this);


        save_Item =  new JMenuItem("Save file",'s');
        save_Item.setActionCommand("S");
        save_Item.setAccelerator(KeyStroke.getKeyStroke('S',KeyEvent.CTRL_DOWN_MASK));
        save_Item.addActionListener(this);

        exit_Item =  new JMenuItem("Exit",'x');
        exit_Item.setActionCommand("E");
        exit_Item.setAccelerator(KeyStroke.getKeyStroke('X',KeyEvent.CTRL_DOWN_MASK));
        exit_Item.addActionListener(this);

        file_Menu.add(open_Item);
        file_Menu.add(save_Item);
        file_Menu.addSeparator();
        file_Menu.add(exit_Item);
        menu.add(file_Menu);
        setJMenuBar(menu);

        // display invoice tables

        invoice_Table = new JTable(inv_data,invoice_tbl);
        add(new JScrollPane(invoice_Table) );
        invoice_Table.setEnabled(true);
        invoice_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invoice_Table.getSelectionModel().addListSelectionListener(this);

        //display item table
        item_Table = new JTable(item_data,invoice_items);
        add(new JScrollPane(item_Table));
        item_Table.setEnabled(true);


        //New invoice button
        new_btn =  new JButton("Create New Invoice");
        add(new_btn);
        new_btn.addActionListener(this);


        //Delete button

        delete_btn =  new JButton("Delete Invoice");
        add(delete_btn);
        delete_btn.addActionListener(this);

        //Save Button
        save_btn =  new JButton("Save");
        add(save_btn);
       save_btn.addActionListener(this);


        //Cancel Button
        cancel_btn =  new JButton("Cancel");
        add(cancel_btn);
        cancel_btn.addActionListener(this);


        invoice_num_lb =  new JLabel("Invoice number: ");
        add(invoice_num_lb);
        number_lb =  new JLabel(" ");
        add(number_lb);

        invoice_total_lb = new JLabel("Invoice total: ");
        add(invoice_total_lb);
        invoice_total_txt = new JTextField(5);
        add(invoice_total_txt);

        invoice_Date_lb = new JLabel("Invoice Date: ");
        add(invoice_Date_lb);
        invoice_date_txt = new JTextField(5);
        add(invoice_date_txt);


        customer_Name_lb = new JLabel("Customer Name: ");
        add(customer_Name_lb);
        customer_name_txt = new JTextField(5);
        add(customer_name_txt);

        //panel 2
        Panel1.setBorder(BorderFactory.createTitledBorder(" Invoice Table"));
        Panel3.setBorder(BorderFactory.createTitledBorder(" Invoice Items"));
        Panel2.setBorder(BorderFactory.createTitledBorder(" "));


        newPanel= new JPanel(new GridLayout(0,1));
        newPanel.setBorder(BorderFactory.createTitledBorder(" Add new Invoice Header"));

        idLbl = new JLabel("ID: ");
        idField1 = new JTextField(25);
        nameLbl = new JLabel("Name:  ");
        nameField1 = new JTextField(25);
        dateLbl = new JLabel("Date:  ");
        dateField1 = new JTextField(25);

        newPanel.add(idLbl);
        newPanel.add(idField1);
        newPanel.add(nameLbl);
        newPanel.add(nameField1);
        newPanel.add(dateLbl);
        newPanel.add(dateField1);

        //table2= new JTable(values2, col2);
        Panel1.add(new JScrollPane(invoice_Table));
        Panel4.add(invoice_num_lb);
        Panel4.add(number_lb);
        Panel4.add(invoice_Date_lb);
        Panel4.add(invoice_date_txt);
        Panel4.add(customer_Name_lb);
        Panel4.add(customer_name_txt);
        Panel4.add(invoice_total_lb);
        Panel4.add(invoice_total_txt);

        Panel3.add(new JScrollPane(item_Table));
        Panel2.add(Panel4);
        Panel2.add(Panel3);

        buttonPanel1.add(new_btn);
        buttonPanel1.add(delete_btn);
        buttonPanel2.add(save_btn);
        buttonPanel2.add(cancel_btn);
        buttonPanel.add(buttonPanel1);
        buttonPanel.add(buttonPanel2);
        contentPane.add(new JScrollPane(Panel1));
        contentPane.add(Panel2, BorderLayout.EAST);
        contentPane.add(buttonPanel, BorderLayout.PAGE_END);

    }


  void  loadFile(){

       JOptionPane.showMessageDialog(this, " please select Invoice header ",
              " Invoice Header", JOptionPane.WARNING_MESSAGE);

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(" Load Header ");

        String line = null;
        FileReader fr = null;
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            try {
                fr = new FileReader(selectedFile);
            }
            catch (FileNotFoundException e)
            {JOptionPane.showMessageDialog(this, " File Not Found",
                    " Invoice Header File ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            BufferedReader br = new BufferedReader(fr);


            try {
                while ((line = br.readLine()) != null) {

                   String[] headerSegments = line.split(",");
                    invoicetb.add(headerSegments);

                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, " Wrong File Format",
                        " Invoice Header  ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();}
            finally {
                try {br.close();
                    fr.close();
                } catch (Exception e) {e.printStackTrace();}

                Object[][] content = new Object[invoicetb.size()][invoice_tbl.length];
                for(int i=0; i<invoicetb.size(); i++) {
                    content[i][0] = invoicetb.get(i)[0];

                    content[i][1] = invoicetb.get(i)[1];
                    content[i][2] = invoicetb.get(i)[2];

                }

                invoice_Table.setModel(new DefaultTableModel(content,invoice_tbl));

            }
        }
    }

    // load Invoice lines files and import in Jtable
    public void openFile()
    {

        JOptionPane.showMessageDialog(this, " please select Invoice line ",
                " Invoice line", JOptionPane.WARNING_MESSAGE);

        JFileChooser fc1 = new JFileChooser();
        fc1.setDialogTitle(" Load invoice ");

        String line1 = null;
        FileReader fr1 = null;
        int result1 = fc1.showOpenDialog(this);
        if (result1 == JFileChooser.APPROVE_OPTION) {
            File selectedFile1 = fc1.getSelectedFile();
            try {
                fr1 = new FileReader(selectedFile1);
            }
            catch (FileNotFoundException e)
            {JOptionPane.showMessageDialog(this, " File Not Found",
                    " Invoice Header File ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            BufferedReader br1 = new BufferedReader(fr1);


            try {
                while ((line1 = br1.readLine()) != null) {

                    String[] headerSegments1 = line1.split(",");
                    invoiceItem.add(headerSegments1);

                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, " Wrong File Format",
                        " Invoice Header  ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();}
            finally {
                try {br1.close();
                    fr1.close();
                } catch (Exception e) {e.printStackTrace();}

                Object[][] content1 = new Object[invoiceItem.size()][invoice_items.length];
                for(int i=0; i<invoiceItem.size(); i++) {
                    content1[i][0] = invoiceItem.get(i)[0];
                    content1[i][1] = invoiceItem.get(i)[1];
                    content1[i][2] = invoiceItem.get(i)[2];
                    double x = Double.parseDouble(invoiceItem.get(i)[2]);
                    content1[i][3] = invoiceItem.get(i)[3];
                   double y = Double.parseDouble(invoiceItem.get(i)[3]);
                   content1[i][4] = x*y;

                }

                item_Table.setModel(new DefaultTableModel(content1,invoice_items));

            }
        }
        }


// Export table to CSV file

    public void exportToCSV(JTable tableToExport) {

        JFileChooser Fch = new JFileChooser();
        Fch.setDialogTitle(" Save as");
        int userSelection = Fch.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File savedFile = Fch.getSelectedFile();

            DefaultTableModel dtm = (DefaultTableModel) tableToExport.getModel();
            int Row = dtm.getRowCount();
            int Col = dtm.getColumnCount();
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savedFile), "utf-8"));

                StringBuffer bufferHeader = new StringBuffer();
                for (int j = 0; j < Col; j++) {
                    bufferHeader.append(dtm.getColumnName(j));
                    if (j != Col) bufferHeader.append(", ");
                }
                writer.write(bufferHeader.toString() + "\r\n");

                for (int i = 0; i < Row; i++) {
                    StringBuffer buffer = new StringBuffer();
                    for (int j = 0; j < Col; j++) {
                        buffer.append(dtm.getValueAt(i, j));
                        if (j != Col) buffer.append(", ");
                    }
                    writer.write(buffer.toString() + "\r\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Create new invoice

    public  void DeleteSelectedRow(JTable table)
    {
        if(itemsFull==true)
        {
            if(table.getSelectedRow() != -1)
            {
                DefaultTableModel df1 = (DefaultTableModel) table.getModel();
                int row[] = table.getSelectedRows();
                for (int i = row.length - 1; i >= 0; i--)
                {int x = row[i];
                    df1.removeRow(x);
                    JOptionPane.showMessageDialog(this, "Selected row is deleted Successfully",
                            " Delete Row .. ", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please Select Row of Invoice Lines ",
                        " Warning ", JOptionPane.WARNING_MESSAGE);
            }
        } else if(itemsFull== false) {
            JOptionPane.showMessageDialog(this, " Table is Empty, Please load Invoice lines ",
                    " Delete Row ", JOptionPane.ERROR_MESSAGE);
        }
    }


        private void createInvoice()
            {
                if( itemsFull==true)
                {
                    int optionType = JOptionPane.DEFAULT_OPTION;
                    int messageType = JOptionPane.PLAIN_MESSAGE;
                    String[] options = { "Save", "Cancel" };
                    Object initialValue = options[0];

                    int res= JOptionPane.showOptionDialog(null, newPanel,
                            "Add new invoice", optionType, messageType, null, options,
                            initialValue);

                    if ( res==JOptionPane.YES_OPTION)
                    {
                        String id=idField1.getText();
                        String newDATE= dateField1.getText();
                        String name1= nameField1.getText();



                        DefaultTableModel invoiceRow= (DefaultTableModel)invoice_Table.getModel();


                        invoiceRow.addRow(new Object[]{id, newDATE,name1});


                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Please load Header File first  ",
                            " Warning ", JOptionPane.WARNING_MESSAGE);
                }
            }



     public void cancel_change(){

             customer_name_txt.setText(old_name);
             invoice_date_txt.setText(old_date);

     }






    public void getSelectedRowValues(JTable table)
    {

        int selection = invoice_Table.getSelectedRow();

        old_name= customer_name_txt.getText();
        old_date = invoice_date_txt.getText();
        number_lb.setText(invoice_Table.getValueAt(selection,0).toString());
        invoice_date_txt.setText(invoice_Table.getValueAt(selection, 1).toString());
        customer_name_txt.setText(invoice_Table.getValueAt(selection, 2).toString());

        invoice_total_txt.setText(invoice_Table.getValueAt(selection,4).toString());


    }

    private void saveChnagesOfInvoice()
    {
        int i= invoice_Table.getSelectedRow();
        if(invoice_Table.getSelectedRow() != -1)
        {
            item_Table.setValueAt(invoice_date_txt.getText(),i,1);
            invoice_Table.setValueAt(customer_name_txt.getText(),i,2);
            JOptionPane.showMessageDialog(this, "Invoice Number: "+number_lb.getText()+ "  is Updated successfully ",
                    " Save Changes ", JOptionPane.INFORMATION_MESSAGE);
        }
        else {JOptionPane.showMessageDialog(this, "No row selected ",
                " Save Changes ", JOptionPane.ERROR_MESSAGE);}
    }



    @Override
    public void actionPerformed(ActionEvent e) {





        switch (e.getActionCommand())
        {

            case "L":
                loadFile();
                openFile();
                itemsFull = true;



            break;

            case "S":

                if (itemsFull==true) {
                    JOptionPane.showMessageDialog(this, "Save The Invoice Header Table ",
                            " Save Invoice Header", JOptionPane.INFORMATION_MESSAGE);
                    exportToCSV(invoice_Table);

                    JOptionPane.showMessageDialog(this, "Save The Invoice Lines Table ",
                            " Save Invoice Lines", JOptionPane.INFORMATION_MESSAGE);
                    exportToCSV(item_Table);

                    JOptionPane.showMessageDialog(this, "Files are saved Sucessfully",
                            " Success ", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(this,"No records to save","Error Message",JOptionPane.ERROR_MESSAGE);


                break;


            case "E":
                System.exit(0);
            break;


        }


        if(e.getSource().equals(new_btn)) {
            if(itemsFull==false)
            JOptionPane.showMessageDialog(null, "Please Load invoice line First", "Warning", JOptionPane.WARNING_MESSAGE);
            else
                idField1.setText("");
            nameField1.setText("");
            dateField1.setText("");
                createInvoice();

        }
      else if (e.getSource().equals(delete_btn))
        {
            DeleteSelectedRow(invoice_Table);
            JOptionPane.showMessageDialog(null,"No Record selected","Error",JOptionPane.ERROR_MESSAGE);

        }
        else if (e.getSource().equals(save_btn))
        {
            saveChnagesOfInvoice();
          //  JOptionPane.showMessageDialog(null,"No Record selected","Error",JOptionPane.ERROR_MESSAGE);

        }
        else if (e.getSource().equals(cancel_btn))
        {
            if(itemsFull)
            cancel_change();
            else
            JOptionPane.showMessageDialog(null,"No Record selected","Error",JOptionPane.ERROR_MESSAGE);

        }





    }

    @Override
    public void valueChanged(ListSelectionEvent e) {getSelectedRowValues(invoice_Table);}
}
