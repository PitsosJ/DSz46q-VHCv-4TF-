import java.util.*;

public class Menu 
{
    static Scanner scanner = new Scanner(System.in);
    public static void init(Organization org)
    {
        System.out.println("Welcome, please enter your phone number to log-in:");
        String phone = scanner.nextLine();
        Boolean userFound = false;
        if (phone.equals(org.getAdmin().getPhone()))
        {
            userFound = true;
            adminMenu(org);
        }
        for (Beneficiary b: org.getBeneficiaryList())
        {
            if (phone.equals(b.getPhone()))
            {
                userFound = true;
                beneficiaryMenu(b,org); 
            }
        }
        for (Donator d: org.getDonatorList())
        {
            if (phone.equals(d.getPhone()))
            {
                userFound = true;
                donatorMenu(d,org);
            }
        }
        if (userFound == false)
        {
            register(org);
        }

    }

    public static void adminMenu(Organization org)
    {
        int selection1 = 0;
        System.out.println("You're currently logged as " + org.getAdmin().getName() + ". Status: " + org.getAdmin().info());
        System.out.println("1. View");
        System.out.println("2. Monitor Organization");
        System.out.println("3. Logout");
        System.out.println("4. Exit");
        while (selection1 < 1 || selection1 > 4)
        {
            System.out.println("Please select an action.");
            selection1 = scanner.nextInt();
            scanner.nextLine();
        }
        if (selection1 == 1)
        {
            if (org.getCurrentDonations().isEmpty())
            {
                System.out.println("Donation list is empty");
            }
            else {
                System.out.println("Materials:");
                for (RequestDonationList rdl: org.getCurrentDonations())
                {
                    for (RequestDonation rd: rdl.getRdEntities())
                    {
                        if (rd.getEntity().getLevel1() >= 0)
                        {
                            rdl.monitor();
                        }
                    }
                }
                System.out.println("Services: ");
                for (RequestDonationList rdl: org.getCurrentDonations())
                {
                    for (RequestDonation rd: rdl.getRdEntities())
                    {
                        if (rd.getEntity().getLevel1() == -1)
                        {
                            rdl.monitor();
                        }
                    }

                }
            }
            returntoAdminMenu(org);
        }
        if (selection1 == 2)
            {
                System.out.println("1. List Beneficiaries");
                System.out.println("2. List Donators");
                System.out.println("3. Reset Beneficiaries List");
                int selection2 = scanner.nextInt();
                scanner.nextLine();
                while (selection2 < 1 || selection2 > 3)
                {
                    System.out.println("Please select an action.");
                    selection2 = scanner.nextInt();
                    scanner.nextLine();
                }
                if (selection2 == 1)
                {
                    org.listBeneficiaries();
                    returntoAdminMenu(org);
                }
                if (selection2 == 2)
                {
                    org.listDonators();
                    returntoAdminMenu(org);
                }
                if (selection2 == 3)
                {
                    for(Beneficiary b: org.getBeneficiaryList())
                    {
                        b.resetReceivedList();
                    }
                    System.out.println("Beneficiary List has been reset!");
                    returntoAdminMenu(org);
                }
            }
            if (selection1 == 3)
            {
                Menu.init(org);
            }
            if (selection1 == 4)
            {
                System.exit(0);
            }
    }
    public static void returntoAdminMenu(Organization org)
    {
        System.out.println("\nPress ENTER to return to main menu");
        scanner.nextLine();
        adminMenu(org);        
    }

