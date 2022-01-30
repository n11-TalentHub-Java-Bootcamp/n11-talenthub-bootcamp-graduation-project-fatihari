# n11 talenthub bootcamp graduation project - fatihari


> Homework done in Java using Spring Boot and using PostgreSQL.
>
> According to the layered architecture in the project, interfaces (abstract classes) and implemented classes (concrete classes) are used on both Service side.
>
> Sample script file containing the data inserted into the PostgreSQL database have been added under the "resource" folder.
>
> PostgreSQL connection information has been added as "application.properties" file under the name of "resource" folder.
>
> The SMS API used was created by logging in from [Twilio](https://www.twilio.com). 
>
> Below is the required dependency for Maven.

```java
<dependency>
  <groupId>com.twilio.sdk</groupId>
  <artifactId>twilio</artifactId>
  <version>8.25.1</version>
</dependency>
```
> Twilio generates a local but different number each time on the trial account in regions other than the USA and Canada. In trial mode, a message is sent to only one number.
>
> ![image](https://user-images.githubusercontent.com/57245919/151708082-7ea3de17-5146-49d7-88bd-f71bb4e668dc.png)
>
> The account information in the image above is kept in the file named "application.yml" under the resources folder.

><br>
> IMPORTANT NOTE1:
> When the program is run, the user is created first. Then, a credit application is made with only the "user_account_id" of the registered user in the system.

><br>
> IMPORTANT NOTE2:
> For security reasons, user numbers and ID numbers will be shared as fake numbers in the script file. But to send sms to the phone, a real number must be entered! In the screenshot, these parts are censored.

><br>
> IMPORTANT NOTE3:
> The credit score, which is thought to have been written before, is calculated as follows in CreditScoreService:

```java
    // if age of user is equal to 20, so Credit Score is 20 x 25 = 500.
    @Override
    public int findCreditScore(Long userId)
    {
        return (Calendar.getInstance().get(Calendar.YEAR) - 
            Integer.parseInt(findByUserId(userId).getDateOfBirth().toString().substring(0,4))) * 25;
    }
```
> In short, the Credit Score is calculated to be 25 times the age. 
><br>
# 1-Entities

## 1.1 user_account entity
![image](https://user-images.githubusercontent.com/57245919/151709460-af82f6e6-2fc8-46db-827d-09c785714af4.png)

## 1.2 credit_application entity
![image](https://user-images.githubusercontent.com/57245919/151709544-c67e8926-b989-4f25-8d35-0e6a9ca1bebc.png)


##  2-UserRestController
### 2.1 save user
![image](https://user-images.githubusercontent.com/57245919/151709803-f59ca131-a59b-4fb0-a154-91c2b6b8414c.png)
![image](https://user-images.githubusercontent.com/57245919/151709880-a0c9178a-3fe1-4dbc-b7eb-4e86b13fc94c.png)
### 2.2 update user
![image](https://user-images.githubusercontent.com/57245919/151709953-ff8273a4-cd96-429a-b6fc-d8f04cb928d7.png)
![image](https://user-images.githubusercontent.com/57245919/151710011-d58d547c-e800-4377-b5ba-26a7da7c377f.png)
### 2.3 delete user
![image](https://user-images.githubusercontent.com/57245919/151710066-680e3d5c-e1b1-4f19-ab7a-d592cd1b156e.png)
![image](https://user-images.githubusercontent.com/57245919/151710086-f6941ed4-3886-401a-a257-b0c4233243be.png)

> Credit applications will now be made for existing users registered in the system.

## 3-CreditRestController
### 3.1 save credit application
- If the credit score is below 500, the user will be rejected. (Credit result: Rejection) 
>![image](https://user-images.githubusercontent.com/57245919/151710599-bf491b49-41c4-458e-b25a-587b2c9f1d31.png)
>
><br>
> credit_application table
>
> ![image](https://user-images.githubusercontent.com/57245919/151710673-7df43bb9-9294-4fbe-965a-d3364d97998e.png)
>
>
><br>
> reference user_account table
>
> ![image](https://user-images.githubusercontent.com/57245919/151711816-0e86ed30-b223-4c4e-a1a5-573663faeb7a.png)
>
><br>
>
> ![image](https://user-images.githubusercontent.com/57245919/151710571-7f6ea151-dee9-48ce-afb6-734c079b1852.png)
>
>
>
- If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the loan application of the user is approved and a limit of 10.000 TL is assigned to the user. (Credit Result: Approval) 
>![image](https://user-images.githubusercontent.com/57245919/151710864-be2dd63a-92d3-4dab-a527-dd5072e4148f.png)
>
><br>
> credit_application table
>
>![image](https://user-images.githubusercontent.com/57245919/151710905-e6e6976f-6f2c-4b32-93e9-af03b8b1b4ab.png)
>
><br>
> reference user_account table
>
>![image](https://user-images.githubusercontent.com/57245919/151711879-5b593f11-cb6f-4eba-b8c7-bca7ede70a48.png)
>
><br>
>
> ![image](https://user-images.githubusercontent.com/57245919/151710948-96e84ac4-d04c-4eee-8e06-5ff39591f6a7.png)
>
>
>
- If the credit score is between 500 points and 1000 points and the monthly income is between 5000 TL and 10,000 TL, the credit application of the user is approved and a 20,000 TL limit is assigned to the user. (Credit Result: Approval)
>![image](https://user-images.githubusercontent.com/57245919/151711051-fcc297fc-3691-4eb5-a4cf-90f9a3063ec2.png)
>
><br>
> credit_application table
>
>![image](https://user-images.githubusercontent.com/57245919/151711089-a9723f54-f7bc-4837-a0a0-c8b9d52ae9d9.png)
>
><br>
> reference user_account table
>
>![image](https://user-images.githubusercontent.com/57245919/151711921-99c98f68-442d-4030-91b6-fced292b177f.png)
>
><br>
>
>![image](https://user-images.githubusercontent.com/57245919/151711145-9e24f8ee-8193-4525-89b1-4d163e58846c.png)
>
>
>
- If the credit score is between 500 points and 1000 points and the monthly income is over 10,000 TL, the user's credit application is approved and the user is assigned a limit of MONTHLY INCOME INFORMATION * CREDIT LIMIT MULTIPLIER / 2.
(Credit Result: Approval)
<br> <br>CREDIT LIMIT MULTIPLIER = 4
>![image](https://user-images.githubusercontent.com/57245919/151711343-9a5740f8-535e-490c-b949-85ad8eb72416.png)
>
><br>
> credit_application table
>
>![image](https://user-images.githubusercontent.com/57245919/151711398-09b7da64-2793-4dba-8846-d7e6523872e1.png)
>
><br>
> reference user_account table
>
>![image](https://user-images.githubusercontent.com/57245919/151712007-4df1f839-e0d4-436c-bf88-83bbb9e2a6b6.png)
>
><br>
>
>![image](https://user-images.githubusercontent.com/57245919/151711441-1eb40833-286f-428d-a743-fdd537577ccb.png)
>
>
>
- If the credit score is equal to or above 1000 points, the user is assigned a limit equal to MONTHLY INCOME * CREDIT LIMIT MULTIPLIER. (Credit Result: Approval)
<br> <br>CREDIT LIMIT MULTIPLIER = 4
>![image](https://user-images.githubusercontent.com/57245919/151711553-4f09acb3-1ce0-446f-8d75-5d462f2ed85f.png)
>
><br>
> credit_application table
>
>![image](https://user-images.githubusercontent.com/57245919/151711587-768d1308-2444-46da-88f0-f49c24e32b05.png)
>
><br>
> reference user_account table
>
>![image](https://user-images.githubusercontent.com/57245919/151712064-ebf72d40-8ec9-4588-879f-bb81900e7bea.png)
>
><br>
>
>![image](https://user-images.githubusercontent.com/57245919/151711636-e362dafa-49eb-4614-9eda-6143a8455646.png)


### 3.2 Find credit application with identification number and date of birth
- If an incorrect identification number or date of birth is entered,
![image](https://user-images.githubusercontent.com/57245919/151712484-6005be6d-8105-442f-be67-bce3e04aaf65.png)

- If the correct identification number and date of birth is entered,
![image](https://user-images.githubusercontent.com/57245919/151712615-6822ef1f-0c28-48de-b8f0-205e1745c7c3.png)

- One another user's query (me)
![image](https://user-images.githubusercontent.com/57245919/151712772-f9da8a3b-1e41-4b7f-bffe-2df448da25ea.png)
