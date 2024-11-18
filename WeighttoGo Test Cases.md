**Test Plan for Android Weight Tracking Application**

**Test Case 1: User Enters a Weight on the Weight Entry Page**

  --------------------------------------------------------------------------
  Step   Action            Expected Result             Successful     Date
                                                       (Y/N)          
  ------ ----------------- --------------------------- -------------- ------
  1.1    Enter a valid     Weight entry is saved to                   
         weight (e.g., 180 the database and viewable                  
         lbs)              on the history page                        

  1.2    Enter an invalid  Error message \"Please                     
         weight (e.g.,     enter a valid weight\" is                  
         letters)          displayed                                  

  1.3    Enter a weight,   Error message \"Please                     
         leave date/time   enter date and time\" is                   
         empty             displayed                                  

  1.4    Leave all fields  Error messages for empty                   
         blank             fields are displayed                       
  --------------------------------------------------------------------------

**Test Case 2: User Views Weight History**

  -----------------------------------------------------------------------------------
  **Step**   **Action**           **Expected Result**       **Successful   **Date**
                                                            (Y/N)**        
  ---------- -------------------- ------------------------- -------------- ----------
  2.1        Navigate to the      History page is displayed                
             History page         showing a list of weight                 
                                  entries                                  

  2.2        View the latest      The correct weight, date,                
             weight entry         and time of entry are                    
                                  displayed                                

  2.3        Delete a weight      Entry is removed from the                
             entry                history page                             

  2.4        Close and reopen the All previously entered                   
             app, then view       weights are still                        
             history              viewable in history                      
  -----------------------------------------------------------------------------------

**Test Case 3: User Edits Profile Information**

  ------------------------------------------------------------------------------------
  **Step**   **Action**               **Expected Result**    **Successful   **Date**
                                                             (Y/N)**        
  ---------- ------------------------ ---------------------- -------------- ----------
  3.1        Go to Profile page and   First name is updated                 
             edit first name          and saved successfully                

  3.2        Enter an invalid name    Error message \"Please                
             (e.g., numbers)          enter a valid name\"                  
                                      is displayed                          

  3.3        Leave required fields    Error message \"Please                
             blank (e.g., gender)     select a gender\" is                  
                                      displayed                             

  3.4        Save the profile and     Profile changes are                   
             check that changes       retained and displayed                
             persist after app is     correctly                             
             reopened                                                       
  ------------------------------------------------------------------------------------

**Test Case 4: User Logs In**

  ------------------------------------------------------------------------------------
  **Step**   **Action**         **Expected Result**          **Successful   **Date**
                                                             (Y/N)**        
  ---------- ------------------ ---------------------------- -------------- ----------
  4.1        Enter valid email  User is successfully logged                 
             and password       in and redirected to                        
                                dashboard                                   

  4.2        Enter incorrect    Error message \"Invalid                     
             email or password  email or password\" is                      
                                displayed                                   

  4.3        Leave fields blank Error messages for empty                    
             and attempt login  email and password fields                   
                                are displayed                               

  4.4        Use the \"Forgot   Password reset instructions                 
             Password?\" option are sent to the user\'s                     
                                email                                       
  ------------------------------------------------------------------------------------

**Test Case 5: User Registers a New Account**

  ------------------------------------------------------------------------------------
  **Step**   **Action**         **Expected Result**          **Successful   **Date**
                                                             (Y/N)**        
  ---------- ------------------ ---------------------------- -------------- ----------
  5.1        Enter valid full   Account is created                          
             name, email, and   successfully, and the user                  
             password           is redirected to the                        
                                dashboard                                   

  5.2        Enter an already   Error message \"Email                       
             registered email   already in use\" is                         
                                displayed                                   

  5.3        Enter invalid      Error message \"Please enter                
             email format       a valid email address\" is                  
                                displayed                                   

  5.4        Leave password     Error message \"Please enter                
             fields blank       a password\" is displayed                   
  ------------------------------------------------------------------------------------

**Test Case 6: User Views Dashboard and Updates Weight**

  ------------------------------------------------------------------------------------
  **Step**   **Action**          **Expected Result**         **Successful   **Date**
                                                             (Y/N)**        
  ---------- ------------------- --------------------------- -------------- ----------
  6.1        Open the dashboard  Dashboard displays the                     
             screen              current weight and progress                
                                 metrics                                    

  6.2        Click the \"Update  The weight entry screen is                 
             Weight\" button     displayed                                  

  6.3        Enter a new weight  The current weight on the                  
             and submit          dashboard updates to                       
                                 reflect the new entry                      

  6.4        Reopen the app and  The updated weight is                      
             view the dashboard  displayed correctly                        
  ------------------------------------------------------------------------------------

