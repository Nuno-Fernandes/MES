/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.net.InetAddress;
import javax.swing.JOptionPane;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.util.BitVector;


/**
 *This class extends Protocol
 * @author Mário Xavier
 */
public final class Modbus {
    
    
    public String ID;
    private String address;
    private int port;
    // Modbus connection
    private TCPMasterConnection modbusConnection = null; 
    // Modbus transaction  
    private ModbusTCPTransaction modbusTransaction = null; 
    // Modbus read request
    private ReadInputDiscretesRequest modbusReadRequest = null;     
    // Modbus read response
    private ReadInputDiscretesResponse modbusReadResponse = null; 
    // Modbus write request
    private WriteMultipleCoilsResponse modbusWriteResponse = null;
    //Modbus write response
    private WriteMultipleCoilsRequest modbusWriteRequest = null;
    //the slave's address
    private InetAddress modbusAddress = null; 
    // offset where to start reading from
    private int startReadingReference = 0; 
    // number of bits to read
    private int noOfBits = 0;
    // a loop for repeating the transaction
    private int noOfTransactions = 1;       
    // offset where to start writing
    private int startWritingReference = 0;
    // Bit Vector to Write in PLC
    private BitVector bitsToWrite;
    
    
    public Modbus() {
        // initializes modbus protocol
        initModbus();
    }
    
    /**
     * Gets number of bits to read/write
     * @return 
     */
    public int getNoOfBits()
    {
        return noOfBits;
    }
    
    /**
     * //Sets number of bits to read/write
     * @param bitCount
     * @return 
     */
    public boolean setNoOfBits(int bitCount)
    {
        noOfBits = bitCount;
        // returns true if bitCount is not zero
        return bitCount != 0;
    }
    
    /**
     * Gets bits to write
     * @return 
     */
    public BitVector getBitsToWrite()
    {
        return bitsToWrite;
        
    }
    
    
    /**
     * Sets bits to write
     * @param vectorOfBits
     * @return 
     */
    public boolean setBitsToWrite(BitVector vectorOfBits)
    {
        bitsToWrite = vectorOfBits;
        // returns true if bitsToWrite is not null
        return bitsToWrite != null;
    }
    
   
    /**
     * Gets modbus address
     * @return 
     */
    public String getModbusAddress()
    {
        return address;
    }
    
     /**
     * Sets the protocol address
     * @return 
     *true if address is not empty
     *false if address is empty
     */
    public boolean setModbusAddress()
    {
       
        // user prompt
        address = JOptionPane.showInputDialog("Insert address");
        
        // if given address is empty
        if ("".equals(address))
            return false;
        // if the address is not empty
        else 
            return true;
    }
    
    /**
     * Gets the protocol port
     * @return  port
     */
    public int getModbusPort()
    {
        return port;
    }
    
    /**
     * Sets the protocol port
     * @return 
     * true
     * false
     */
    public boolean setModbusPort()
    {
 
        // user prompt
        String inputPort = JOptionPane.showInputDialog("Insert port");
      
        port = Integer.parseInt(inputPort);
        
        // returns true if port is not empty
        return port != 0; 
    }
    
    /**
     * Gets start reading point in PLC
     * @return 
     */
    public int getStartReadingReference()
    {
        return startReadingReference;
    }
    
    /**
     * Sets start reading point in PLC
     * @param offset
     * @return 
     */
    public boolean setStartReadingReference(int offset)
    {
        startReadingReference = offset;
        return true;
    }
   
    /**
     * Gets the offset where to start writing in PLC
     * @return 
     */
    public int getStartWritingReference()
    {
     return startWritingReference;   
    }
    
    /**
     * Sets start writing point in PLC
     * @param offset
     * @return 
     */
    public boolean setStartWritingReference(int offset)
    {
        startWritingReference = offset;
        return true;
    }
    
    
    /**
     * Sets a modbus connection
     * @return 
     */
    public boolean setModbusConnection()
    {
        try
        {
            // sets connection address
            modbusConnection = new TCPMasterConnection(
                    InetAddress.getByName(address));

            // sets connection port
            modbusConnection.setPort(port);
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            System.exit(1);
            return false;
        }
        // if a modbus connection was created
        return true;
    }
    
    /**
     * Gets a modbus connection
     * @return 
     */
    public TCPMasterConnection getModbusConnection()
    {
        return modbusConnection;
    } 
  
