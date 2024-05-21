package emploiDuTemps_java;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Requetes extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbclasse,lbmatiere,lbtitre,lbtitre2,lbid,lbclasse2;
	JTextField tfmatiere,tfid;
	JComboBox comboclasse,comboclasse2;
	JButton btrech,btsupp,btrech2,btnRetour;
	public Requetes(){
		this.setTitle("TEKUP_emploi");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(173, 216, 230));
		
		lbtitre=new JLabel("Les séances de cours dans la semaine d'une matiére dans une classe");
		lbtitre.setBounds(20,5,800,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		
		lbtitre2=new JLabel("Emploi du temps de la semaine par classe");
		lbtitre2.setBounds(20,380,800,30);
		lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre2);
		
		//classe
		lbclasse=new JLabel("Classe");
		lbclasse.setBounds(30,40,150,25);
		lbclasse.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbclasse);
		
		comboclasse=new JComboBox();
		comboclasse.addItem("");
		comboclasse.addItem("TIC1A");
		comboclasse.addItem("TIC1B");
		comboclasse.addItem("TIC1C");
		comboclasse.addItem("TIC1D");
		comboclasse.setBounds(30,70,150,25);
		pn.add(comboclasse);
		//classe2
				lbclasse2=new JLabel("Classe");
				lbclasse2.setBounds(30,420,150,25);
				lbclasse2.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbclasse2);
				
				comboclasse2=new JComboBox();
				comboclasse2.addItem("");
				comboclasse2.addItem("TIC1A");
				comboclasse2.addItem("TIC1B");
				comboclasse2.addItem("TIC1C");
				comboclasse2.addItem("TIC1D");
				comboclasse2.setBounds(100,420,150,25);
				pn.add(comboclasse2);
		//matiere
		lbmatiere=new JLabel("Matiére");
		lbmatiere.setBounds(200,40,150,25);
		lbmatiere.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbmatiere);
		
		tfmatiere=new JTextField();
		tfmatiere.setBounds(200,70,150,25);
		pn.add(tfmatiere);
		//id
		
				lbid=new JLabel("ID");
				lbid.setBounds(20,340,150,25);
				lbid.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbid);
				
				tfid=new JTextField();
				tfid.setBounds(50,340,90,25);
				pn.add(tfid);
			//bouton supprimer
				btsupp=new JButton("SUPPRIMER");
				btsupp.setBounds(150,340,110,25);
				btsupp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String id=tfid.getText();
						String rq="delete from tb_cours where id='"+id+"'";
						try{
							st=con.laConnection().createStatement();
		if(JOptionPane.showConfirmDialog(null,"voulez vous Supprimer? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							 st.executeUpdate(rq);
							 JOptionPane.showMessageDialog(null,"Suppression effectuée avec succés !",null,JOptionPane.INFORMATION_MESSAGE);
		}
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
					    }
						
					}
					
				});
				pn.add(btsupp);
		//bouton recherche
		btrech=new JButton("CHERCHER");
		btrech.setBounds(360,70,120,25);
		btrech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String classe=comboclasse.getSelectedItem().toString(),
						matiere=tfmatiere.getText();
				DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("ID");
				 df2.addColumn("Classe");
				 df2.addColumn("Matiere");
				 df2.addColumn("Jour");
				 df2.addColumn("Heure");
				 df2.addColumn("Nom enseignant");
				 df2.addColumn("Contact enseignant");
				 table2.setModel(df2);
				 String rq2="select * from enseignant_cours where classe='"+classe+"' and matiere='"+matiere+"' order by num_jour";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("id"),rst.getString("classe"),rst.getString("matiere"),rst.getString("jour"),rst.getString("heure"),
		rst.getString("nom"),rst.getString("contact")
								 });
						 
					   } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
				
			}
		});
		pn.add(btrech);
		
		//btrech2
		//bouton recherche
				btrech2=new JButton("CHERCHER");
				btrech2.setBounds(260,420,120,25);
				btrech2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String classe=comboclasse2.getSelectedItem().toString();
						DefaultTableModel df2=new  DefaultTableModel();
						  init2();
						  pn.add(scroll2);
						 df2.addColumn("ID");
						 df2.addColumn("Classe");
						 df2.addColumn("Jour");
						 df2.addColumn("Matiere");
						 df2.addColumn("Heure");
						 df2.addColumn("Nom enseignant");
						 df2.addColumn("Contact enseignant");
						 table2.setModel(df2);
						 String rq2="select * from enseignant_cours where classe='"+classe+"' order by num_jour,heure";
						 try{
							 st=con.laConnection().createStatement();
							 rst=st.executeQuery(rq2);
							 while(rst.next()){
								 df2.addRow(new Object[]{
				rst.getString("id"),rst.getString("classe"),rst.getString("jour"),rst.getString("matiere"),rst.getString("heure"),
				rst.getString("nom"),rst.getString("contact")
										 });
								 
							   } 
							 }
							 
						 catch(SQLException ex){
						    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
						    }
						
					}
				});
				pn.add(btrech2);
				
	
			
		        
		      //bouton retour
		        JButton btnRetour = new JButton("RETOUR");
		        btnRetour.setBounds(300,340,100,25);
		        btnRetour.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent ev) {
		                int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment revenir à la fenêtre précédente ?", "Confirmation", JOptionPane.YES_NO_OPTION);
		                if (option == JOptionPane.YES_OPTION) {
		                    dispose();
		                    Cours crs = new Cours();
		                    crs.setVisible(true);
		                }
		            }
		        });
		        pn.add(btnRetour);


		        setVisible(true);
		    
		
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(10,120,770,200);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {
	Requetes rq=new Requetes();
	rq.setVisible(true);
	
	}

}
