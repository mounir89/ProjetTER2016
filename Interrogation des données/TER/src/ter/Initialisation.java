/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author proprietaire
 */
class Initialisation {
   
    
    static HashMap<String,ArrayList<String>> initTopicOperation()
    {
        
        return InterrogationBDD.getTopicOperation();
        
    }
    
    static HashMap<String,ArrayList<String>> initRelationParameters()
    {
        return InterrogationOntologie.initRelationParameters();
    }
    
}
