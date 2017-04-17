/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;


import java.util.*;


/**
 *
 * @author Utilizador
 */
public class Monitor 
{
    
    private char[] inputData;
    private char[] outputData;
    private Modbus protocolToPLC;
    private Factory virtualFactory;
    
    
    public Monitor(Modbus protocol, Factory receivedFactory)
    {
        protocolToPLC = protocol;
        virtualFactory = receivedFactory;
    }
    
    public char[] getInputData()
    {
        return inputData;
    }
    
    public char[] getOutputData()
    {
        return outputData;
    }
    
    

    /**
     * returns a char array with the values of all the sensors read by the PLC
     * @return 
     */
    public void readSensors()
    {        
        // dataReceived contains a string with the value of all sensors, separated by a space on each byte
        String dataReceived = protocolToPLC.readModbus(0,146);
        
        
        // s is a string array in which each position has a byte
        String[] split = dataReceived.split(" ");
        
        // cycle to invert the order of each byte
        int i = 0;
        do
        {
        split[i] = new StringBuilder(split[i]).reverse().toString();
        i++;
        }while(i<19);
        
        // emptying dataReceived
        dataReceived="";
        
        // placing a string on dataReceived with the data in the intended format
        for (String tool : split)
        {
           dataReceived = dataReceived + tool;
        }
        
        // inputData contains an array in which each position has the value of the corresponding sensor on the PLC
        inputData = dataReceived.toCharArray();
        

        
        
    }

    
    public void readActuators()       
        {

            
            // dataReceived contains a string with the value of all actuators, separated by a space on each byte
            String dataReceived = protocolToPLC.readModbus(160,200);
        
        
            // s is a string array in which each position has a byte
            String[] split = dataReceived.split(" ");
        
            // cycle to invert the order of each byte
            int i = 0;
            do
            {
            split[i] = new StringBuilder(split[i]).reverse().toString();
            i++;
            }while(i<25);
        
            // emptying dataReceived
            dataReceived="";
        
            // placing a string on dataReceived with the data in the intended format
            for (String tool : split)
            {
                dataReceived = dataReceived + tool;
            }
        
            // outputData contains an array in which each position has the value of the corresponding actuator on the PLC
            outputData = dataReceived.toCharArray();
            

        
        }
    
    
    
    
    public void updateTransportConveyors()      // TO DO
    {
        // Reads the value of all the Sensors and Actuators at the time this method is called
        this.readSensors();
        this.readActuators();
        
        // stores an array of all the Conveyors that are part of the Transport
        Conveyor[] conveyorArray = virtualFactory.getConveyors("transport");
        

        
        // string dataOfConveyor will store the data corresponding to the Conveyor being updated
        String dataOfConveyor = "";
        
        // inputIndexArray and outputIndexArray will store the address of each sensor/actuator of the Conveyor being updated
        List<Integer> inputIndexArray = new ArrayList<Integer>();
        List<Integer> outputIndexArray = new ArrayList<Integer>();
        
        // iterator will be the index of inputIndexArray and outputIndexArray
        int iterator;
        
        for (Conveyor conveyor : conveyorArray)
        {
            switch(conveyor.ID)
            {
                case("00"):
                {
                   // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(0);
                   outputIndexArray.add(0); outputIndexArray.add(1);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                }
                case("01"):
                {
                   // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(2);
                   outputIndexArray.add(5); outputIndexArray.add(6);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("02"):
                {
                   // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(3); inputIndexArray.add(4); inputIndexArray.add(5);
                   outputIndexArray.add(7); outputIndexArray.add(8); outputIndexArray.add(9); outputIndexArray.add(10);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("030"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(6);
                   outputIndexArray.add(11); outputIndexArray.add(12);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                
                case("031"):
                {
                   // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(7);
                   outputIndexArray.add(11); outputIndexArray.add(12);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                
                case("04"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(32);
                   outputIndexArray.add(47); outputIndexArray.add(48);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                }
                case("05"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(33); inputIndexArray.add(34); inputIndexArray.add(35);
                   outputIndexArray.add(49); outputIndexArray.add(50); outputIndexArray.add(51); outputIndexArray.add(52);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                }
                case("06"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(53);
                   outputIndexArray.add(79); outputIndexArray.add(80);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("07"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(54); inputIndexArray.add(55); inputIndexArray.add(56);
                   outputIndexArray.add(81); outputIndexArray.add(82); outputIndexArray.add(83); outputIndexArray.add(84);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("080"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(57); 
                   outputIndexArray.add(85); outputIndexArray.add(86);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("081"):
                {
                   // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(58); 
                   outputIndexArray.add(85); outputIndexArray.add(86);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("09"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(83); 
                   outputIndexArray.add(121); outputIndexArray.add(122);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("010"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(84); inputIndexArray.add(85); inputIndexArray.add(86); 
                   outputIndexArray.add(123); outputIndexArray.add(124); outputIndexArray.add(125); outputIndexArray.add(126);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("011"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(104); 
                   outputIndexArray.add(153); outputIndexArray.add(154);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                
                case("012"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(105); inputIndexArray.add(106); inputIndexArray.add(107); 
                   outputIndexArray.add(155); outputIndexArray.add(156); outputIndexArray.add(157); outputIndexArray.add(158);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("013"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(128);
                   outputIndexArray.add(176); outputIndexArray.add(177);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("014"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(129); inputIndexArray.add(130); inputIndexArray.add(131);
                   outputIndexArray.add(178); outputIndexArray.add(179); outputIndexArray.add(180); outputIndexArray.add(181);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }
                case("015"):
                {
                    // clears dataOfConveyor string
                   dataOfConveyor="";
                   
                   // clears both data index arrays
                   inputIndexArray.clear();
                   outputIndexArray.clear();
                   
                   // adds the sensors and actuators memory slots of this Conveyor
                   inputIndexArray.add(132);
                   outputIndexArray.add(182); outputIndexArray.add(183);
                   
                   // places in dataOfConveyor the value of each sensor of the Conveyor
                   for (iterator=0; iterator<inputIndexArray.size(); iterator++)
                       dataOfConveyor+= inputData[inputIndexArray.get(iterator)];
                   
                   // places in dataOfConveyor the value of each actuator of the Conveyor
                   for (iterator=0; iterator<outputIndexArray.size(); iterator++)
                       dataOfConveyor+=outputData[outputIndexArray.get(iterator)];
                  
                    // updates the conveyor passing as argument the string dataOfConveyor containing all 
                    // the necessary information for it to update itself correctly
                    conveyor.updateStatus(dataOfConveyor);
                   
                   
                   break;
                    
                }

            }
        }
        
    }
    

    
    
    

    
    
    
}
