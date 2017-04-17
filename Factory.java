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
public class Factory {
    
    public int ID;
    private boolean status;
    private Conveyor[] cellConveyors, transportConveyors;
    private Transport inputTransport, outputTransport;
    private Machine[] machines;
    private Cell[] parallelCells, serialCells;
    
    
    /**
     * Initializes factory
     * @return 
     */
    public boolean initFactory()
    {
        this.addCells("parallel", 2, this);
        this.addCells("serial", 2, this);
        this.addTransport(this);
        status = true;
        return status;
    }
    
    /**
     * Gets factory status
     * @return 
     */
    public boolean isReady()
    {
        return status;
    }

    /**
     * Gets all the conveyors of a given type
     * @param conveyorType
     * @return 
     */
    public Conveyor[] getConveyors(String conveyorType)
    {
        if (null == conveyorType)
            return null;
        else
        {
            switch (conveyorType) {
                case "transport":
                    return transportConveyors;
                case "cell":
                    return cellConveyors;
                // if some error occured
                default:
                    return null;
            }
        }
    }
    
    /**
     * Gets a specific conveyor
     * @param conveyors
     * @param conveyorIndex
     * @return 
     */
    public Conveyor getConveyor(Conveyor[] conveyors, int conveyorIndex)
    {
        // if no conveyor array was given
        if (null == conveyors)
            return null;
        // if a conveyor array was given
        else
        {
            // if is a valid conveyor ID
            if (conveyorIndex > 0)
                return conveyors[conveyorIndex];
            // if some error occured
            else
                return null;
        }
    }
    
    /**
     * Gets machines
     * @return 
     */
    public Machine[] getMachines()
    {
        return machines;
    }
    
    /**
     * Gets a specific conveyor
     * @param machines
     * @param machineIndex
     * @return 
     */
    public Machine getConveyor(Machine[] machines, int machineIndex)
    {
        // if no machine array was given
        if (null == machines)
            return null;
        // if a machine array was given
        else
        {
            // if is a valid machine ID
            if (machineIndex > 0)
                return machines[machineIndex];
            // if some error occured
            else
                return null;
        }
    }
 
    /**
     * Gets cells of a given type
     * @param cellType
     * @return 
     */
    public Cell[] getCells(String cellType)
    {
        if (null == cellType)
            return null;
        else
        {
            switch (cellType) {
                case "parallel":
                    return parallelCells;
                case "serial":
                    return serialCells;
                    // if some error occured
                default:
                    return null;
            }
        }
    }
    
    /**
     * Gets a specific conveyor
     * @param cells
     * @param cellIndex
     * @return 
     */
    public Cell getCell(Cell[] cells, int cellIndex)
    {
        // if no cell array was given
        if (null == cells)
            return null;
        // if a cell array was given
        else
        {
            // if is a valid cell ID
            if (cellIndex > 0)
                return cells[cellIndex];
            // if some error occured
            else
                return null;
        }
    }
  
    
    
   
    
    /**
     * Adds conveyors of a given type
     * @param conveyorGroup
     * @param conveyorType
     * @param numberOfConveyors 
     * @return
     */
    
    public boolean addConveyors(String conveyorGroup, String conveyorType,
            int numberOfConveyors)
    {
        // no conveyor type was given
        if (null == conveyorType)
        {
            System.out.println("No conveyor type given\n");
            return false;
        }
        
        // the number of conveyors is zero
        else if (numberOfConveyors == 0)
        {
            System.out.println("The number of conveyors if zero.\n");
            return false;
        }
        
        // if a number of conveyors was given
        else
        switch(conveyorType)
        {
            // creates transport conveyors
            case "transport":
                // creates conveyors
                for(int i = 0; i < numberOfConveyors; i++)
                    transportConveyors[i] = new Conveyor(conveyorGroup, conveyorType);
                break;
            
            // creates cell conveyors
            case "cell":
                // creates conveyors
                for(int i = 0; i < numberOfConveyors; i++)
                    cellConveyors[i] = new Conveyor(conveyorGroup,conveyorType);
                break; 
                
            default:
                System.out.println("Conveyor type not recognized.\n");
                return false;
        }  
        return true;
    }
    
    
    
    
    /**
     * Adds machines of a given type
     * @param machineType
     * @param numberOfMachines 
     * @return
     */
    public boolean addMachines(String machineType, int numberOfMachines)
    {        
        // no machine type was given
        if (null == machineType)
        {
            System.out.println("No machine type given\n");
            return false;
        }
        
        // the number of conveyors is zero
        else if (numberOfMachines == 0)
        {
            System.out.println("The number of machines if zero.\n");
            return false;
        }
        
        // if a number of conveyors was given
        else
        switch(machineType)
        {
            // creates type A machines
            case "A":
                for(int i = 0; i < numberOfMachines; i++)
                    machines[i] = new Machine(machineType);
                break;
            // creates type B machines    
            case "B":

                for(int i = 0; i < numberOfMachines; i++)
                    machines[i] = new Machine(machineType);
                break;
            // creates type C machines
            case "C":
                for(int i = 0; i < numberOfMachines; i++)
                    machines[i] = new Machine(machineType);
                break;
            // if machine type is not recognized
            default:
                System.out.println("Machine type not recognized.\n");
                return false;
        }  
        return true;
    }
    
    /**
     * Adds cells of a given type
     * @param cellType
     * @param numberOfCells
     * @param currentFactory
     * @return 
     */
    public boolean addCells(String cellType, int numberOfCells, Factory
            currentFactory)
    {  
        // no cell type was given
        if (null == cellType)
        {
            System.out.println("No cell type given\n");
            return false;
        }
        
        // the number of cells is zero
        else if (numberOfCells == 0)
        {
            System.out.println("The number of cells if zero.\n");
            return false;
        }
        
        // if a number of cells was given
        else
        switch(cellType)
        {
            case "parallel":
                // creates cells
                 for(int i = 0; i < numberOfCells; i++)
                    parallelCells[i] = new Cell(cellType, currentFactory);
                break;
            
            case "serial":
                // creates cells
                for(int i = 0; i < numberOfCells; i++)
                    serialCells[i] = new Cell(cellType, currentFactory);
                break; 
                
            default:
                System.out.println("Cell type not recognized.\n");
                return false;
        }  
        return true;
    }
    
    /**
     * Adds transport to factory
     * @param currentFactory
     * @return 
     */
    public boolean addTransport(Factory currentFactory)
    {
        // creates input transport
        inputTransport = new Transport("input", currentFactory);
       
        // creates output transport
        outputTransport = new Transport("output", currentFactory);
        
        if (null == inputTransport)
        {
            System.out.println("Error creating input transport.\n");
            return false;                    
        }
        
        else if (null == outputTransport)
        {
            System.out.println("Error creating output transport.\n");
            return false; 
        }
        // if nb error ocurred
        else
            return true;
    }
}