    /**
     * Sets a modbus transaction
     * @param transactionType
     * @return 
     */
    public boolean setModbusTransaction(String transactionType)
    {
        // creates a new transaction
        modbusTransaction = new ModbusTCPTransaction(modbusConnection);
      
        // if error occured while creating modbusTransaction
        if (modbusTransaction == null)
        {
            System.out.println("Error creating new transaction.\n");
                return false;
        }
        
        // no errors creating new transaction
        else 
        {
            // if no type of transaction was given
            if (null == transactionType)
            {
                System.out.println("No transaction type was given\n");
                return false;
            }
            // if a transaction type was given
            else
                switch(transactionType)
                {
                    // if type is 1 we set up a Transaction to Read Discrete Inputs
                    case "read":
                    {
                        modbusTransaction.setRequest(modbusReadRequest);
                        break;
                    }

                    // if type is 2 we set up a Transaction to Write Multiple Coils
                    case "write":
                    {
                        modbusTransaction.setRequest(modbusWriteRequest);
                        break;
                    }

                    default: 
                    {
                        System.out.println("Transaction type not recognized.\n");
                        return false;
                    }
               }
            // if transaction was created
            return true;
        }  
    }
    
    /**
     * Gets a modbus transaction
     * @return 
     */
    public ModbusTCPTransaction getModbusTransaction()
    {
        return modbusTransaction;
    } 
   
    /**
     * Sets a modbus transaction
     * @return 
     */
    public boolean setModbusReadRequest()
    {
        modbusReadRequest = new ReadInputDiscretesRequest(
                startReadingReference, noOfBits);
        
        // returns true if read request is not null
        return modbusReadRequest != null;
    }
    
    /**
     * Gets a modbus read request
     * @return 
     * true
     * false
     */
    public ReadInputDiscretesRequest getModbusReadRequest()
    {
        return modbusReadRequest;
    }     
    
    /**
     * Sets a write request
     * @return 
     * true
     * false
     */
    public boolean setModbusWriteRequest()
    {
        modbusWriteRequest = new WriteMultipleCoilsRequest(startWritingReference, bitsToWrite);
        
        // returns true if the write request is not null
        return modbusWriteRequest != null;  
    }
    
    /**
     * returns the write multiple coils request
     * @return 
     */
    public WriteMultipleCoilsRequest getModbusWriteRequest()
    {
        return modbusWriteRequest;
    }     
    
    /**
     * Initializes the protocol
     * @return 
     * true
     * false
     */
    public boolean initModbus() 
    {
        // error setting the port
        if(setModbusPort() == false) {
            System.out.println("Error inserting port.\n");
            return false;
        } 
        // error setting the address
        else if (setModbusAddress() == false)
        {
            System.out.println("Error inserting address.\n");
            return false;
        }
        // no error
        else
            return true;
    }   
    
    /**
     * Opens a new connection
     * @return 
     */
    public boolean openConnection()
    {
        try
        {
            modbusConnection.connect();
        }
        catch (Exception ex) 
        {   
            ex.printStackTrace();
            System.exit(1);
        }
        // returns true if connection is on
        return modbusConnection.isConnected();
    }
    
/**
 * Reads from PLC
 * @param offset
 * @param bitCount
 * @return 
 */
    public String readModbus(int offset, int bitCount)
    {
        if(setStartReadingReference(offset))
        {
            //TO DO
        }
        if(setNoOfBits(bitCount))
        {
            //TO DO
        }
        
        // if read request was created succsessfully
        if(setModbusReadRequest())
        {
            // if read transaction was read successfully
            if(setModbusTransaction("read"))
            {
                try
                {
                    // executes a reading
                    modbusTransaction.execute();
                }
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                    System.exit(1);
                }
               
                // receives a reading
                modbusReadResponse = (ReadInputDiscretesResponse) modbusTransaction.getResponse();
                return modbusReadResponse.getDiscretes().toString();
            }
            // if some error occured setting transaction;
            else
                return null;
        }
        // if some error occured setting the request;
        else
            return null;
    }   
    
    /**
     * Writes to PLC
     * @param offset
     * @param vectorOfBits
     * @return 
     */
    public String writeModbus(int offset, BitVector vectorOfBits)
    {
        if(setStartWritingReference(offset))
        {
            //TO DO
        }
        
        if (setBitsToWrite(vectorOfBits))
        {
            //TO DO
        }
        
        // if a write request was created
        if (setModbusWriteRequest())
        {
            // if a write transaction was created
            if (setModbusTransaction("write"))
            {
                try
                {
                    // executes a reading
                    modbusTransaction.execute();
                }          
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    System.exit(1);
                }
                
                modbusWriteResponse = (WriteMultipleCoilsResponse) modbusTransaction.getResponse();
                
                // Returns the response in String format
                return modbusWriteResponse.toString();
            }
            // if some error occured setting transaction;
            else
                return null;            
        }
        // if some error occured setting the request;
        else
            return null;
    }
}
