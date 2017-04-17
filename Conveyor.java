/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

/**
 *
 * @author Utilizador
 */
public class Conveyor {
    
    public String ID;
    private String type;
    private String group;
    private String status;
    
    
      
    /**
     * Constructor
     * @param conveyorGroup
     * @param conveyorType
     */
    public Conveyor(String conveyorGroup, String conveyorType)
    {
      if (null == conveyorType)
          System.out.println("No conveyor type given.\n");
      else
      {
          // sets the conveyor group
          switch(conveyorGroup)
          {
              case "cell":
                  group = conveyorGroup;
                  break;
                  
              case "transport":
                  group = conveyorGroup;
                  break;
                  
              default:
                System.out.println("Conveyor group not recognized.\n");
          }
          
          // sets the conveyor type
          switch(conveyorType)
          {
              case "rotating":
                  type = conveyorType;
                  break;
                  
              case "linear":
                  type = conveyorType;
                  break;
              case "slide":
                  type = conveyorType;
                  break;
              default:
                System.out.println("Conveyor type not recognized.\n");
          }
       }
    }
    
    /**
     * Updates the status of the conveyor
     * @param conveyorStatus
     * @return 
     */
    public boolean updateStatus(String conveyorStatus)
    {
        // if no conveyor status was given
        if (null == conveyorStatus)
        {
            System.out.println("No conveyor status was defined.\n");
            return false;
        }
        // if a conveyor status was given
        else
        {
            // Running some tests
            
            char[]c = conveyorStatus.toCharArray();
            
            for (int i=0; i<c.length; i++)
            {
                System.out.println(c[i]+"\n");
            }
            
            status = conveyorStatus;
            return true;
        }
    }
    
    /**
     * Gets conveyor status
     * @return 
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * Gets the conveyor group
     * @return 
     */
    public String getGroup()
    {
        return group;
    }
    
    /**
     * 
     * Sets a conveyor type
     * @param conveyorType
     * @return 
     */
    public boolean setType(String conveyorType)
    {
        // if no conveyorType was given
        if (null == conveyorType)
        {
            System.out.println("No conveyor type was given.\n");
            return false;
        }
        // if a conveyor type was given
        else
        {
            type = conveyorType;
            return true;
        }
    }
    
    /**
     * Gets the conveyor type
     * @return 
     */
    public String getType()
    {
        return type;   
    }
}