    public static void beneficiaryMenu(Beneficiary b, Organization org) //BENEFICIARYMAIN
    {
        System.out.println("You're currently logged as " + b.getName() +". Status: " +b.info());
        System.out.println("1. Add Request");
        System.out.println("2. Show Requests");
        System.out.println("3. Commit");;
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        int selection1 = 0;
        int level = 0;
        if (b.getNoPersons() == 1)
        {
            level = 1;
        }
        if (b.getNoPersons() > 1 && b.getNoPersons() < 5)
        {
            level = 2;
        }
        if (b.getNoPersons() >= 5)
        {
            level = 3;
        }
        while (selection1 < 1 || selection1 > 6)
        {
            System.out.println("Please select an action.");
            selection1 = scanner.nextInt();
            scanner.nextLine();
        }
        if (selection1 == 1) // Add request
        {
            int selection = 0;
            System.out.println("\n1. Material\n2. Service");
            while (!(selection == 1 || selection == 2))
            {
                System.out.println("Please select an action.");
                selection = scanner.nextInt();
                scanner.nextLine();
            }
            if (selection == 1)
            {
                char c = 'z';
                System.out.println("Material List: ");
                for (Entity e: org.getEntityList())
                {
                    if (e.getLevel1() >= 0.0)
                    {
                        System.out.println("\t" + e.getEntityInfo() + " ( " + org.returnQuantity(e.getId(), b.getReceivedList()) + " / " + e.level(level) + " )");
                        // emfanizei mesa stin parenthsi tin posotita pou exoun lavei kai tin posotita pou dikaiounte
                    }
                }
                System.out.println("Would you like to add a request?");
                while (!(c == 'y' || c == 'Y' || c == 'n' || c == 'N'))
                {
                    System.out.println("Please select an action. (y|n)");
                    c = scanner.next().charAt(0);
                }
                if (c == 'y' || c == 'n')
                {
                    int selID = 0;
                    Boolean entityFound = false;
                    while (!entityFound)
                    {
                        System.out.println("Please enter the ID of the Material you want to request.");
                        selID = scanner.nextInt();
                        scanner.nextLine();
                        for (Entity e: org.getEntityList())
                        {
                            if (selID == e.getId())
                            {
                                entityFound = true;
                            }
                        }
                    }
                    System.out.println("Please enter the amount of the Material you want to request.");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    for (Entity e: org.getEntityList())
                    {
                        if(selID == e.getId() && e.getLevel1() >= 0)
                        {
                            RequestDonation a = new RequestDonation(e, amount);
                            Requests r = new Requests();
                            r.add(a, org);
                            b.add(r);
                        }
                    }
                }
                returntoBeneficiaryMenu(b, org);
            }
            if (selection == 2) // gia material 
            {





            }
        }
        if (selection1 == 2)
        {
            if (b.getRequestsList().isEmpty())
            {
                System.out.println("You have no uncommited requests.");
                returntoBeneficiaryMenu(b, org);
            }
            else 
            {
                b.monitor();
                char c = 'z';
                int selID;
                Requests request = null;
                
                while (!(c == 'y' || c == 'Y' || c == 'n' || c == 'N'))
                {
                    System.out.println("Would you like to modify a reuqest? (y|n)");
                    c = scanner.next().charAt(0);
                }
                if (c == 'y' || c == 'Y')
                {
                    boolean idFound = false;
                    while (!idFound)
                    {
                        System.out.println("Enter the id of the entity you would like to modify: ");
                        selID = scanner.nextInt();
                        scanner.nextLine();
                        for (Requests r: b.getRequestsList())
                        {
                            if(r.get(selID) != null)
                            {
                                request = r;
                                idFound = true;
                            }
                        }
                    }
                    int sel = 0;
                    while (!(sel == 1 || sel == 2));
                    {
                        System.out.println("Enter 1 to delete the request or 2 to modify the quantity of the request:");
                        sel = scanner.nextInt();
                        scanner.nextLine();
                    }
                    if (sel == 1)
                    {
                        int index = b.getRequestsList().indexOf(request);
                        b.requestRemove(index);
                        System.out.println("Request was deleted successfully.");
                    }
                }
                if (c == 'n' || c == 'N')
                {
                    returntoBeneficiaryMenu(b, org);
                }
                
            }
        }
        if (selection1 == 3)
        {
            if(b.getRequestsList().isEmpty())
            {
                System.out.println("Your request list is empty.");
                returntoBeneficiaryMenu(b, org);
            }
            else
            {
                b.commit(org);
            }
        }
        if (selection1 == 4)
        {
            init(org);
        }
        if (selection1 == 5)
        {
            System.exit(0);
        }

    }

