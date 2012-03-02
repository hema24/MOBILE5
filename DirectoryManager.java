  package com.managementconsole.OID;

 import javax.naming.Context;
 import javax.naming.directory.InitialDirContext;
 import javax.naming.directory.SearchControls;
 import javax.naming.directory.Attributes;
 import javax.naming.directory.BasicAttributes;
 import javax.naming.directory.Attribute;
 import javax.naming.directory.SearchResult;
 import javax.naming.directory.BasicAttribute;
 import javax.naming.directory.DirContext;
 import oracle.ldap.util.*;


 import oracle.ldap.util.jndi.*;


 import javax.naming.NamingException;

 import javax.naming.NamingEnumeration;
 import javax.naming.AuthenticationException;
 import javax.naming.NameAlreadyBoundException;

 import java.util.List;
 import java.util.Map;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.Collection;
 import java.util.TreeMap;
 import java.util.Hashtable;

 import javax.naming.ldap.InitialLdapContext;

  



 /**
  * This class manages all Directory operations.
  */
 public class DirectoryManager  {

   private DirContext dirctx = null;
     OIDConnection env = new OIDConnection();

   /**
    * Empty default Constructor.
    */
   public DirectoryManager() {
   }

   /**
    * Checks if the specified uname is a member of the specified group.
    * 
    * @param uname  Relative Distinguished name of the user
    * @param groupDN Distingushed name of the group
    * @return  true - if the user belongs to the group, else false
    * @exception NamingException if any directory operation fails
    */
   public boolean isUserInGroup(String uname,String groupDN) 
     throws NamingException {
     
     boolean ingroup = false;

     // Get the Distinguished Name of the user
     String userDN = this.getUserDN(uname);    
     
     // Filter to check if the user DN is a member
     // A user is a member of a group if the uniqueMember attribute of that group entry
     // has the user DN value.
     String filter = "(uniqueMember="+userDN+")";

     // Initialize search controls to search with scope as sub tree
     SearchControls searchControls = new SearchControls();
     searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE) ;
     // Set the attributes to be returned 
     searchControls.setReturningAttributes(new String[]{"cn"});

     // Search under the specified group
     NamingEnumeration  results = dirctx.search(groupDN, filter, searchControls);

     // If the search has results, then the user is a member  
    //System.out.println("The result in Directory Manager is "+results);
   /// if (results != null)  {
      if (results.hasMore()) { 
          ingroup = true;
      }  // else user not present, i.e defaulted
  //  }
  
     //System.out.println("The ingroup is "+ingroup);

      return ingroup;
   }

     /**------For getting Users in DirectConnect Group------
      * Returns the users in the groups
      * Checks if the specified uname is a member of the specified group.
      * 
      * @param uname  Relative Distinguished name of the user
      * @param groupDN Distingushed name of the group
      * @return  true - if the user belongs to the group, else false
      * @exception NamingException if any directory operation fails
      */
     public boolean isUserInDCGroup(String uname,String groupDN) 
       throws NamingException {
       
       ArrayList searchResults =new ArrayList(1000);
         boolean ingroup = false;
       // Get the Distinguished Name of the user
       String userDN = this.getUserDN(uname);    
         System.out.println("In is userinDC group -----");
         System.out.println("In DirectoryManger in isUserIndcGroup STRING uNAME is-------"+uname);
         System.out.println("In DirectoryManger in isUserInDCGroup STRING getUserDN is-------"+userDN);
         System.out.println("In DirectoryManger in isUserInDCGroup STRING groupDN is-------"+groupDN);
       // has the user DN value.
       String filter = "(uniqueMember="+userDN+")";
 //NamingEnumeration results = oIDTransaction.getSearchResults
 //("cn=users,dc=fjcs,dc=net","(&(orgcustomernumber="+orgCustomerNumber1+")(orgrole="+search+"))"  );

       // Initialize search controls to search with scope as sub tree
       SearchControls searchControls = new SearchControls();
       searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE) ;
       // Set the attributes to be returned 
       searchControls.setReturningAttributes(new String[]{"cn"});

       // Search under the specified group
       NamingEnumeration  results = dirctx.search(groupDN, filter, searchControls);

       // If the search has results, then the user is a member  
      System.out.println("The result in Directory Manager is "+results);
     /// if (results != null)  {
        if (results.hasMore()) { 
            ingroup = true;
        }  // else user not present, i.e defaulted
     //  }
     
       System.out.println("The ingroup is "+ingroup);

        return ingroup;
     }



   /**
    *  Authenticates the user credentials with Directory.
    *  
    * @param username  User Name of the user
    * @param passwd Password of the user
    * @return  true - if the credentials are valid
    * 
    * @exception AuthenticationException If credentials are invalid
    * @exception NamingException if any directory operation fails
    */
   public boolean authenticateUser(String username, String passwd) 
     throws AuthenticationException,NamingException {

       boolean authorized = false;
           
       // Get the Distinguished Name 
      // String dn = this.getUserDN(username);
       
       //try {
         // Authenticate with Directory
         try {
         //dirctx = this.getDirectoryContext("cn="+username+","+"cn=users,dc=fjcs,dc=net",passwd);
          dirctx = this.getDirectoryContext("cn="+username+","+"cn=activedir_fc,cn=users,dc=fjcs,dc=net",passwd);
         
         return true;
         }
         catch(AuthenticationException authEx) {
             System.out.println(" not a customer user. Its a employee user "+authEx.getMessage());
             //throw new AuthenticationException(" not a customer user. Its a employee user ");  
         }
              try {
              //dirctx = this.getDirectoryContext("cn="+username+","+"cn=users,dc=fjcs,dc=net",passwd);
               dirctx = this.getDirectoryContext("cn="+username+","+"cn=customers,cn=users,dc=fjcs,dc=net",passwd);
              
              return true;
              }
              catch(AuthenticationException authEx) {
                  System.out.println(" not a customer user. Its a employee user "+authEx.getMessage());
                  //throw new AuthenticationException(" not a customer user. Its a employee user ");  
              }
         try {
         //if (dirctx == null || dirctx.equals("")) {
         System.out.println("It is inside the if of directorymanager ");
         //dirctx = this.getDirectoryContext("cn="+username+","+"cn=activedir_users,cn=users,dc=fjcs,dc=net",passwd);
             dirctx = this.getDirectoryContext("cn="+username+","+"cn=activedir_users,cn=users,dc=fjcs,dc=net",passwd);
          //dirctx = this.getDirectoryContext(dn,passwd);
         System.out.println("It is inside the if of directorymanager "+dirctx);
         return true;
        //}
         }
       catch(AuthenticationException authEx) {
           System.out.println("  Its a employee user. but exception thrown "+authEx.getMessage());
           //throw new AuthenticationException(" not a customer user. Its a employee user ");  
       }
         
         
       //} catch(AuthenticationException authEx)   {
       
       //  throw new AuthenticationException(" Invalid Password ");
       //}

       //authorized = true;
         
       return authorized;
   }

   /**
    * Retrieves the Distinguished name of them of the specified RDN.
    * 
    * @param uname  Relative Distinguished name.
    * @return  Distinguished name of the user
    * @exception NamingException if directory operation fails
    */
   public String getUserDN(String uname) 
     throws NamingException {
   
     DirContext dCtx = null;
     
     // if Grocery context is available, use it, else create one as application entity
     if(dirctx==null) {
       //dCtx = this.getDirectoryContext("orclApplicationCommonName=GroceryStoreApp,cn=GroceryStore,cn=Products,cn=OracleContext,"+GroceryNames.rootContext,"");
        
        dCtx = this.getDirectoryContext(uname,"");
     }   else {
       dCtx = dirctx;
     } 
       
       SearchResult searchResult = null;
       NamingEnumeration results = null;
      String userDN = null;
     // String filter="("+GroceryNames.RDN+"="+uname+")";
     // String filter = "uid="+uname;   THIS WAS ORIGINAL
     String filter = "cn=" + uname;
       String base = "dc=fjcs,dc=net";
     
         // To set search controls to search with subtree scope
         SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

         // Search the directory based on the search string from the specified context
         results = dCtx.search(base , filter, searchControls);

         // If matching record found
         if (results.hasMore()) { 
             
             searchResult = (SearchResult)results.next();            
             // Build the User DN
             userDN = searchResult.getName() + "," +base;

         } else {
           // User not found
           throw new NamingException(" Invalid Username ");
         }
      return userDN;
   }

   /**
    *  Initializes a Directory Context with the specified credentials and return it.
    *  If the password is blank(null), it binds as anonymous user and returns the
    *  context.
    *  
    * @param username Directory user name
    * @param password Directory user password
    * @return  valid directory context, if credentials are valid
    * @exception AuthenticationException  if credentails are invalid
    * @exception NamingException if directory operation fails
    */
   public DirContext getDirectoryContext(String username,String password) 
        throws AuthenticationException,NamingException {

      DirContext dCtx = null;
      
       //Build the LDAP url 
   //    String ldapurl = "ldap://" + "brios-dev3.fjcs.net" + ":" + "389";  // original
   //   String ldapurl = "ldap://" + "brios-dev3.fjcs.net" + ":" + "636";
     

   /*    Hashtable env = new Hashtable();
       env.put(Context.INITIAL_CONTEXT_FACTORY,
           "com.sun.jndi.ldap.LdapCtxFactory");
       env.put(Context.PROVIDER_URL, ldapurl);
       
       // if password is specified, set the credentials
       if( password != null ) {
         env.put(Context.SECURITY_AUTHENTICATION, "simple");
         env.put(Context.SECURITY_PRINCIPAL,username);
         env.put(Context.SECURITY_CREDENTIALS, password);
       }    */   //original
       
       InitialDirContext dCtx1 = ConnectionUtil.getSSLDirCtx(env.getString("hostname"),env.getString("port"),username,password);

       // Bind and initialize the Directory context
    //   dCtx = new InitialDirContext(env);

      // return dCtx;
      return dCtx1;
   }

   /**
    * Searchs the directory under the specified context with specified filter 
    * and returns the search results.
    * The scope is set to one-level.
    * 
    * @param ctxname Context under which seach has to be done.
    * @param filter Search filter
    * @return  Attributes matching the search specification
    * @exception NamingException if directory search fails
    */
    public Collection search( String ctxname, String filter)
      throws NamingException {

       SearchResult searchResult = null;
       NamingEnumeration results = null;
       Collection retColl = new ArrayList();
       Attributes attrs = null;
   
         // Initialize search search controls with one-level scope
         SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

         // Search the directory based on the search string under the specified context
         results = dirctx.search(ctxname, filter, searchControls);

         // Check if any matching results were found in Directory
         if (results.hasMore()) { 
   
           // Iterate through the results and populate the return collection
           do { 
           
             searchResult = (SearchResult)results.next();
             // Get the attributes 
             attrs = searchResult.getAttributes();

             retColl.add(attrs);
             
           }while(results.hasMore()); 
         }
         return retColl;
      } 
       

   /**
    * Creates an entry in Directory with the specified attributes and objectclass,  
    * with the specified Distingushed Name.
    * 
    * @param dn Distinguished name of the entry to be created
    * @param objCls Object classes that the entry must use
    * @param map Attribute,value mappings of the entry
    * @exception NamingException if adding entry fails
    * @exception NameAlreadyBoundException if user already exists
    */
    public int addDirectoryEntry(String dn, List objCls, Map map)
      throws NamingException,NameAlreadyBoundException {

       // Create attribute list, ignore case of attribute names
       Attributes attrs = new BasicAttributes(true);
         System.out.println("Inside the addDirectoryEntry");
       if( !objCls.isEmpty()) {
         Attribute objclass = new BasicAttribute("objectclass");
        //   System.out.println("Inside the objCls.isEmpty 1");
         // Iterate thriough the collection and add the object classes to the attribute
         Iterator objclsIter = objCls.iterator();        
         while(objclsIter.hasNext()) {
           // Add the object classes    
      //      System.out.println("Inside the objCls.isEmpty 2");
           objclass.add(objclsIter.next());  
         }             
         // Add the object class attribute to list
         attrs.put(objclass);
        //   System.out.println("Inside the objCls.isEmpty 3");
       }

       // Iterate through other attributes and add to attributes list
       Iterator attrsIter = map.entrySet().iterator();
     //   System.out.println("Inside the objCls.isEmpty 4");

       while( attrsIter.hasNext() ) {
      //     System.out.println("Inside the objCls.isEmpty 5");
         Map.Entry attr = (Map.Entry)attrsIter.next();
       //    System.out.println("Inside the objCls.isEmpty 6");
         attrs.put(new BasicAttribute((String)attr.getKey(),attr.getValue()));
       //    System.out.println("Inside the objCls.isEmpty 7");
       }
       
       // add the directory entry to the directory with the attributes
       try {
       DirContext ctxx = dirctx.createSubcontext(dn, attrs);
       if (ctxx != null) {
           return 0;
       }
       else {
           return 1;
       }
       
      
     
       }
       catch (Exception e ) {
           System.out.println("The exception in addDirectory is "+e.getMessage());
           e.printStackTrace();
           return 1;
       }
        
      
    }


   /**
    * Replaces the specified attributes of an entry.
    * 
    * @param dn The distinguished name of the entry whose attributes have to be modified
    * @param map Attribute,Value pair to be replaced
    * @exception NamingException if directory operation fails, or attribute is not present
    */
    
    public void setGeminiInitialContext(InitialLdapContext ctx) {
        this.dirctx = ctx;
    }
   public int modifyDirectoryEntry(String dn, Map map) 
     throws NamingException  {

       // Get the attribute,newvalue mapping
       Iterator attrsIter = map.entrySet().iterator();

       // Construct the attributes list
       Attributes attrs = new BasicAttributes(true); // case-ignore

       while( attrsIter.hasNext() ) {
         Map.Entry attr = (Map.Entry)attrsIter.next();
         attrs.put(new BasicAttribute((String)attr.getKey(),attr.getValue()));
       }

       // Replace the existing attribute values with the specified new values
       
     //   System.out.println("Hello from modifyDirectoryEntry of DirectoryManager dirctx"+dirctx);
        try {
       dirctx.modifyAttributes(dn,DirContext.REPLACE_ATTRIBUTE, attrs);
       return 0;
        }
        catch (Exception e) {
            return 1;
        }
     
   }
   
     /**
     * Method to create a new OID user under the default or provided context path. 
     * @param userid
     * @param password
     * @param firstname
     * @param lastname
     * @param displayname
     * @param mail
     * @throws NamingException
     * @throws UtilException
     */
   //  public void createLdapUser(String userid, String password, 
   // String firstname, String lastname, 
   //  String telephone, 
   //  String mail, InitialLdapContext ctx ) throws NamingException, UtilException
 /*   public void createLdapUser(TreeMap userHash,InitialLdapContext ctx ) throws NamingException, UtilException
      {
         System.out.println("Inside createLdapUser ");
     Map userHashTable = userHash;
     //userHashTable.put("cn", userid);  //try also uid
     //userHashTable.put("uid", userid);
    // userHashTable.put("userpassword", password);
    // userHashTable.put("sn", lastname);
    // userHashTable.put("givenname", firstname);
     //userHashTable.put("telephonenumber", telephone);
    // userHashTable.put("mail", mail);
     createLdapUser(userHashTable, ctx);
     }  */

     /**
     * Method to create a new OID user under the default or provided context path. 
     * Missing mandatory properties will cause and Exception to be thrown
     * 
     * @param userLdapProperties Map of OID property and value pairs, where the property is 
     * the key in the Map and the property value the Map value
     * @return The newly created user
     * @throws NamingException
     * @throws UtilException
     */
     public oracle.ldap.util.User createLdapUser(Map userLdapProperties, InitialLdapContext ctx) throws NamingException, 
     UtilException {
         System.out.println("Inside createLdapUser ");
     Subscriber mysub = null;
     Iterator oidUserProperties = userLdapProperties.keySet().iterator();
     Iterator oidUserPropertyValues = 
     userLdapProperties.values().iterator();
     
   //  System.out.println("The oidUserProperties "+oidUserProperties);
    // System.out.println("The oidUserProperties "+oidUserPropertyValues);
     

     //ldapSubscriberPath = this.getLdapSubscriberPath();
        // RootOracleContext roc = new RootOracleContext(ctx);

        // Subscriber subscriber = roc.getSubscriber(ctx, Util.IDTYPE_DN, "o=dec", );
         
          //Attribute defaultSubscriber = ctx.getAttributes("dc=fjcs,dc=net").get("orcldefaultsubscriber");
          //String subscriberPathName = (String)defaultSubscriber.getAll().next();
          
           Attribute defaultSubscriber = ctx.getAttributes("cn=Common, cn=Products, cn=OracleContext").get("orcldefaultsubscriber");
           String subscriberPath =(String) defaultSubscriber.getAll().next();


     Subscriber subscriber = new Subscriber(ctx, Util.IDTYPE_DN, subscriberPath,false); 
     
      

    

     ModPropertySet userProperties = new ModPropertySet();

     while (oidUserProperties.hasNext() && 
     oidUserPropertyValues.hasNext()) {
     System.out.println("Inside the while loop ");
     String propertyName = (String)oidUserProperties.next();
   //  System.out.println(    propertyName);
     String propertyValue = (String)oidUserPropertyValues.next();
   //      System.out.println(    propertyValue);
         
         //userProperties.addProperty(propertyName,propertyValue);
     userProperties.addProperty(LDIF.ATTRIBUTE_CHANGE_TYPE_ADD, 
     propertyName, propertyValue);
   //  System.out.println(    userProperties);    
     }
     System.out.println("Outside the while loop");
    //     System.out.println("The userProperties is "+userProperties);
         //ModPropertySet mps = new ModPropertySet();
         //    mps.addProperty(LDIF.ATTRIBUTE_CHANGE_TYPE_ADD,"cn", "John");
         //    mps.addProperty(LDIF.ATTRIBUTE_CHANGE_TYPE_ADD,"sn", "John");
         //    mps.addProperty(LDIF.ATTRIBUTE_CHANGE_TYPE_ADD,"uid", "John");

             // Create the user
             //User newUser = subscriber.createUser( ctx, mps, false );
             //System.out.println("New User DN: " + newUser.getDN( ctx ) );
         oracle.ldap.util.User usr = null;
 try{
      usr = subscriber.createUser(ctx, userProperties, false);
 //    System.out.println("New User DN: " + usr); 
     //System.out.println("The guid isn "+usr.getGUID(ctx));
 }
       
         catch (UtilException m) {
             System.out.println("The message is UtilException "+m.getMessage());
         }
 catch (Exception e) {
     e.printStackTrace();
 }
     System.out.println("New User DN: " + usr.getDN( ctx )); 
     System.out.println("The guid isn "+usr.getGUID(ctx));
     return usr;
     }


   /**
    *  Adds the specified user to the group.
    *  
    * @param uid Relative distinguished name of the entry 
    * @param groupdn Group to which the user has to be added
    * @exception NamingException if adding to group fails, or user is already a member
    */
   public void addToGroup(String uid,String groupdn) 
     throws NamingException  {
       // Build the distinguished name of the entry
       String userdn = this.getUserDN(uid);
       
       Attributes attrs = new BasicAttributes(true); 

       //The DN of the user  has to be added to the uniqueMember attribute of the group 
       // to become a member
       attrs.put(new BasicAttribute("uniqueMember", userdn));

       // Add the user as member
       dirctx.modifyAttributes(groupdn,DirContext.ADD_ATTRIBUTE,attrs);
     
   }

   /**
    * Removes the specified user from the group.
    * 
    * @param uid  User to be removed from group
    * @param groupdn Group from which the user has to be removed
    * @exception NamingException if directory opertaion fails, or the user is not a memeber of this group
    */
   public void removeFromGroup(String uid,String groupdn) 
     throws NamingException  {
       // Build the user distinguished name
       String userdn = this.getUserDN(uid);
       
       Attributes attrs = new BasicAttributes(true); // case-ignore

       // Remove the user dn from the uniqueMember attribute
       attrs.put(new BasicAttribute("uniqueMember", userdn));

       // Modify the attributes in Directory
       dirctx.modifyAttributes(groupdn,DirContext.REMOVE_ATTRIBUTE,attrs);
   }


   /**
    *  Delete the specified entry from Directory.
    *  
    * @param dn The distinguished name of the entry to be removed
    * @exception NamingException if entry not found
    */
   public void deleteDirectoryEntry(String dn)
     throws NamingException  {
       // delete the entry with this DN
       dirctx.destroySubcontext(dn);
   }

   

 }