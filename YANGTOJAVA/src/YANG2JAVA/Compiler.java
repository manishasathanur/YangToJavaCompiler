package YANG2JAVA;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class Compiler extends JFrame {
	/**
	 * 
	 */
	boolean failed=true;
	
	static SyntaxChecker parser = null;
	private static final long serialVersionUID = 1L;
	String fname="",fpath="",name="",exten="";
	JLabel x = new JLabel("Select Yang FIle "), y = new JLabel("Java File Path "),z=new JLabel("Status");
	
	private JTextField filename = new JTextField(), dir = new JTextField(),output=new JTextField(),jfile=new JTextField(),
			objectFile=new JTextField();
	JTextArea ofile=new JTextArea();

	private JButton open = new JButton("Browse"),convert=new JButton("Convert to JAVA"),done = new JButton("Compile"),see=new JButton("Open Explorer"),clear=new JButton("Reset");

	protected String parseError;
	public Compiler() {
		see.setEnabled(false);
		convert.setEnabled(false);
		done.setEnabled(false);
	  setTitle("Yang-2-JAVA Compiler");
	this.setResizable(false);
	  filename.setColumns(20);
	  dir.setColumns(30);
	  output.setColumns(30);
	  jfile.setColumns(20);
	  objectFile.setColumns(30);
	  ofile.setColumns(24);
	  ofile.setRows(8);
	  
    new JPanel();
	new JPanel();
	new JPanel();
    java.net.URL path = Compiler.class.getResource("back.jpg");
    JLabel bg=new JLabel(new ImageIcon( path.getFile()));
    SpringLayout layout =new SpringLayout();
    bg.setLayout(layout);
    
    dir.setText("Path will appear here");
    filename.setText("FILE /  MESSAGE");
    output.setText("Java File Path");
    jfile.setText("Java file name");
    ofile.setText(" Status");
    objectFile.setText("Byte-Code file Path");
    
    dir.setEditable(false);
    filename.setEditable(false);
    output.setEditable(false);
    objectFile.setEditable(false);
    jfile.setEditable(false);
   ofile.setEditable(false);
    
    bg.add(x);
    layout.putConstraint(SpringLayout.WEST, x, 10, SpringLayout.WEST, this);
    layout.putConstraint(SpringLayout.NORTH, x, 25, SpringLayout.NORTH, this);
    
    bg.add(dir);
    layout.putConstraint(SpringLayout.NORTH, dir, 25, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, dir, 20, SpringLayout.EAST, x);
    
    bg.add(open);
    layout.putConstraint(SpringLayout.NORTH, open, 25, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, open, 20, SpringLayout.EAST,dir);
    
    bg.add(filename);
    
    add(bg);
    layout.putConstraint(SpringLayout.NORTH, filename, 55 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, filename, 122, SpringLayout.WEST, this);
   
    bg.add(convert);
    layout.putConstraint(SpringLayout.NORTH, convert, 100 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, convert, 255, SpringLayout.WEST, this);
    
    bg.add(y);
    layout.putConstraint(SpringLayout.NORTH, y, 150 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, y, 10, SpringLayout.WEST, this);
    
    bg.add(output);
    layout.putConstraint(SpringLayout.NORTH, output, 150 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, output, 27, SpringLayout.EAST, y);
    
    bg.add(jfile);
    layout.putConstraint(SpringLayout.NORTH, jfile, 180 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, jfile, 27, SpringLayout.EAST, y);
    
    
   /* bg.add(done);
    layout.putConstraint(SpringLayout.NORTH, done, 220 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, done, 270, SpringLayout.WEST, this);
    */
    bg.add(z);
    layout.putConstraint(SpringLayout.NORTH, z, 300 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, z, 10, SpringLayout.WEST, this);
    
   /* bg.add(objectFile);
    layout.putConstraint(SpringLayout.NORTH, objectFile, 350 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, objectFile, 20, SpringLayout.EAST, z);
    */
    bg.add(ofile);
    layout.putConstraint(SpringLayout.NORTH, ofile, 300 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, ofile, 115, SpringLayout.WEST,this);
    
    bg.add(see);
    layout.putConstraint(SpringLayout.NORTH, see, 500 , SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, see, 400, SpringLayout.WEST,this);
    
    bg.add(clear);
    layout.putConstraint(SpringLayout.NORTH,clear, 500 , SpringLayout.NORTH, this);
   layout.putConstraint(SpringLayout.WEST, clear, 10, SpringLayout.WEST,this);
    
    
    
  //LISTENERS
    open.addActionListener(new ActionListener(){

  		@Override
  		public void actionPerformed(ActionEvent e) {
  			// TODO Auto-generated method stub
  			 JFileChooser c = new JFileChooser();
  		      // Demonstrate "Open" dialog:
  		      int rVal = c.showOpenDialog(Compiler.this);
  		    
  		      if (rVal == JFileChooser.APPROVE_OPTION) {
  		    	  
  		    	  fname=c.getSelectedFile().getName();
  		    	  fpath=c.getCurrentDirectory().toString();
  		    	 // exten=fname.substring(fname.lastIndexOf("."),fname.length());//ex is .txt will be stored in txt
  		    	  String x[]=fname.split("\\.");
  		    	  name=x[0];
  		    	  exten=x[1];
  		    	//ADD FILE EXTENSION CHECKING
  		    	  

  		        filename.setText("File Selected:	"+c.getSelectedFile().getName());
  		        dir.setText("Path:	"+c.getCurrentDirectory().toString());
  		      convert.setEnabled(true);
  			
  		      
  		      }
  		      
  		      if (rVal == JFileChooser.CANCEL_OPTION) {
  		        filename.setText("No file Selected");
  		        dir.setText("If Selected,Path will appear here");
  		      }
  		
  		}	
      });
      
    convert.addActionListener(new ActionListener(){

  		@Override
  		public void actionPerformed(ActionEvent e) {
  			// Compile and Parse Code Here

  			failed=true;
  		    output.setText("Java File Path");
  		    jfile.setText("Java file name");
  		    ofile.setText(" Status");
  		   
  			if(exten.compareTo("yang")!=0)
  			{
  				filename.setText("Invalid File - Choose Again");
  				
  			}
  			else
  			{
  			try {
				//grammar
				//String exampleString = null;
		        BufferedReader br = new BufferedReader(new FileReader(fpath+"/"+name+".yang"));
		

			String sCurrentLine;
			ofile.setText("Parsing Started\n");
			while ((sCurrentLine = br.readLine()) != null) {
				//InputStream is = new ByteArrayInputStream(exampleString.getBytes());
                if(parser == null) parser = new SyntaxChecker(br);
                else SyntaxChecker.ReInit(br);
               
              
                  switch (SyntaxChecker.start())
                  {
                    case 0 :
                    	//System.out.println("expression parsed ok.");
                    	failed=false;
                    break;
                    default ://System.out.println("expression parsed wrong");
                    break;
                  }
			}
			
			
			br.close();


		//Converter.convert(fpath,name);
			}
  			
  			 catch (Exception e1) {
				// TODO Auto-generated catch block
			//	e1.printStackTrace();
				parseError=e1.getMessage();
			} 
		catch (Error e1)
        	{
			failed=true;
        // System.out.println("error in expression.\n"+ e1.getMessage());
                                              //  ofile.setText(""+e1.getMessage());

        	}	
  			finally{
  				try {
  					
  					
  					if(failed==false)
					{
  						Converter.convert(fpath,name);
  						output.setText(fpath);
  						new Thread(new Runnable(){
  		  				

						@Override
						public void run() {
							try{
								Thread.sleep(550);
								ofile.append("Lexical Analysis\n");
								Thread.sleep(1000);
								ofile.append("Syntax Analysis\n");
								Thread.sleep(1000);
								ofile.append("Parse-Successfull\n");
								Thread.sleep(1000);
								ofile.append("Generating Java File\n");
								Thread.sleep(1000);
								
								jfile.setText(name+".java");
								see.setEnabled(true);
								ofile.append("Java file Generated Successfully,With name :\n"+name+".java");
							}
							catch(Exception e){};
						}
  		  				
  		  			}).start();
  					
  					
  					
  					
  					
  		  			}
  					else
  					{
  						ofile.append(parseError);
  						jfile.setText("Error in Parsing");
  					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
  			}
  			
  			//done.setEnabled(true);
  			}
  		}});
      
    
 /*   done.addActionListener(new ActionListener(){

  		@Override
  		public void actionPerformed(ActionEvent e) {
  			//Auto Compile and show path
  			//ObjectGenerator.generate(fpath,name);
  			objectFile.setText(fpath);
  			
  			see.setEnabled(true);
  		
  		}});*/
	see.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 Desktop desktop = Desktop.getDesktop();
		        File dirToOpen = null;
		        try {
		            dirToOpen = new File(fpath);
		            desktop.open(dirToOpen);
		        } catch (IllegalArgumentException iae) {
		           iae.getMessage();
		        } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}});
	
clear.addActionListener(new ActionListener(){

	@Override
	public void actionPerformed(ActionEvent e) {
		
	fname="";	 fpath="";name="";exten="";
		
		dir.setText("Path will appear here");
	    filename.setText("FILE /  MESSAGE");
	    output.setText("Java File Path");
	    jfile.setText("Java file name");
	    ofile.setText(" Status");
	    objectFile.setText("Byte-Code file Path");
		
	    see.setEnabled(false);
	    convert.setEnabled(false);
		done.setEnabled(false);
	//reset all other inits too if any
	}
	
});
}


  

  public static void run(final JFrame frame, final int width, final int height) {
  
	  
	
								  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								  frame.setSize(width, height);
								  frame.setVisible(true);
									
  }
  
  public static void main(String[] args) throws Exception {
	 
	  SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				Compiler.run(new Compiler(), 600, 600);
			
			}
	  });
  }
  
  
  
} 