    public static void returntoBeneficiaryMenu(Beneficiary b, Organization org)
    {
        System.out.println("\nPress ENTER to return to main menu");
        scanner.nextLine();
        beneficiaryMenu(b,org); 
    }

    public static void donatorMenu(Donator d, Organization org) //DONATORMAIN
    {
        System.out.println("You're currently logged as " + d.getName() +". Status: " +d.info());
        System.out.println("1. Add Offer");
        System.out.println("2. Show Offers");
        System.out.println("3. Commit");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        int selection1 = 0;
        while (selection1 < 1 || selection1 > 5)
        {
            System.out.println("Please select an action.");
            selection1 = scanner.nextInt();
            scanner.nextLine();
        }

        if (selection1 == 1) // ADD OFFER
        {
            int selection = 0;
            System.out.println("\n1. Material\n2. Service");
            
            while (!(selection ==1 || selection == 2))
            {
                System.out.println("Please select an action.");
                selection = scanner.nextInt();
                scanner.nextLine();
            }
            if (selection == 1)
            {
                char c = 'z';
                System.out.println("Material List: ");
                for (Entity e: org.getEntityList())
                {
                    if (e.getLevel1() >= 0.0)
                    {
                        System.out.println("\t" + e.getEntityInfo() + " (" + org.returnQuantity(e.getId(),org.getCurrentDonations())+")");
                    }
                }
                System.out.println("Would you like to add a donation?");
                while (!(c == 'y' || c == 'n' || c == 'Y' || c == 'N'))
                {
                    System.out.println("Please select an action. (y|n)");
                    c = scanner.next().charAt(0);
                }
                if (c == 'y' || c == 'Y')
                {
                    int selID = 0;
                    Boolean entityFound = false;
                    while (!entityFound)
                    {
                        System.out.println("Please enter the ID of the Material you want to donate.");
                        selID = scanner.nextInt();
                        scanner.nextLine();
                        for (Entity e: org.getEntityList())
                        {
                            if (selID == e.getId())
                            {
                                entityFound = true;
                            }
                        }                        
                    }
                    System.out.println("Please enter the amount of the Material you want to donate.");
                    double amount = scanner.nextDouble();
                    for (Entity e: org.getEntityList())
                    {
                        if(selID == e.getId() && e.getLevel1()>=0) // an to entity einai material 
                        {
                            /*RequestDonation a = new RequestDonation(e, amount);
                            Offers o = new Offers();
                            o.add(a, org);
                            d.add(o);*/
                            RequestDonation a =new RequestDonation(e, amount);
                            d.add(a, org);
                        }
                    }
                }
                returntoDonatorMenu(d, org);
            }
        
            if (selection == 2) 
            {
                char c = 'z';
                System.out.println("Service list:");
                for (Entity e: org.getEntityList())
                {
                    if (e.getLevel1() == -1)
                    {
                        System.out.println("\t" + e.getEntityInfo() + " (" + org.returnQuantity(e.getId(),org.getCurrentDonations())+")");
                    }
                }
                System.out.println("Would you like to add a donation?");
                while(!(c == 'y' || c == 'Y' || c == 'n' || c == 'N'))
                {
                    System.out.println("Please select an action. (y|n)");
                    c = scanner.next().charAt(0);
                }
                if (c == 'y' || c == 'Y')
                {
                    int selID = 0;
                    Boolean entityFound = false;
                    while (!entityFound)
                    {
                        System.out.println("Please enter the ID of the Service you want to donate.");
                        selID = scanner.nextInt();
                        scanner.nextLine();
                        for (Entity e: org.getEntityList())
                        {
                            if (selID == e.getId())
                            {
                                entityFound = true;
                            }
                        }
                    }
                    System.out.println("Please enter the amount of Service you want to donate.");
                    double amount = scanner.nextDouble();
                    for (Entity e: org.getEntityList())
                    {
                        if (selID == e.getId() && e.getLevel1() == -1)
                        {
                            RequestDonation a = new RequestDonation(e, amount);
                            d.add(a, org);
                        }
                    }
                }
                returntoDonatorMenu(d, org);
            }
        }

        if (selection1 == 2)
        {
            if (d.getOfferList().isEmpty())
            {
                System.out.println("You have no uncommited offer");
                returntoDonatorMenu(d, org);
            }
            else {
                d.monitor(org);
                System.out.println(d.getOfferList().size());
                char c = 'a';
                int selID = 0;
                Offers offer = null;
                RequestDonation rd = null;
                while (!(c == 'y' || c == 'Y' || c == 'n' || c == 'N')){
                    System.out.println("Would you like to modify an offer? (y|n)");
                    c = (char)scanner.next().charAt(0);
                }
                if (c == 'y' || c == 'Y')
                {
                    boolean idFound = false;
                    while (idFound == false)
                    {
                        System.out.println("Enter the id of the entity you would like to modify:");
                        selID = scanner.nextInt();
                        scanner.nextLine();
                        for (Offers o: d.getOfferList())
                        {
                            if(o.get(selID) != null)
                            {
                                offer = o;
                                idFound = true;
                            }
                        }
                    }
                    int sel = 0;
                    while (!(sel == 1 || sel == 2))
                    {
                        System.out.println("Enter 1 to delete the offer or 2 to modify the quantity of the offer");
                        sel = scanner.nextInt();
                        scanner.nextLine();
                    }
                    if (sel == 1)
                    {
                        int index = d.getOfferList().indexOf(offer);
                        d.remove(index);
                        System.out.println("Offer was deleted successfully.");
                    }
                    if (sel == 2)
                    {
                        System.out.println("Enter the new quantity:");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        int index = d.getOfferList().indexOf(offer);
                        d.getOfferList().get(index).modify(selID, amount);
                    }
                }

                returntoDonatorMenu(d, org);
            }
        }
        if (selection1 == 3) 
        {
            if (d.getOfferList().isEmpty())
            {
                System.out.println("Your offer list is empty.");
                returntoDonatorMenu(d, org);;
            }
            else {
                d.commit(org);
                returntoDonatorMenu(d, org);
            }
        }
        if (selection1 == 4)
        {
            init(org);
        }
        if (selection1 == 5)
        {
            System.exit(0);
        }
    }
    