**Test Case 7: User Deletes a Weight Entry from History**

  ------------------------------------------------------------------------------------
  **Step**   **Action**               **Expected Result**    **Successful   **Date**
                                                             (Y/N)**        
  ---------- ------------------------ ---------------------- -------------- ----------
  7.1        Navigate to the History  History page is                       
             page                     displayed with a list                 
                                      of weight entries                     

  7.2        Select a weight entry    The weight entry is                   
             and choose \"Delete\"    deleted from the                      
                                      database and history                  
                                      page                                  

  7.3        Confirm that the deleted The entry is                          
             entry is no longer       permanently deleted                   
             viewable in the app      and not visible                       
             after reopening                                                
  ------------------------------------------------------------------------------------

**Test Case 8: User Sets a Weight Goal**

  ------------------------------------------------------------------------------------
  **Step**   **Action**                **Expected Result**   **Successful   **Date**
                                                             (Y/N)**        
  ---------- ------------------------- --------------------- -------------- ----------
  8.1        Enter a valid weight goal Goal is saved and                    
             (e.g., 150 lbs)           displayed on the                     
                                       dashboard and                        
                                       settings page                        

  8.2        Enter an unrealistic      Error message                        
             weight goal (e.g., 0 lbs) \"Please enter a                     
                                       realistic weight                     
                                       goal\" is displayed                  

  8.3        Leave the goal field      Error message                        
             blank                     \"Please enter a goal                
                                       weight\" is displayed                

  8.4        Change the weight goal    The updated goal is                  
             and check if the updated  saved and displayed                  
             goal persists after app   correctly                            
             is reopened                                                    
  ------------------------------------------------------------------------------------

**Test Case 9: User Uploads a Progress Picture**

  ------------------------------------------------------------------------------------
  **Step**   **Action**          **Expected Result**         **Successful   **Date**
                                                             (Y/N)**        
  ---------- ------------------- --------------------------- -------------- ----------
  9.1        On the weight entry The camera opens for taking                
             screen, tap the     a picture                                  
             camera icon                                                    

  9.2        Take a progress     The picture is saved and                   
             picture and confirm viewable on the history                    
             the upload          page with the weight entry                 

  9.3        Delete a progress   The picture is removed                     
             picture from the    along with the weight entry                
             history page                                                   

  9.4        Reopen the app and  The progress picture is                    
             check the history   still available if not                     
             page                deleted                                    
  ------------------------------------------------------------------------------------

**Test Case 10: Accessibility Compliance (508 Compliance)**

  -------------------------------------------------------------------------------------
  **Step**   **Action**             **Expected Result**       **Successful   **Date**
                                                              (Y/N)**        
  ---------- ---------------------- ------------------------- -------------- ----------
  10.1       Check font sizes and   Font sizes are large                     
             readability on all     enough for readability,                  
             screens                and contrast is                          
                                    sufficient                               

  10.2       Test for screen reader Screen readers correctly                 
             functionality on       announce labels and                      
             profile and dashboard  buttons                                  
             screens                                                         

  10.3       Check for focus-based  Focus moves properly                     
             navigation             between input fields and                 
                                    buttons                                  

  10.4       Ensure color contrast  The app\'s color scheme                  
             meets accessibility    (charcoal, green, etc.)                  
             guidelines (4.5:1      meets accessibility                      
             ratio)                 contrast standards                       
  -------------------------------------------------------------------------------------

**Test Case 11: Security and Data Privacy**

  -------------------------------------------------------------------------------------
  **Step**   **Action**                **Expected Result**    **Successful   **Date**
                                                              (Y/N)**        
  ---------- ------------------------- ---------------------- -------------- ----------
  11.1       Register a new account    The password is stored                
             with a password           securely using                        
                                       encryption                            

  11.2       Attempt to access         Sensitive data is                     
             sensitive data            inaccessible without                  
             (passwords, progress      proper credentials                    
             pictures)                                                       

  11.3       Use SQL injection or      The app resists SQL                   
             similar attacks in login  injection or similar                  
             and registration forms    attacks                               

  11.4       Change profile data and   Data is securely                      
             check for any security    updated without leaks                 
             warnings (e.g., data      or insecure storage                   
             leaks)                                                          
  -------------------------------------------------------------------------------------
