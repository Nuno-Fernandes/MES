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
public class Machine {
    
    public int ID;
    private String type;
    private String status;
    
    /**
     * 
     * @param machineType 
     */
    public Machine(String machineType)
    {
        if (null == machineType)
            System.out.println("Machine type not given.\n");
        else
        {
            type = machineType;
        }
    }
    
}