    public static void returntoDonatorMenu(Donator d, Organization org)
    {
        System.out.println("\nPress ENTER to return to main menu");
        scanner.nextLine();
        donatorMenu(d,org);  
    }

    public static void register(Organization org)
    {
        System.out.println("Number not detected, would you like to register or try again?");
        int selection = 0;
        while (selection <1 || selection >2)
        {
            System.out.println("1. Register / 2. Try Again");
            selection = scanner.nextInt();
            scanner.nextLine(); // new line dump
        }
        if (selection == 1)
        {
            int selection1 = 0;
            while (selection1 <1 || selection1 >2)
            {
                System.out.println("Chose acount type: 1. Beneficiary or 2. Donator");
                selection1 = scanner.nextInt();
                scanner.nextLine(); // new line dump
            }
            if (selection1 == 1)
            {
                System.out.println("Please enter your name:");
                String name = scanner.nextLine();
                System.out.println("Please enter your phone number:");
                String phone = scanner.nextLine();
                System.out.println("Please enter the number of family members:");
                int noPersons = scanner.nextInt();
                scanner.nextLine(); // new line dump
                org.insertBeneficiary(name,phone,noPersons);
                System.out.println("Success!Now press ENTER and we will take you back to the login page.");
                scanner.nextLine();
                init(org);                
            }
            if (selection1 == 2)
            {
                System.out.println("Please enter your name:");
                String name = scanner.nextLine();
                System.out.println("Please enter your phone number:");
                String phone = scanner.nextLine();
                org.insertDonator(name, phone);
                System.out.println("Success!Now press ENTER and we will take you back to the login page.");
                scanner.nextLine();
                init(org);
            }

        }
        if (selection == 2)
        {
            init(org);
        }
    }
}//