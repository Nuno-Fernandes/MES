/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.sql.SQLException;
import java.util.*;
import javax.swing.JOptionPane;
import net.wimpi.modbus.util.BitVector;

/**
 * Classe para gerir o arranque e funcionamento do sistema
 * @author Mário Xavier
 */
public class systemManager 
{    
    private String ID;
    private PriorityQueue taskQueue;
    private int status;
    
     public static void main (String[] args) throws SQLException
     {     
        Monitor factoryMonitor;
        Modbus protocolToPLC;
        UDP protocolToERP;
        Factory virtualFactory;
        
        
        // creates UDP protocol object
        //protocolToERP = new Protocol();
        // error creating protocol to ERP
        //if (protocolToERP == null)
        {
            //TO DO
        }
        
        // creates Modbus protocol object
        protocolToPLC = new Modbus();
        virtualFactory = new Factory();
        factoryMonitor = new Monitor(protocolToPLC, virtualFactory); 
        // error creating protocol to PLC
        if (protocolToPLC == null)
        {
            //TO DO
        }
        
        if (protocolToPLC.setModbusConnection())
            System.out.println("Modbus connection on.\n");
        else
            System.out.println("Modbus connection failed.\n");

        
        protocolToPLC.openConnection();
        
        
        
        
        

        //  Testes do NUNO a partir daqui
        
        
        /*
        Conveyor Conveyor1, Conveyor2;
        
        Conveyor1 = new Conveyor("transport","linear","04");
        Conveyor2 = new Conveyor("transport","slide","010");
        
        
        
        Conveyor[] conveyorArray = new Conveyor[2]; 
        conveyorArray[0]=Conveyor1;
        conveyorArray[1]=Conveyor2;
        
        factoryMonitor.updateTransportConveyors(conveyorArray);
        
        
        
        */
        

        
        
        //Tests the monitor readSensors() method
        
        /*
        factoryMonitor.readSensors();
        System.out.println(factoryMonitor.getInputData());
        */

        
        

        // ************  HOW TO PLACE A BLOCK IN FACTORY ****************
        
        /*
        // creating a bit vector of size 8
        BitVector b = new BitVector(8);
        
        //b.setBit(3, true);
        
        // setting all bits to 1

        
        // prints the bit vector
        System.out.println(b.toString());
        
        // prints the result of the function writeModbus (Write Multiple Coils) 
        System.out.println(protocolToPLC.writeModbus(144,b));
        */
        


        


        
        
        
        
       // creates a database object
       Database db = new Database();
      
       // if database is initialized
       if(db.initDatabase("org.postgresql.Driver", 
                "jdbc:postgresql://dbm.fe.up.pt/sinf16g67"))
       {
          // if credentials are right
          if(db.setCredentials("sinf16g67","manueljoaofraga"))
           {
               // if a databased connection is opened
               if(db.openConnection())
               {
                   // executes a query
                   db.executeQuery("CREATE TABLE mes.TEST_THREE();");
              }
          }
       }
         
        // creates a factory object
        Factory simulatedFactory = new Factory();

        // initializes factory 
        simulatedFactory.initFactory();
        
        // checks if factory is ready
        //if((simulatedFactory.isReady));
  
        
        
        
        
        
        
        
        
        
        
 }
   
}
