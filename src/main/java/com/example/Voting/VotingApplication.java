package com.example.Voting;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

//@SpringBootApplication
public class VotingApplication {

	public static void main(String[] args) {
        //SpringApplication.run(VotingApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.Voting");
        while(true) {
            System.out.println("WELCOME");

            AuthorityAccess authorityAccess = (AuthorityAccess) context.getBean("authority");
            Scanner input = new Scanner(System.in);
            System.out.println("""
                    Choose from below
                    1.Want to Vote
                    2.See All Votes(ADMIN)
                    """);
            int userInput = input.nextInt();
            String beanID = "";
            switch (userInput) {
                case 1: {
                    System.out.println("Enter Your Username");
                    input.nextLine();
                    String username = input.nextLine();
                    User user = (User) context.getBean("user");
                    user.setUserName(username);
                    System.out.println("""
                            Choose from below
                            1.Democratic
                            2.Republic
                            3.Independent
                            """);
                    int partyInput = input.nextInt();
                    switch (partyInput) {
                        case 1: {
                            beanID = "democratic";
                            break;
                        }
                        case 2: {
                            beanID = "republic";
                            break;
                        }
                        case 3: {
                            beanID = "independent";
                            break;
                        }
                    }
                    PoliticalParty party = (PoliticalParty) context.getBean(beanID);
                    user.setPoliticalParty(party);//political party inject into the user
                    UserList userList = (UserList) context.getBean("userList");
                    userList.addUser(user);
                    //authorityAccess.setUserList(userList);
                    System.out.println("Thank You");
                    break;
                }
                case 2: {
                    authorityAccess.getUserList().getUserList().
                            forEach(item -> System.out.println(item.getUserName() + " is voted for " + item.getParty().getPartyName()));
                    break;
                }
            }
        }
    }

}
