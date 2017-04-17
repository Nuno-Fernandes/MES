/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

/**
 *
 * @author Mário Xavier
 */
public class Transport {
    
    private int ID;
    private String type;
    private String status;
    
    /**
     * Constructor that creates his own conveyors
     * @param transportType 
     * @param currentFactory
     */
    public Transport(String transportType, Factory currentFactory)
    {
        if (null == transportType)
            System.out.println("No transport type given.\n");
        else
        switch(transportType)
        {
            case "input":
                type = transportType;
                // creates transport conveyors in the curren factory
                currentFactory.addConveyors("linear","transport", 30);
                break;

            case "output":
                type = transportType;
                //creates transport conveyors in the current factory
                currentFactory.addConveyors("linear","transport", 30);
                break;
            
            default:
                System.out.println("Transport type not recognized.\n");
        }
    }
    
}