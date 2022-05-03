/**
 * Name: Jerimey Simons 
 * Date: 3/13/2022  
 * Description: Node class for patient queue, sort, and pop methods. 
 * 
 */
package whackyhospital;

public class PatientNode {
    public String name;
    public String queue;
    public boolean employee;
    public boolean insured;
    String condition ="";
    public PatientNode next;
    
    
    /**
     * Method to sort singly linked list with condition priority.
     * @param head <code>PatientNode</code> Object holding patient information.
     * @param p <code>PatientNode</code> node containing patient information.
     * @return head <code>PatientNode</code> return updated sorted head of linked list.
     */
    public  PatientNode sortList(PatientNode head, PatientNode p){
        
        PatientNode tmp = new PatientNode();
        tmp = head;
        if(tmp == null){
            tmp = p;
            head = tmp;
            return head;
        }else{
            int headNode = getValue(tmp);
            int currentPatient = getValue(p);
            if(currentPatient == 1){
                while(tmp.next != null){
                    tmp = tmp.next;
                }
                    tmp.next = p;
                    return head;
            }  
         while(tmp.next != null && headNode >= currentPatient){
            int nextHeadNode = getValue(tmp.next);
                if(nextHeadNode <currentPatient){
                    p.next = tmp.next;
                    tmp.next = p;
                    return head;
                }
            tmp = tmp.next;
            headNode = getValue(tmp);
            currentPatient = getValue(p);
        }    
        if(currentPatient >headNode){
            p.next = tmp;
            tmp = p;
            head =tmp;
            return head;
        }
            tmp.next = p;   
        }
        return head;    
    }
    
     /**
     * Method to determine priority queue level of incoming patient.
     * @param p <code>PatientNode </code> incoming patient information
     * @return value <code>int</code> patient queue level.
     */
    public int getValue(PatientNode p){
        if(p.employee == true && p.insured == true){
            return 4;
        }else if(p.employee == true && p.insured == false){
            return 3;
        }else if(p.employee == false && p.insured == true){
            return 2;
        }else if(p.employee == false && p.insured == false){
            return 1;
        }
        
        return 1;
    }
    
    /**
     * Method to pull the first node from incoming linked list and print name and parse.
     * @param head <code>PatientNode</code> Linked list containing patient nodes
     * @return updated list
     */
    public PatientNode pop(PatientNode head){
        
        if(head !=null){
            if(head.queue.equals("0")){
                condition = "Myocardial Infarction (MI)";
            }else if(head.queue.equals("1")){
                condition = "Gushing Artery (GA)";
            }else if(head.queue.equals("2")){
                condition = "Cephalalgia (C)";
            } 
            System.out.println("Patient: "+ head.name + " Condition: "+ condition); 
            head=head.next;
            
        }else{
            System.out.println("No Patients in Queue.");
        }
        return head;
    }
}


