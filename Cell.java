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
public class Cell {
    
    public int ID;
    private String type;
    
    /**
     * Constructor
     * @param cellType 
     * @param currentFactory
     */
    public Cell(String cellType, Factory currentFactory)
    {
         if (null == cellType)
            System.out.println("No conveyor type given.\n");
         else
         switch(cellType)
         {
            case "parallel":
                type = cellType;
                // creates cells in the current factory
                currentFactory.addConveyors("cell", "linear", 3);
                currentFactory.addMachines("A", 1);
                
                break;

            case "serial":
                type = cellType;
                // creates cells in the current factory
                currentFactory.addCells(cellType, 4, currentFactory);
                break; 
            
            default:
                System.out.println("Cell type not recognized.\n");
         }
    }
    
}


// TESTE TESTE ESTE
