
package mes;

import java.io.*;
import java.net.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.util.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * This class is responsible for handling the communications
 * @author Mário Xavier
 */
    
    /**
     * Starts the protocol
     * @param protocolType 
     */
 
/*
public void startProtocol()
    {   
        // starts UDP protocol
        if(type == "UDP")
        {
            try
            {
                // creates new datagram socket (Port: 54321)  
                DatagramSocket serverSocket = new DatagramSocket(port);
                // creates array of bytes (receiveData)
                byte[] receiveData = new byte[1024]; 
       
                while(true)
                {
            
                    // creates new packet to receive data
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    // receives a packet from port 54321
                    serverSocket.receive(receivePacket);
                    // retrieves the sentence from the packet
                    String sentence = new String( receivePacket.getData());
                    // prints the sentence
                    System.out.println("RECEIVED: " + sentence);
                }
            }
        */  